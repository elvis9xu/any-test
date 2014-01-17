package com.xjd.test.enumtest;

public enum CodeEnum {
    CodeEnum1(1, "January"), CodeEnum2(2, "Feb");

    int    code;
    String name;

    CodeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }
    
    public int getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return "CodeEnum[code=" + code + ", name=" + name + "]";
    }
    
    public static CodeEnum valueOf(int code) {
        for (CodeEnum ce : CodeEnum.values()) {
            if (ce.code == code) {
                return ce;
            }
        }
        return null;
    }
}
