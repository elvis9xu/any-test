package com.xjd.test.jfinal.active_record.gen.bean.base;

import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by JFinal, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseTest<M extends BaseTest<M>> extends Model<M> implements IBean {

	public void setId(java.lang.Long id) {
		set("id", id);
	}
	
	public java.lang.Long getId() {
		return getLong("id");
	}

	public void setName(java.lang.String name) {
		set("name", name);
	}
	
	public java.lang.String getName() {
		return getStr("name");
	}

	public void setMobile(java.lang.String mobile) {
		set("mobile", mobile);
	}
	
	public java.lang.String getMobile() {
		return getStr("mobile");
	}

	public void setMail(java.lang.String mail) {
		set("mail", mail);
	}
	
	public java.lang.String getMail() {
		return getStr("mail");
	}

	public void setAge(java.lang.Integer age) {
		set("age", age);
	}
	
	public java.lang.Integer getAge() {
		return getInt("age");
	}

	public void setNum(java.lang.Integer num) {
		set("num", num);
	}
	
	public java.lang.Integer getNum() {
		return getInt("num");
	}

}
