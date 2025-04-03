package com.Lect.week03;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DIController {
	
	@Autowired
	private WebApplicationContext context;
	
	@GetMapping({"/annotationDI","/quailfyingDI"})
	public ModelAndView annotationDI() {
		ModelAndView mav =new ModelAndView("week03/annotationDIView");
		HardWorkUnit work=(HardWorkUnit)context.getBean("hardWorkUnit");
		mav.addObject("obj",work);
		return mav;
	}
	
	@GetMapping({"configDI"})
	public ModelAndView configDI(ModelAndView mav) {
		SmsSender sms =(SmsSender)context.getBean("configSms");
		List<String> unit = (List<String>)context.getBean("unit");
		mav.addObject("obj1",sms);
		mav.addObject("obj2",unit);
		
		mav.setViewName("week03/configDIView");
		return mav;
	}

}
