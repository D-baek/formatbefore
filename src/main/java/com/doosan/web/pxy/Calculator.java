package com.doosan.web.pxy;

import org.springframework.stereotype.Component;

@Component
public class Calculator {
	public int sum(int a, int b) {
		return a + b;
	}
	public int sub(int a, int b) {
		return a - b;
	}
	public int abs(int a, int b) {
		return Math.abs(a);
	}
}
