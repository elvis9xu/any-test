package com.xjd.test.jfinal.active_record.hello;

import com.jfinal.plugin.activerecord.Model;

public class Test extends Model<Test> {
    public static final Test dao = new Test().dao();

}
