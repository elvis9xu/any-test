package com.xjd.test.bean.example;

import com.xjd.test.bean.GuardGroup;

public class TestBean extends BaseBean {

	private String prop1 = "prop";
	private String prop2 = "prop";
	@GuardGroup("A")
	private String propA = "prop";
	@GuardGroup("B")
	private String propB = "prop";
	@GuardGroup({ "A", "B" })
	private String propAB = "prop";
	@GuardGroup({ "C" })
	private String propC = "prop";
	@GuardGroup({ "A", "B", "C" })
	private String propABC = "prop";

	public String getProp1() {
		return prop1;
	}

	public void setProp1(String prop1) {
		this.prop1 = prop1;
	}

	public String getProp2() {
		return prop2;
	}

	public void setProp2(String prop2) {
		this.prop2 = prop2;
	}

	public String getPropA() {
		return propA;
	}

	public void setPropA(String propA) {
		this.propA = propA;
	}

	public String getPropB() {
		return propB;
	}

	public void setPropB(String propB) {
		this.propB = propB;
	}

	public String getPropAB() {
		return propAB;
	}

	public void setPropAB(String propAB) {
		this.propAB = propAB;
	}

	public String getPropC() {
		return propC;
	}

	public void setPropC(String propC) {
		this.propC = propC;
	}

	public String getPropABC() {
		return propABC;
	}

	public void setPropABC(String propABC) {
		this.propABC = propABC;
	}

}
