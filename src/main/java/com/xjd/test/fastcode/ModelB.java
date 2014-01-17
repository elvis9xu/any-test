package com.xjd.test.fastcode;

import com.xjd.test.fastcode.ModelA;
import java.util.List;
import java.util.ArrayList;

/**
 * <pre>
 * TODO
 * </pre>
 * @author elvis.xu
 * @since Jan 7, 2014 4:16:43 PM
 */
public class ModelB {
	private String name;
	private int age;
	private String address;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * This method copies fields from ModelA to ModelB
	 * @param modelA
	 * @param modelB
	 * 
	 */
	public void copyModelAToModelB(final ModelA modelA, final ModelB modelB) {
		modelB.setName(modelA.getName());
		modelB.setAge(modelA.getAge());
		modelB.setAddress(modelA.getAddress());
	}
}
