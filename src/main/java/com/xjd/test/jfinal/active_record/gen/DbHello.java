package com.xjd.test.jfinal.active_record.gen;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.plugin.druid.DruidPlugin;
import com.xjd.test.jfinal.active_record.gen.bean.Test;
import com.xjd.test.jfinal.active_record.gen.bean._MappingKit;

public class DbHello {
    public static void main(String[] args) {
        DruidPlugin druidPlugin = new DruidPlugin("jdbc:mysql://119.23.148.103:3306/test", "access", "accessdb2017");
        ActiveRecordPlugin activeRecordPlugin = new ActiveRecordPlugin(druidPlugin);
        druidPlugin.start();
        activeRecordPlugin.start();

        Record record = Db.findById("test", 1);
        System.out.println(record);
    }
}
