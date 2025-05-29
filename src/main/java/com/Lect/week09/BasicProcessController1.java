package com.Lect.week09;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("regist")
public class BasicProcessController1 {
    @GetMapping("/step/{id}")
    public String detail(@PathVariable("id") Long stepId) {
        String viewName = "registerStep"+stepId;
        return "week09/" + viewName;
    }
}
