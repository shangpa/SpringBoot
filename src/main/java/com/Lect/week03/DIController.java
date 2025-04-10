package com.Lect.week03;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@ImportResource("classpath:static/xml/Ex2.xml")
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
	private final List<String> myList;
	@GetMapping("/lombok")
	public ModelAndView useLombok(ModelAndView mav) {
		mav.addObject("obj",myList);
		mav.setViewName("week03/lombokDIView");
		return mav;
	}

	@GetMapping("/xmlDI")
	public ModelAndView useXml(ModelAndView mav) {
		LombokService service = (LombokService)context.getBean("service");
		mav.addObject("obj",service);
		mav.setViewName("week03/xmlDIView");
		return mav;
	}

	@GetMapping("/autoDI")
	public ModelAndView useAuto(ModelAndView mav) {
		AnimalAutoDI service = (AnimalAutoDI)context.getBean("animalAutoDI");
		mav.addObject("obj",service);
		mav.setViewName("week03/autoDIView");
		return mav;
	}

	@GetMapping("/commonDI")
	public ModelAndView useCommon(ModelAndView mav) {
		CommonService service = (CommonService)context.getBean("commonService");
		service.setDefaultValue(30);
		service.setPeriodTime(20);
		mav.addObject("obj",service);
		mav.setViewName("week03/commonDIView");
		return mav;
	}
}
