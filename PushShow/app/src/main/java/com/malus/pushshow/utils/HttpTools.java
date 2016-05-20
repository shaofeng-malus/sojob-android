package com.malus.pushshow.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import okhttp3.Callback;
import okhttp3.Response;


/**
 * <hr/>
 *
 * @author www.TheWk.cn.vc
 */
public abstract class HttpTools {

    static String TAG = "HttpTools";

    static {

    }

    private static final String Encoding = "UTF-8";

    private HttpTools() throws IllegalAccessException {
        throw new IllegalAccessException("工具类无法实例化!");
    }

    public static String post(String path, Map<String, String> params)
            throws CntvException {
        String result = null;
        try {
            Response response = null;
            if (params != null && !params.isEmpty()) {
                response = OkHttpUtils.post().url(path).params(params).build().execute();
            }
            if (response != null && response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    public static String post(String path) throws CntvException {
        try {
            path = path.replaceAll(" ", "");
            String result = null;
            Response response = OkHttpUtils.post().url(path).build().execute();
            if (response != null && response.isSuccessful()) {
                result = response.body().string();
            }
            return result;
        } catch (Exception e) {
            throw new CntvException("服务器连接失败或超时！", e);
        }
    }


    public static void get(String path,HttpCallBack callback) {

        try {
            path = path.replaceAll(" ", "");
            String result = null;
            Response mResponse = OkHttpUtils.get().url(path).build().execute();
            if (mResponse != null) {
                if(mResponse.isSuccessful()){
                    result = mResponse.body().string();
                    if(callback!=null){
                        callback.onSuccess(result);
                    }
                    Logs.d(TAG,"result success");
                }else{
                    if(callback!=null){
                        callback.onFailure(mResponse.code(),mResponse.message());
                    }
                    Logs.d(TAG,"errpr code:"+mResponse.code()+"  message:"+mResponse.message());
                }
            }else{
                if(callback!=null){
                    callback.onFailure(0,"服务器连接失败或超时！");
                }
                Logs.d(TAG,"服务器连接失败或超时！");
            }
        } catch (Exception e) {
            if(callback!=null){
                callback.onFailure(0,"服务器连接失败或超时！");
            }
            Logs.d(TAG,"服务器连接失败或超时！");
        }
    }

    public static void getInputStream(String path,HttpCallBack callback) {

        try {
            path = path.replaceAll(" ", "");
            InputStream result = null;
            Response mResponse = OkHttpUtils.get().url(path).build().execute();
            if (mResponse != null) {
                if(mResponse.isSuccessful()){
                    result = mResponse.body().byteStream();
                    if(callback!=null){
                        callback.onSuccess(result);
                    }
                    Logs.d(TAG,"result success");
                }else{
                    if(callback!=null){
                        callback.onFailure(mResponse.code(),mResponse.message());
                    }
                    Logs.d(TAG,"errpr code:"+mResponse.code()+"  message:"+mResponse.message());
                }
            }else{
                if(callback!=null){
                    callback.onFailure(0,"服务器连接失败或超时！");
                }
                Logs.d(TAG,"服务器连接失败或超时！");
            }
        } catch (Exception e) {
            if(callback!=null){
                callback.onFailure(0,"服务器连接失败或超时！");
            }
            Logs.d(TAG,"服务器连接失败或超时！");
        }
    }

    /**
     * @param url 传入的url,包括了查询参数
     * @return 返回get后的数据
     */
    public static String sendGet(String url) {
        String result = "";
        // String
        URL realURL = null;
        URLConnection conn = null;
        BufferedReader bufReader = null;
        String line = "";
        try {
            realURL = new URL(url);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Logs.e("HttpTools", "url 格式错误");
        }
        try {
            conn = realURL.openConnection();
            // 设置连接参数...conn.setRequestProperty("xx", "xx");
            conn.setConnectTimeout(10000); // 10s timeout
            // conn.setRequestProperty("accept", "*/*");
            // conn.setRequestProperty("", "");
            conn.connect(); // 连接就把参数送出去了 get方法
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Logs.e("HttpTools", "连接错误");
        }
        try {
            bufReader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "gbk"));
            while ((line = bufReader.readLine()) != null) {
                result += line + "\n";
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            Logs.e("HttpTools", "读取数据错误");
        } finally {
            // 释放资源
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static long getTime(String path) throws CntvException, ParseException {
        try {
            long result = 0;
            String value = null;
            Response mResponse = OkHttpUtils.post().url(path).build().execute();
            if (mResponse != null && mResponse.isSuccessful()) {
                value = mResponse.header("date");
                if (!TextUtils.isEmpty(value)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.UK);
                    Date date = sdf.parse(value);
                    result = date.getTime() / 1000;
                }
            }
            Logs.e("postTime", result + "");
            return result;
        } catch (IOException e) {
            throw new CntvException("服务器连接失败或超时！", e);
        }
    }

    public static String getVarientPlaylist(String mainUrl, String subUrl) {
        if (mainUrl == null || "".equals((mainUrl = mainUrl.trim()))) {
            return null;
        }

        if (!mainUrl.startsWith("http://") && !mainUrl.startsWith("https://")) {
            return null;
        }

        String protocol = mainUrl.substring(0, mainUrl.indexOf("/") - 1);
        String strtoken = mainUrl.substring(mainUrl.indexOf("/") + 2);
        String domainName = "";

        int domainIdx = strtoken.indexOf("/");
        if (domainIdx != -1) {

            domainName = strtoken.substring(0, domainIdx);
        } else
            return null;


        String realUrl = "";
        if (subUrl.startsWith("http://") || subUrl.startsWith("https://")) {
            //contain whole real url
            realUrl = subUrl;
        } else if (subUrl.startsWith("/")) {
            //domain + subUrl;
            String domainUrl = protocol + "://" + domainName;
            realUrl = domainUrl + subUrl;
        } else {
            //mainUrl + subUrl;
            int index = mainUrl.lastIndexOf("/");
            realUrl = mainUrl.substring(0, index) + "/" + subUrl;
        }
        return realUrl;
    }

    public interface HttpCallBack{
        void onSuccess(String result);
        void onSuccess(InputStream result);
        void onFailure(int code,String msg);
    }
}
