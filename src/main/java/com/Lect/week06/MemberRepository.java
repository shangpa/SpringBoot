package com.Lect.week06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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


}
