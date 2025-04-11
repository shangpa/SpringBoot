package com.Lect.week03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ImportResource({"classpath:static/xml/Ex3.xml","classpath:static/xml/Ex4.xml"})
public class CollectionDIController {
    @Autowired
    private WebApplicationContext context;

    @GetMapping("/list")
    public ModelAndView useList(ModelAndView mav){
        AnimalService animalService = (AnimalService) context.getBean("animalService");
        mav.addObject("animalService", animalService);
        mav.setViewName("week03/listDIView");
        return mav;
    }
    @GetMapping("/map")
    public ModelAndView useMap(ModelAndView mav){
        AnimalService animalService = (AnimalService) context.getBean("animalServiceMap");
        mav.addObject("animalMap", animalService.getAnimalMap());
        mav.setViewName("week03/mapDIView");
        return mav;
    }
}
