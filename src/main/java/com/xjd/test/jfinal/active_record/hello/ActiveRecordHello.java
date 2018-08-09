package com.xjd.test.jfinal.active_record.hello;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.xjd.utils.basic.JsonUtils;

public class ActiveRecordHello {
    public static void main(String[] args) {
        DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://119.23.148.103:3306/test", "access", "accessdb2017");
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        activeRecordPlugin.addMapping("test", "id", Test.class);

        druidPlugin.start();
        activeRecordPlugin.start();

        Test test = Test.dao.findById(1);
        System.out.println(test.toJson());

    }
}
