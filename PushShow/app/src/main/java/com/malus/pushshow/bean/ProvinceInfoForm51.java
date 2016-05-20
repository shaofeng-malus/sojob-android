package com.malus.pushshow.bean;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/********************
 * 作者：malus
 * 日期：16/3/10
 * 时间：上午10:54
 * 注释：
 ********************/
public class ProvinceInfoForm51 {
    private String result;
    private List<ProvinceItemForm51> resultbody;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<ProvinceItemForm51> getResultbody() {
        return resultbody;
    }

    public void setResultbody(List<ProvinceItemForm51> resultbody) {
        this.resultbody = resultbody;
    }

    @Override
    public String toString() {
        return "CityInfoForm51{" +
                "result='" + result + '\'' +
                ", resultbody=" + resultbody +
                '}';
    }

    public ProvinceInfoForm51 parse(InputStream is){
        XmlPullParser parser = Xml.newPullParser();
        ProvinceItemForm51 item = null;
        this.resultbody = new ArrayList<>();
        try {
            parser.setInput(is, "UTF-8");
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        break;
                    case XmlPullParser.START_TAG:

                        if (parser.getName().equals("result")) {
                            eventType = parser.next();
                            this.result = parser.getText();
                        } else if (parser.getName().equals("item")) {
                            item = new ProvinceItemForm51();
                        } else if (parser.getName().equals("code")) {
                            if (item != null) {
                                eventType = parser.next();
                                item.setCode(parser.getText());
                            }
                        } else if (parser.getName().equals("hassub")) {
                            if (item != null) {
                                eventType = parser.next();
                                item.setHassub(parser.getText());
                            }
                        } else if (parser.getName().equals("value")) {
                            if (item != null) {
                                eventType = parser.next();
                                item.setValue(parser.getText());
                            }
                        } else if (parser.getName().equals("ishot")) {
                            if (item != null) {
                                eventType = parser.next();
                                item.setIshot(parser.getText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            if(item!=null){
                                resultbody.add(item);
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return this;
    }

}
