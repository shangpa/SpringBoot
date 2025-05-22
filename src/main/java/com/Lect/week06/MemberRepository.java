package com.Lect.week06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
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


}
