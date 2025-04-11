package com.Lect.week04;

import com.Lect.week03.SmsSender;
import com.Lect.week03.WorkUnit;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BeanScopeController {
    @Autowired
    private WebApplicationContext context;//자동으로 주입

    @GetMapping("/scopeBean")
    public ModelAndView scopeBean(ModelAndView mav) {
        SmsSender[][] scopeBeanArray = new SmsSender[4][2];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 2; j++)
                scopeBeanArray[i][j] = (SmsSender) context.getBean("scopeBean"+i);
        }
        mav.addObject("scopeBeanArray", scopeBeanArray);
        mav.setViewName("week04/scopeBeanView");
        return mav;
    }

    @GetMapping({"/useDifferentScope"})
    public ModelAndView differentScope(ModelAndView mav) {
        WorkUnit[][] scopeBeanArray =new WorkUnit[2][1];
        scopeBeanArray[0]= (WorkUnit[])context.getBean("useDifferentScope");
        scopeBeanArray[1]= (WorkUnit[])context.getBean("useDifferentScope");
        mav.addObject("scopeBeanArray", scopeBeanArray);
        mav.setViewName("week04/differentScopeView");
        return mav;
    }

    @GetMapping("/objectFactoryBeanTest")
    public ModelAndView objectFactoryTest(ModelAndView mav) {
        ObjectFactory<WorkUnit[]> prototyeBeanFactory;
        WorkUnit[][] scopeBeanArray = new WorkUnit[2][1];

        prototyeBeanFactory = (ObjectFactory<WorkUnit[]>) context.getBean("objectFactoryBean");
        scopeBeanArray[0] = prototyeBeanFactory.getObject();

        prototyeBeanFactory = (ObjectFactory<WorkUnit[]>) context.getBean("objectFactoryBean");
        scopeBeanArray[1] = prototyeBeanFactory.getObject();

        mav.addObject("scopeBeanArray", scopeBeanArray);
        mav.setViewName("week04/differentScopeView");
        return mav;
    }
}
