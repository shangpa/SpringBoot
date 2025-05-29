package com.Lect.week06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;
import java.util.Map;

@Repository
public class MemberRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate=null;
    //생성하지 않고 인젝션 받음

    public List<Member> selectAll(String sql) {
        List<Member> results = jdbcTemplate.query(sql, new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member(
                        rs.getString("EMAIL"),
                        rs.getString("PASSWORD"),
                        rs.getString("NAME"),
                        rs.getTimestamp("REGDATE").toLocalDateTime()
                );
                member.setId(rs.getLong("id"));
                return member;
            }
        });
        return results;
    }

    public List<Member> selectAllUsingParameter(String sql,Object[] args) {
        List<Member> results = jdbcTemplate.query(sql,args, new MemberRowMapper());
        return results;
    }

    public List<Map<String,Object>> selectListMap(String sql,String email) {
        return jdbcTemplate.queryForList(sql,email);
    }

    public void updateMember(String sql,Object[] args) {
        int cnt = jdbcTemplate.update(sql,args);
    }

    public void batchInsertMembers(String sql, List<Object[]> memberData) {
        jdbcTemplate.batchUpdate(sql,memberData);
    }

    public void batchInsertMembersSetter(String sql, List<Member> memberData) {
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {

            //BatchPreparedStatementSetter의 추상메서드 들
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                // PreparedStatement ps : 실제로 실행될 SQL 문. 여기서 ?에 값을 채워 넣음
                // int i : 리스트의 인덱스. 즉, 몇 번째 레코드를 처리 중인지 나타냄
                Member member = memberData.get(i);
                ps.setString(1,member.getEmail());
                ps.setString(2,member.getPassword());
                ps.setString(3,member.getName());
                ps.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));
            }

            @Override
            public int getBatchSize() { //일괄처리할 sql 문의 갯수 반환
                return memberData.size();// List<Member> memberData 의 List 길이
            }
        }
        );
    }



    // Member 객체와 SQL 문, 그리고 인자 배열을 받아서 PreparedStatementCreator 를 반환하는 메서드
    public PreparedStatementCreator createPreparedStatement(Member member, String sql, String args[]) {
        // 익명 내부 클래스로 PreparedStatementCreator 구현
        return new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                // 주어진 sql과 args로 PreparedStatement 객체 생성
                PreparedStatement pstmt = con.prepareStatement(sql, args);

                pstmt.setString(1, member.getEmail());
                pstmt.setString(2, member.getPassword());
                pstmt.setString(3, member.getName());
                // 4번 파라미터: 회원 가입 날짜(시간) 값 세팅 (LocalDateTime → Timestamp로 변환)
                pstmt.setTimestamp(4, Timestamp.valueOf(member.getRegisterDateTime()));

                return pstmt;
            }
        };
    }

    public long insertMember(Member member, String sql) {
        // 데이터베이스에 새 멤버(member)를 삽입(insert)하고, 자동 생성된 id(primary key) 값을 반환하는 메서드

        KeyHolder keyHolder = new GeneratedKeyHolder();
        // KeyHolder: insert 실행 후 생성되는 키(예: auto_increment id)를 담아주는 객체
        // GeneratedKeyHolder(): KeyHolder의 기본 구현체

        PreparedStatementCreator pstmtObj = createPreparedStatement(member, sql, new String[]{"id"});
        // PreparedStatementCreator: 쿼리 실행에 사용할 PreparedStatement(미리 컴파일된 SQL문)를 생성하는 역할
        // createPreparedStatement: 멤버와 SQL, 그리고 반환받고 싶은 컬럼명(여기서는 "id")을 이용해서 객체 생성

        jdbcTemplate.update(pstmtObj, keyHolder);
        // update: DB에 쿼리 실행(여기서는 insert), 두 번째 인자(keyHolder)로 자동 생성된 키 값을 받아온다

        Number keyValue = keyHolder.getKey();
        // keyHolder에서 자동 생성된 키 값을 꺼내옴 (예: insert된 row의 id)

        return keyValue.longValue();
    }

    @Transactional(rollbackFor = SQLSyntaxErrorException.class)
    // 트랜잭션 처리: SQL 구문 오류가 발생하면 전체 작업을 롤백(되돌림)한다는 의미
    public void changePassword(Member member, String newPassword) {
        String sql="insert into MEMBER (EMAIL,PASSWORD,NAME,REGDATE) values (?,?,?,?)";
        // MEMBER 테이블에 새로운 회원을 추가하는 SQL 쿼리문

        insertMember(member,sql);
        // 위에서 만든 SQL로 새 멤버를 DB에 추가 (id 자동 생성됨)

        sql = " update MEMBER set NAME = ?, PASSWORD=? where EMAI=?";
        // 일부러 오타내서 오류생김

        Object[] args1={member.getName(), newPassword, member.getEmail()};
        // 위 UPDATE 쿼리의 ? 에 들어갈 값들 준비 (이름, 새 비밀번호, 이메일 순서)

        updateMember(sql,args1);
        // UPDATE 쿼리를 실행해서 해당 회원의 이름/비밀번호를 실제로 변경
    }

}