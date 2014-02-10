/**
 * 
 *汇付天下有限公司
 * Copyright (c) 2006-2014 ChinaPnR,Inc.All Rights Reserved.
 */
package com.xjd.test.grammar;

import java.io.File;

/**
 * 
 * @author elvis.xu
 * @version $Id: ConstructCallB.java, v 0.1 2014-2-10 下午12:42:04 elvis.xu Exp $
 */
public class ConstructCallB extends ConstructCallA {

    /**
     * @param file
     */
    public ConstructCallB(File file) {
        super(file);
        System.out.println("ConstructCallB(File file)");
    }

    /**
     * @param name
     */
    public ConstructCallB(String name) {
        super(name);
        System.out.println("ConstructCallB(String name)");
    }
    
    public static void main(String[] args) {
        new ConstructCallB("HELLO");
        //执行结果为:
//        ConstructCallA(File file)
//        ConstructCallA(String name)
//        ConstructCallB(String name)
    }

}
