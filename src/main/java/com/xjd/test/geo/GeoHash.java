package com.xjd.test.geo;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class GeoHash {
    public static void main(String[] args) {
        double lng = 121.43960190000007D;
        double lat = 31.1932993D;
//        byte[] bs = encodePoint(lng, lat, 5);
//        System.out.println(Arrays.toString(bs));
        System.out.println(Arrays.toString(encodeLongitude(lng, 15)));
        System.out.println(Arrays.toString(encodeLatitude(lat, 15)));
        System.out.println(Arrays.toString(encodePoint(lng, lat, 30)));
        System.out.println(base32Point(lng, lat, 6));
        System.out.println(Byte.parseByte("1"));
    }

    public static String[] getNearBlock(String block, String[] nearParam) {
        if (nearParam == null || nearParam.length == 0) return new String[0];
        byte[] bytes = base32ToByte(block);



        return null;
    }

    public static byte[] base32ToByte(String block) {
        int len = block.length();
        byte[] cds = new byte[len * 5];
        for (int i = 0; i < len; i++) {
            int idx = codeToIndex(block.charAt(i));
            String bitStr = Integer.toString(idx, 2);
            for (int j = 0, bitLen = bitStr.length(); j < 5; j++) {
                if (j - (5 - bitLen) < 0) {
                    cds[i * 5 + j] = (byte) 0;
                } else {
                    cds[i * 5 + j] = Byte.parseByte(bitStr.charAt(j - (5 - bitLen)) + "");
                }
            }
        }
        return cds;
    }

    public static int codeToIndex(char c) {
        for (int i = 0; i < codes.length; i++) {
            if (c == codes[i]) return i;
        }
        throw new IllegalArgumentException("Illegal Character '" + c + "'");
    }

    public static char[] codes = "0123456789bcdefghjkmnpqrstuvwxyz".toCharArray();

    public static String base32Point(double lng, double lat, int len) {
        byte[] bs = encodePoint(lng, lat, len * 5);
        StringBuilder codeBuf = new StringBuilder(len);
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < bs.length; i++) {
            sb.append(bs[i]);
            if ((i + 1) % 5 == 0) {
                codeBuf.append(codes[Integer.parseInt(sb.toString(), 2)]);
                sb.delete(0, 5);
            }
        }
        return codeBuf.toString();
    }

    public static byte[] encodePoint(double lng, double lat, int bitLen) {
        byte[] lngCode = encodeLongitude(lng, bitLen / 2 + (bitLen % 2 == 0 ? 0 : 1));
        byte[] latCode = encodeLatitude(lat, bitLen / 2);
        byte[] code = new byte[lngCode.length + latCode.length];
        for (int i = 0; i < latCode.length; i++) {
            code[i * 2] = lngCode[i];
            code[i * 2 + 1] = latCode[i];
        }
        if (code.length % 2 > 0) {
            code[code.length - 1] = lngCode[lngCode.length - 1];
        }
        return code;
    }

    public static byte[] encodeLatitude(double value, int bitLen) {
        return encode(value, bitLen, new double[]{-90D, 90D});
    }

    public static byte[] encodeLongitude(double value, int bitLen) {
        return encode(value, bitLen, new double[]{-180D, 180D});
    }

    public static byte[] encode(double value, int bitLen, double[] range) {
        byte[] bs = new byte[bitLen];
        for (int i = 0; i < bitLen; i++) {
            bs[i] = encode(value, range);
        }
        return bs;
    }

    public static byte encode(double value, double[] range) {
        double middle = (range[0] + range[1]) / 2.0d;
        if (value > middle) {
            range[0] = middle;
            return (byte) 1;
        } else {
            range[1] = middle;
            return (byte) 0;
        }
    }

}
