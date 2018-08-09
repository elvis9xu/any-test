package com.xjd.test.mysql;

public class Ip2Long {
    public static void main(String[] args) {
        String ip = "192.168.1.1";
        long ipLong = ip2Long(ip);
        System.out.println(ipLong);
        System.out.println(long2Ip(ipLong));
    }

    public static long ip2Long(String ipString) {
        String[] ips = ipString.split("\\.");
        long rt = 0;
        for (int i = 0; i < 4; i++) {
            long ip = Long.parseLong(ips[i]);
            rt |= ip << ((3 - i) * 8);
        }
        return rt;
    }

    public static String long2Ip(long ipLong) {
        StringBuilder sb = new StringBuilder(15);
        for (int i = 3; i >= 0; i--) {
            long ip = ipLong >> (i * 8) & 0xFF;
            sb.append(ip);
            if (i > 0) {
                sb.append('.');
            }
        }
        return sb.toString();
    }
}
