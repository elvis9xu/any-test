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
 * @version $Id: ConstructCallA.java, v 0.1 2014-2-10 下午12:40:28 elvis.xu Exp $
 */
public class ConstructCallA {

    public ConstructCallA(String name) {
        this(new File(name));
        System.out.println("ConstructCallA(String name)");
    }
    
    public ConstructCallA(File file) {
        System.out.println("ConstructCallA(File file)");
    }
}
