package com.Lect.week10;

import com.Lect.week09.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BasicProcessController2 {

    @GetMapping({"/message", "/validation"})
    public String memberShipForm(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "week10/registerForm";
    }

    @RequestMapping("/membershipSubmit")
    public String memberShipSubmit(RegisterRequest regReq, BindingResult result) {
        RegisterRequestValidator regValid = new RegisterRequestValidator();
        regValid.validate(regReq, result);
        if(result.hasErrors()) {
            return "week10/registerForm";
        }
        return "week10/welcome";
    }

}
