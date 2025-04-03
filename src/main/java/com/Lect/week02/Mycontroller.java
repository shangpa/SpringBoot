package com.Lect.week02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Mycontroller {

	@GetMapping("/modelTest")
	public ModelAndView modelObjTest() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("week02/modelView");
		mav.addObject("obj", "ModelAndView 객체를 이용한 전달한 문자입니다.");
		return mav;
	}
}
