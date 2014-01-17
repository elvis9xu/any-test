package com.xjd.test.enumtest;

public class EnumTest {

    public static void main(String[] args) {
        System.out.println(EmptyEnum.valueOf("B"));
        System.out.println(NumberEnum.valueOf(1));
        System.out.println(NumberEnum.valueOf("Number2"));
        
        System.out.println(CodeEnum.valueOf(1));
        System.out.println(CodeEnum.valueOf("CodeEnum2"));
    }

    
    private int i = 0;
    private String name = "ABC";
    
}
