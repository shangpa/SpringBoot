package com.Lect.week03;

import org.springframework.stereotype.Controller;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContextController {
	private XmlWebApplicationContext xmlContext;
	private AnnotationConfigWebApplicationContext configContext;
	ModelAndView mav;
	
	public ContextController() {
		super();
		xmlContext = new XmlWebApplicationContext();
		configContext = new AnnotationConfigWebApplicationContext();
		mav = new ModelAndView();
	}

}
