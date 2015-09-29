package com.xjd.test.bean.example;

import com.xjd.test.bean.GuardGroup;

public class BaseBean {
	private String base1 = "base";
	@GuardGroup("A")
	private String baseA = "base";

	public String getBase1() {
		return base1;
	}

	public void setBase1(String base1) {
		this.base1 = base1;
	}

	public String getBaseA() {
		return baseA;
	}

	public void setBaseA(String baseA) {
		this.baseA = baseA;
	}

}
