package com.Lect.week03;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //환경 설정
public class AppConfig {
	@Bean
	public SmsSender configSms() {
		return new SmsSender();
	}
}