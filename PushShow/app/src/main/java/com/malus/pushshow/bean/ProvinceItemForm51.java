package com.malus.pushshow.bean;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.InputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/********************
 * 作者：malus
 * 日期：16/3/10
 * 时间：上午10:54
 * 注释：
 ********************/
public class ProvinceItemForm51 {
    private String code;
    private String hassub;
    private String value;
    private String ishot;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getHassub() {
        return hassub;
    }

    public void setHassub(String hassub) {
        this.hassub = hassub;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getIshot() {
        return ishot;
    }

    public void setIshot(String ishot) {
        this.ishot = ishot;
    }

    @Override
    public String toString() {
        return "CityItemForm51{" +
                "code='" + code + '\'' +
                ", hassub='" + hassub + '\'' +
                ", value='" + value + '\'' +
                ", ishot='" + ishot + '\'' +
                '}';
    }

}
