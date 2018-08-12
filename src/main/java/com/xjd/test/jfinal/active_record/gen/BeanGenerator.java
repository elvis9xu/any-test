package com.xjd.test.jfinal.active_record.gen;

import com.alibaba.druid.pool.DruidDataSource;
import com.jfinal.plugin.activerecord.generator.Generator;

public class BeanGenerator {
    public static void main(String[] args) {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl("jdbc:mysql://119.23.148.103:3306/test");
        ds.setUsername("access");
        ds.setPassword("accessdb2017");

        String baseModelPkg = "com.xjd.test.jfinal.active_record.gen.bean.base";
        String baseModelDir = "src/main/java/com/xjd/test/jfinal/active_record/gen/bean/base";

        String modelPkg = "com.xjd.test.jfinal.active_record.gen.bean";
        String modelDir = "src/main/java/com/xjd/test/jfinal/active_record/gen/bean";

        Generator generator = new Generator(ds, baseModelPkg, baseModelDir, modelPkg, modelDir);
        generator.generate();
    }
}
