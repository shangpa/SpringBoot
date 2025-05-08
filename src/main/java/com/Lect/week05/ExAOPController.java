package com.Lect.week05;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
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

    @ResponseBody
    @GetMapping("/afterAOP")
    public String afterLog(){
        String logMsg;
        try{
            service.placeOrder(0.5);
            logMsg="로그가 정상적으로 기록되었습니다.";
        }catch(Exception ex){
            logMsg=ex.getMessage();
        }
        return logMsg;
    }

    @ResponseBody
    @GetMapping("/aroundAOP")
    public String aroundLog(ModelAndView mav){
        String str= service.check("user","1234");
        return str;
    }
}
