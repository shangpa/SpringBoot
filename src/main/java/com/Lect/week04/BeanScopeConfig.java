package com.Lect.week04;

import com.Lect.week03.SmsSender;
import com.Lect.week03.WorkUnit;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Configuration
public class BeanScopeConfig {
    @Bean(name="scopeBean0")// singleton 기본값
    public SmsSender singletonBean(){
        return new SmsSender();
    }

    @Bean(name = "scopeBean1")
    @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
    //@Scope("prototype") 또는 "singleton","request","session" 등
    public SmsSender prototypeBean() {
        return new SmsSender();
    }

    @Bean(name = "scopeBean2")
    @RequestScope
    public SmsSender requestBean() {
        return new SmsSender();
    }
    @Bean(name = "scopeBean3")
    @SessionScope
    public SmsSender sessionBean() {
        return new SmsSender();
    }

    @Autowired
    private WorkUnit[] prototypeBean;
    @Bean
    public WorkUnit[] useDifferentScope(){
        return prototypeBean;
    }

    @Autowired
    private ObjectFactory<WorkUnit[]> prototypeFactory;
    @Bean
    public ObjectFactory<WorkUnit[]> objectFactoryBean() {
        return prototypeFactory;
    }
}
