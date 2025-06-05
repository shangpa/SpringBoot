package com.Lect.week09;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("regist")
public class BasicProcessController1 {
    @GetMapping("/step/{id}")
    public String detail(@PathVariable("id") Long stepId) {
        String viewName = "registerStep" + stepId;
        return "week09/" + viewName;
    }

    @PostMapping("/step2")
    public String handleStep2(@RequestParam("view") String view,
                              @RequestParam(value = "agree", defaultValue = "false") Boolean agree,
                              RedirectAttributes redirectAttributes) {
        if (!agree) {
            redirectAttributes.addFlashAttribute("message", "약관 동의를 해주세요.");
            return "redirect:http://localhost:8080/regist/step/1";
        }
        return "week09/" + view;
    }
    /*@PostMapping("/step3")
    public String handleStep3(
            @RequestParam("view") String view,
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model){
        RegisterRequest registerRequest = new RegisterRequest(email, name, password, confirmPassword);
        model.addAttribute("registerRequest", registerRequest);
        return "week09/"+view;
    }*/

    @PostMapping("/step3")
    public String handleStep3(RegisterRequest registerRequest,
                              @RequestParam("view") String view){
        return "week09/" + view;
    }
    @GetMapping("initCommand")
    public String initForm() {
        return "week09/registerStep2";
    }

    //ModelAttribute적용된 메서드는 Mapping 보다 먼저 실행된다
    @ModelAttribute("registerRequest")
    public RegisterRequest initCommand() {
        RegisterRequest cm = new RegisterRequest();
        cm.setName("한글이름을 입력해주세요");
        cm.setEmail("규격에 맞춰서 이메일을 입력해");
        cm.setPassword("영문자 및 특수 문자 포함 최소 4문자입력");
        cm.setConfirmPassword("암호를 다시 한번 입력해주세요");
        return cm;

    }
}
