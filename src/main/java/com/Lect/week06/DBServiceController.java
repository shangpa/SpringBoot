package com.Lect.week06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@Controller
public class DBServiceController {
    @Autowired
    private WebApplicationContext context = null;

    @ResponseBody
    @GetMapping("/usingDataSource")
    public String usingDataSource() throws SQLException {
        DataSource dataSource = (DataSource) context.getBean("dataSource");

        Statement stmt = null;
        Connection conn = null;
        String sql;
        try {
            conn = dataSource.getConnection();
            stmt = conn.createStatement();


            for (int i = 1; i <= 5; i++) {
                sql = "insert into MEMBER(EMAIL,PASSWORD,NAME,REGDATE)"
                        + "values('virus" + i + "@virus.net' , '1234' , 'std" + i + "', now())";
                stmt.executeUpdate(sql);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            stmt.close();
            conn.close();
        }
        return "테이블에 레코드가 추가되었습니다";
    }

    @Autowired
    private MemberService memberService; //인젝션

    @GetMapping("/simpleQuery")
    public ModelAndView simpleQuery(ModelAndView mav) {
        mav.addObject("members", memberService.getMembers());
        mav.setViewName("week06/membersView");
        return mav;
    }

    @GetMapping("/parameterQuery")
    public ModelAndView parameterQuery(ModelAndView mav) {
        mav.addObject("members", memberService.getMembersUsingParameter());
        mav.setViewName("week06/membersView");
        return mav;
    }

    @GetMapping("/listQuery")
    public ModelAndView listQuery(ModelAndView mav) {
        mav.addObject("memberList", memberService.getMembersUsingMap());
        mav.setViewName("week06/memberListView");
        return mav;
    }

    @GetMapping("/updateQuery")
    public ModelAndView updateQuery(ModelAndView mav) {
        mav.addObject("members", memberService.getUpdateMembers());
        mav.setViewName("week06/membersView");
        return mav;
    }

    @GetMapping("/batchUpdateArray")
    public ModelAndView batchUpdateArray(ModelAndView mav) {
        mav.addObject("members", memberService.getBatchUpdateArray());
        mav.setViewName("week06/membersView");
        return mav;
    }

    @GetMapping("/batchUpdateSetter")
    public ModelAndView batchUpdateSetter(ModelAndView mav) {
        mav.addObject("members", memberService.getBatchUpdateSetter());
        mav.setViewName("week06/membersView");
        return mav;
    }

    @GetMapping("/keyHolder")
    public ModelAndView keyHolder(ModelAndView mav) {
        mav.addObject("members", memberService.getKeyHolder());
        mav.setViewName("week06/membersView");
        return mav;
    }

    @GetMapping("/transactionUpdate")
    public ModelAndView transactionUpdate(ModelAndView mav) {
        mav.addObject("members", memberService.transactionProcess());
        mav.setViewName("week06/membersView");
        return mav;
    }

}
