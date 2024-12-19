package com.softgv.cda.util;

public class MyUtil {
	
	public static int getOTP() {
		double otp = 0;
		while (otp < 1000) {
			double random = Math.random();
			otp = random * 10000;
		}
		return (int) otp;
	}
	
	
}
