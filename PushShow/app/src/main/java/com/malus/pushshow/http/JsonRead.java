package com.malus.pushshow.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.malus.pushshow.bean.job51.JobInfoForm51;
import com.malus.pushshow.bean.JobInfoFormZL;

/********************
 * 作者：malus
 * 日期：16/2/23
 * 时间：下午2:03
 * 注释：
 ********************/
public class JsonRead {
    public static JobInfoFormZL getJobInfoFormZL(String json) {

        Gson gson = new Gson();

        JobInfoFormZL result = null;
        try {
            result = gson.fromJson(json, new TypeToken<JobInfoFormZL>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public static JobInfoForm51 getJobInfoForm51(String json) {

        Gson gson = new Gson();

        JobInfoForm51 result = null;
        try {
            result = gson.fromJson(json, new TypeToken<JobInfoForm51>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
