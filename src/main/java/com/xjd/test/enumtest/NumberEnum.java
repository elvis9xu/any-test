package com.xjd.test.enumtest;

public enum NumberEnum {

    Number1(1), Number2(2);
    
    int n = 0;
    NumberEnum(int n) {
        this.n = n;
    }
    
    public static NumberEnum valueOf(int n) {
        for (NumberEnum ne : NumberEnum.values()) {
            if (n == ne.n) {
                return ne;
            }
        }
        return null;
    }
}
