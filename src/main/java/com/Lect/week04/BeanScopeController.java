package com.Lect.week04;

import com.Lect.week03.SmsSender;
import com.Lect.week03.WorkUnit;
import org.eclipse.jdt.internal.compiler.lookup.ExtraCompilerModifiers;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
                scopeBeanArray[i][j] = (SmsSender) context.getBean("scopeBean" + i);
        }
        mav.addObject("scopeBeanArray", scopeBeanArray);
        mav.setViewName("week04/scopeBeanView");
        return mav;
    }

    @GetMapping({"/useDifferentScope"})
    public ModelAndView differentScope(ModelAndView mav) {
        WorkUnit[][] scopeBeanArray = new WorkUnit[2][1];
        scopeBeanArray[0] = (WorkUnit[]) context.getBean("useDifferentScope");
        scopeBeanArray[1] = (WorkUnit[]) context.getBean("useDifferentScope");
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

    @ResponseBody
    @GetMapping("/post&pre")
    public String customMethod() {
        //객체 소멸을 위하여 AnnotationConfigApplicationContext 타입의 컨테이너
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(InitDestroyUnit.class);
        context.close(); // 닫을때 Destroy-method 실행
        return "Consol 출력 메시지를 확인하세요";
    }

    @GetMapping("/awareInterfaceEx")
    public ModelAndView awareInterfaceEx(ModelAndView mav) {
        AwareInterfaceImp awareInterfaceImp = (AwareInterfaceImp) context.getBean("awareInterfaceImp");
        String[] beanNames = awareInterfaceImp.getContext().getBeanDefinitionNames();
        mav.addObject("beanNames", beanNames);
        mav.setViewName("week04/awareInterfaceView");
        return mav;
    }

    @GetMapping({"/externalConfigEx"})
    public ModelAndView externalConfigEx(ModelAndView mav) {
        ExternalConfigComponent externalConfigComponent = (ExternalConfigComponent) context.getBean("externalConfigComponent");
        mav.addObject("obj", externalConfigComponent);
        mav.setViewName("week04/externalConfigView");
        return mav;
    }
}
