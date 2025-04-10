package com.Lect.week03;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AnimalAutoDI {
    @Autowired //byName 방식으로 주입
    private Animal cat;

    private SmsSender sms;
    public AnimalAutoDI(@Qualifier("xmlSms") SmsSender sms){// byConstructor 방식으로 주입
        this.sms = sms;
    }
    
    private Animal dog;
    @Autowired
    public void setAnimal(@Qualifier("dog") Animal dog){ // byType 방식으로 주입
        this.dog = dog;
    }
}
