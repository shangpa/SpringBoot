package com.Lect.week03;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HardWorkUnit {

	@Autowired
	@Qualifier("configSms")
	private SmsSender autoSms;
	private WorkUnit workUnit;
	private String msg;
	
	@Autowired
	public HardWorkUnit(WorkUnit workUnit) {
		super();
		this.workUnit = workUnit;
	}
	
	public SmsSender getAutoSms() {
		return autoSms;
	}
	public void setAutoSms(SmsSender autoSms) {
		this.autoSms = autoSms;
	}
	public WorkUnit getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(WorkUnit workUnit) {
		this.workUnit = workUnit;
	}
	public String getMsg() {
		return msg;
	}
	@Autowired
	@Value("${message.greeting}")
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
