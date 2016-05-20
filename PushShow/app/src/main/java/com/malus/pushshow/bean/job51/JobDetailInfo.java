package com.malus.pushshow.bean.job51;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/********************
 * 作者：malus
 * 日期：16/4/1
 * 时间：下午5:01
 * 注释：
 ********************/
public class JobDetailInfo {
    private String result;
    private JobDetailBody resultbody;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JobDetailBody getResultbody() {
        return resultbody;
    }

    public void setResultbody(JobDetailBody resultbody) {
        this.resultbody = resultbody;
    }

    @Override
    public String toString() {
        return "JobDetailInfo{" +
                "result='" + result + '\'' +
                ", resultbody=" + resultbody +
                '}';
    }

    public JobDetailInfo parse(InputStream is){
        XmlPullParser parser = Xml.newPullParser();
        this.resultbody = new JobDetailBody();
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
                        } else if (parser.getName().equals("jobid")) {
                            eventType = parser.next();
                            resultbody.setJobid(parser.getText());
                        } else if (parser.getName().equals("jobname")) {
                            eventType = parser.next();
                            resultbody.setJobname(parser.getText());
                        } else if (parser.getName().equals("coid")) {
                            eventType = parser.next();
                            resultbody.setCoid(parser.getText());
                        } else if (parser.getName().equals("coname")) {
                            eventType = parser.next();
                            resultbody.setConame(parser.getText());
                        } else if (parser.getName().equals("issuedate")) {
                            eventType = parser.next();
                            resultbody.setIssuedate(parser.getText());
                        } else if (parser.getName().equals("jobarea")) {
                            eventType = parser.next();
                            resultbody.setJobarea(parser.getText());
                        } else if (parser.getName().equals("jobnum")) {
                            eventType = parser.next();
                            resultbody.setJobnum(parser.getText());
                        } else if (parser.getName().equals("workyear")) {
                            eventType = parser.next();
                            resultbody.setWorkyear(parser.getText());
                        } else if (parser.getName().equals("degree")) {
                            eventType = parser.next();
                            resultbody.setDegree(parser.getText());
                        } else if (parser.getName().equals("address")) {
                            eventType = parser.next();
                            resultbody.setAddress(parser.getText());
                        } else if (parser.getName().equals("joblon")) {
                            eventType = parser.next();
                            resultbody.setJoblon(parser.getText());
                        } else if (parser.getName().equals("joblat")) {
                            eventType = parser.next();
                            resultbody.setJoblat(parser.getText());
                        } else if (parser.getName().equals("welfare")) {
                            eventType = parser.next();
                            resultbody.setWelfare(parser.getText());
                        } else if (parser.getName().equals("jobtag")) {
                            eventType = parser.next();
                            resultbody.setJobtag(parser.getText());
                        } else if (parser.getName().equals("providesalary")) {
                            eventType = parser.next();
                            resultbody.setProvidesalary(parser.getText());
                        } else if (parser.getName().equals("language1")) {
                            eventType = parser.next();
                            resultbody.setLanguage1(parser.getText());
                        } else if (parser.getName().equals("language2")) {
                            eventType = parser.next();
                            resultbody.setLanguage2(parser.getText());
                        } else if (parser.getName().equals("cotype")) {
                            eventType = parser.next();
                            resultbody.setCotype(parser.getText());
                        } else if (parser.getName().equals("cosize")) {
                            eventType = parser.next();
                            resultbody.setCosize(parser.getText());
                        } else if (parser.getName().equals("caddr")) {
                            eventType = parser.next();
                            resultbody.setCaddr(parser.getText());
                        } else if (parser.getName().equals("jobinfo")) {
                            eventType = parser.next();
                            resultbody.setJobinfo(parser.getText());
                        } else if (parser.getName().equals("isapply")) {
                            eventType = parser.next();
                            resultbody.setIsapply(parser.getText());
                        } else if (parser.getName().equals("weibosharetxt")) {
                            eventType = parser.next();
                            resultbody.setWeibosharetxt(parser.getText());
                        } else if (parser.getName().equals("smssharetxt")) {
                            eventType = parser.next();
                            resultbody.setSmssharetxt(parser.getText());
                        } else if (parser.getName().equals("emailsharesubject")) {
                            eventType = parser.next();
                            resultbody.setEmailsharesubject(parser.getText());
                        } else if (parser.getName().equals("emailsharetxt")) {
                            eventType = parser.next();
                            resultbody.setEmailsharetxt(parser.getText());
                        } else if (parser.getName().equals("weixinsharesubject")) {
                            eventType = parser.next();
                            resultbody.setWeixinsharesubject(parser.getText());
                        } else if (parser.getName().equals("weixinsharetxt")) {
                            eventType = parser.next();
                            resultbody.setWeixinsharetxt(parser.getText());
                        } else if (parser.getName().equals("pengyousharesubject")) {
                            eventType = parser.next();
                            resultbody.setPengyousharesubject(parser.getText());
                        } else if (parser.getName().equals("pengyousharetxt")) {
                            eventType = parser.next();
                            resultbody.setPengyousharetxt(parser.getText());
                        } else if (parser.getName().equals("qqsharesubject")) {
                            eventType = parser.next();
                            resultbody.setQqsharesubject(parser.getText());
                        } else if (parser.getName().equals("qqsharetxt")) {
                            eventType = parser.next();
                            resultbody.setQqsharetxt(parser.getText());
                        } else if (parser.getName().equals("share_url")) {
                            eventType = parser.next();
                            resultbody.setShare_url(parser.getText());
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
