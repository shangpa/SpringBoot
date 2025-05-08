package com.Lect.week05;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ExAOPController {
    private WebApplicationContext context = null;
    ExAOPService service = null;

    @Autowired
    public ExAOPController(WebApplicationContext context){
        this.context = context;
        service = (ExAOPService)context.getBean("exAOPService");
    }

    @ResponseBody
    @GetMapping("/beforeAOP")
    public String preAuthenticate(){
        service.performSensitiveOperation("타겟메서드호출",3);
        return "인증이 정상적으로 처리되었습니다. ";
    }
}
