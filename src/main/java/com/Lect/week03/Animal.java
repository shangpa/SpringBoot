package com.Lect.week03;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

public interface Animal{
    String Sound();
}
@Component
class Dog implements Animal{
    @Override
    public String Sound() {
        return "Woof";
    }
}

@Component
@Primary // 동일한 타입의 빈이 여러개 있을 경우 Cat 을 기본 빈으로 설정
class Cat implements Animal{
    @Override
    public String Sound() {
        return "Meow";
    }
}