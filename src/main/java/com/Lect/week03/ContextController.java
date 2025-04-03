package com.Lect.week03;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
	//3주차 실습
	@GetMapping("/createBean")
	public ModelAndView beanTest() {
		//컨텍스트 초기화
		xmlContext.setConfigLocation("classpath:static/xml/Ex1.xml");
		xmlContext.refresh();
		SmsSender xmlSms = (SmsSender)xmlContext.getBean("xmlSms");
		
		//java Config 파일 등록
		configContext.register(AppConfig.class);
		configContext.refresh();
		SmsSender configSms = (SmsSender)configContext.getBean("configSms");
		
		mav.setViewName("week03/beanView");//생성자에 view 이름을 지정하지 않은 경우
		mav.addObject("xmlSms", xmlSms);//모델 객체를 전달
		mav.addObject("configSms", configSms); // 모델 객체를 전달
		return mav;
	}

}
