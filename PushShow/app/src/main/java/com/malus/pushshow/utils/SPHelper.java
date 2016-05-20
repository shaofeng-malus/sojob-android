package com.malus.pushshow.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 所有与sharePreferences类相关的操作均放在该类
 */
public class SPHelper {
	private static SharedPreferences sp;
	public static void initSPHelper(Context context) {
		if (context == null) {
			throw new IllegalArgumentException("context must not null");
		} else {
			sp = context.getSharedPreferences("user", Context.MODE_PRIVATE);
		}
	}

	public static void setJobInfoFormZL(String jobInfoFormZL) {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("jobInfoFormZL", jobInfoFormZL);
		editor.apply();
	}

	public static String getJobInfoFormZL() {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getString("jobInfoFormZL","");
	}
	public static void setJobInfoForm51(String jobInfoForm51) {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("jobInfoForm51", jobInfoForm51);
		editor.apply();
	}

	public static String getJobInfoForm51() {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getString("jobInfoForm51","");
	}

	public static void setSearchKey(String searchKey) {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("searchKey", searchKey);
		editor.apply();
	}

	public static String getSearchKey() {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getString("searchKey","");
	}

	public static void setCity(String city) {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("cityValue", city);
		editor.apply();
	}

	public static String getCity() {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getString("cityValue","");
	}

	public static void setCityCode(String cityCode) {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("cityCode", cityCode);
		editor.apply();
	}

	public static String getCityCode() {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getString("cityCode","");
	}
	public static void setLaunch() {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean("hasLaunch", true);
		editor.apply();
	}

	public static boolean hasLaunch() {
		if (sp == null) {
			throw new IllegalArgumentException(
					"you must initSPHelper before use it");
		}
		return sp.getBoolean("hasLaunch",false);
	}
}
