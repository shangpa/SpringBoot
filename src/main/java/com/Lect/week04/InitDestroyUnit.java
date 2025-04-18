package com.Lect.week04;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;

public class InitDestroyUnit implements InitializingBean, DisposableBean {
    // 빈이 생성될 때 init 매서드 실행, 소멸될때 cleanup 매서드 실행
    @Bean(initMethod ="init", destroyMethod = "cleanup")
    public InitDestroyUnit myBean(){
        return new InitDestroyUnit();
    }
    public void init(){
        System.out.println("InitDestroyUnit 초기화(init) 메서드 실행");
    }
    public void cleanup(){
        System.out.println("InitDestroyUnit 소멸(cleanup) 메서드 실행");
    }

    @PostConstruct //빈 객체가 생성될 때 실행
    public void postConstruct(){
        System.out.println("InitDestroyUnit 초기화(@PostConstruct) 메서드 실행");

    }
    @PreDestroy//빈 객체가 소멸될 때 실행
    public void preDestroy(){
        System.out.println("InitDestroyUnit 소멸(@preDestroy) 메서드 실행");
    }

    // implement 구현
    @Override
    public void destroy() throws Exception {
        System.out.println("destroy() 메서드 실행");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet() 메서드 실행");
    }
}
