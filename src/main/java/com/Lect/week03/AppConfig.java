package com.Lect.week03;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //환경 설정을 위한 어노테이션
public class AppConfig {
	@Bean
	public SmsSender configSms() {
		return new SmsSender();
	}
	
	@Bean
	public List<String> unit(){
		List<String> list = new ArrayList();
		list.add("문자열1");
		list.add("문자열2");
		return list;
	}
}