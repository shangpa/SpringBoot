package com.Lect.week04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class AwareInterfaceImp implements BeanNameAware, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("setApplicationContext() 메서드 실행");
        this.context = applicationContext;
        System.out.println(" 컨테이너 : "+ this.context.getId());
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("setBeanName() 메서드 실행");
        System.out.println("해당 빈 이름 : "+name);
        System.out.println("================================================");
    }

    public ApplicationContext getContext() {
        return context;
    }
}
