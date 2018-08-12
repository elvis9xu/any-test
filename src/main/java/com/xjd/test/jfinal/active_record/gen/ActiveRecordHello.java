package com.xjd.test.jfinal.active_record.gen;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.xjd.test.jfinal.active_record.gen.bean.Test;
import com.xjd.test.jfinal.active_record.gen.bean._MappingKit;

public class ActiveRecordHello {
    public static void main(String[] args) {
        DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://119.23.148.103:3306/test", "access", "accessdb2017");
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        _MappingKit.mapping(activeRecordPlugin);

        druidPlugin.start();
        activeRecordPlugin.start();

        Test test = new Test().dao().findById(1);
        System.out.println(test.toJson());

    }
}
