package com.malus.pushshow.bean.job51;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;

/********************
 * 作者：malus
 * 日期：16/4/1
 * 时间：下午8:44
 * 注释：
 ********************/
public class CompDetailInfo {
    private String result;
    private CompDetailBody resultbody;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CompDetailBody getResultbody() {
        return resultbody;
    }

    public void setResultbody(CompDetailBody resultbody) {
        this.resultbody = resultbody;
    }

    @Override
    public String toString() {
        return "CompDetailInfo{" +
                "result='" + result + '\'' +
                ", resultbody=" + resultbody +
                '}';
    }


    public CompDetailInfo parse(InputStream is){
        XmlPullParser parser = Xml.newPullParser();
        this.resultbody = new CompDetailBody();
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
                        } else if (parser.getName().equals("poster")) {
                            eventType = parser.next();
                            resultbody.setPoster(parser.getText());
                        } else if (parser.getName().equals("coid")) {
                            eventType = parser.next();
                            resultbody.setCoid(parser.getText());
                        } else if (parser.getName().equals("coname")) {
                            eventType = parser.next();
                            resultbody.setConame(parser.getText());
                        } else if (parser.getName().equals("cotype")) {
                            eventType = parser.next();
                            resultbody.setCotype(parser.getText());
                        } else if (parser.getName().equals("cosize")) {
                            eventType = parser.next();
                            resultbody.setCosize(parser.getText());
                        } else if (parser.getName().equals("indtype1")) {
                            eventType = parser.next();
                            resultbody.setIndtype1(parser.getText());
                        } else if (parser.getName().equals("indtype2")) {
                            eventType = parser.next();
                            resultbody.setIndtype2(parser.getText());
                        } else if (parser.getName().equals("caddr")) {
                            eventType = parser.next();
                            resultbody.setCaddr(parser.getText());
                        } else if (parser.getName().equals("coinfo")) {
                            eventType = parser.next();
                            resultbody.setCoinfo(parser.getText());
                        } else if (parser.getName().equals("lon")) {
                            eventType = parser.next();
                            resultbody.setLon(parser.getText());
                        } else if (parser.getName().equals("lat")) {
                            eventType = parser.next();
                            resultbody.setLat(parser.getText());
                        } else if (parser.getName().equals("cojumpurl")) {
                            eventType = parser.next();
                            resultbody.setCojumpurl(parser.getText());
                        } else if (parser.getName().equals("logo")) {
                            eventType = parser.next();
                            resultbody.setLogo(parser.getText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
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
