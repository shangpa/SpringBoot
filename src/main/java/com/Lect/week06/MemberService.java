package com.Lect.week06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepos = null;

    public List<Member> getMembers() {
        String sql = "select * from MEMBER";
        return memberRepos.selectAll(sql);
    }
    public List<Member> getMembersUsingParameter() {
        String sql = "select * from MEMBER where email = ? and NAME=?";
        Object[] args = {"virus1@virus.net","std1"};
        return memberRepos.selectAllUsingParameter(sql, args);
    }
}
