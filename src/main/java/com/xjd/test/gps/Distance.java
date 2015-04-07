package com.xjd.test.gps;

public class Distance {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		new Distance().DistanceOfTwoPoints(31.28050229903,121.46910186169,31.27806,121.461893);

	}

	public static final double EARTH_RADIUS = 6370996.81;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public void DistanceOfTwoPoints(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		double ss = s * 1.0936132983377;
		System.out.println("两点间的距离是：" + s + "米" + "," + (int) ss + "码");
	}

}
