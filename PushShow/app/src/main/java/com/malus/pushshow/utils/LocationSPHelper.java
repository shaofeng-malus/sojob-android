package com.malus.pushshow.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 所有与定位相关的SharePreference
 */
public class LocationSPHelper {
	private static SharedPreferences sp;
	private static String SP_NAME = "weidu";
	private final static double EARTH_RADIUS = 6378137.0;

	public static void initSPHelper(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context must not null");
		} else {
			sp = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
		}
	}

	// 获取距离 单位（m）
	public static double getDsitinct(String lat,String lng) {
		
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		String lat_a = sp.getString("latitude", "0");
		String lng_a = sp.getString("longitude", "0");
		
		return gps2m(Double.parseDouble(lat_a),Double.parseDouble(lng_a),Double.parseDouble(lat),Double.parseDouble(lng));
	}
	// 获取当前纬度
	public static String getLat() {
		
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getString("latitude", "0");
	}
	// 获取当前经度
	public static String getLng() {
		
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getString("longitude", "0");
	}

	
	
	private static double gps2m(double lat_a, double lng_a, double lat_b, double lng_b) {
		double radLat1 = (lat_a * Math.PI / 180.0);
		double radLat2 = (lat_b * Math.PI / 180.0);
		double a = radLat1 - radLat2;
		double b = (lng_a - lng_b) * Math.PI / 180.0;
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
