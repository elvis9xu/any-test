package com.xjd.test.geo;

public class GeoHashUtils {

    static byte encode(double value, double[] range) {
        double middle = (range[0] + range[1]) / 2.0d;
        if (value > middle) {
            range[0] = middle;
            return (byte) 1;
        } else {
            range[1] = middle;
            return (byte) 0;
        }
    }

    static String encode(double value, int bitLen, double[] range) {
        StringBuilder buf = new StringBuilder(bitLen);
        for (int i = 0; i < bitLen; i++) {
            buf.append(encode(value, range));
        }
        return buf.toString();
    }

    public static String encodeLongitude(double value, int bitLen) {
        return encode(value, bitLen, new double[]{-180D, 180D});
    }

    public static String encodeLatitude(double value, int bitLen) {
        return encode(value, bitLen, new double[]{-90D, 90D});
    }

    public static String encodePoint(double lng, double lat, int bitLen) {
        String lngBit = encodeLongitude(lng, bitLen / 2 + (bitLen % 2 == 0 ? 0 : 1));
        String latBit = encodeLatitude(lat, bitLen / 2);
        StringBuilder buf = new StringBuilder(lngBit.length() + latBit.length());
        for (int i = 0, len = latBit.length(); i < len; i++) {
            buf.append(lngBit.charAt(i)).append(latBit.charAt(i));
        }
        if (lngBit.length() % 2 > 0) {
            buf.append(lngBit.charAt(lngBit.length() - 1));
        }
        return buf.toString();
    }
}
