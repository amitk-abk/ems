package com.target.ms.ems.bean;

import org.springframework.stereotype.Component;

@Component
public class CodeGenerator {

	private int counter = 10;

	public String getCodeFor(String type) {
		long currTime = System.currentTimeMillis();
		type = type + currTime + counter++;
		if (counter >= 99) {
			counter = 10;
		}
		return type;
	}	
	
}
