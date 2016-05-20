package com.malus.pushshow.bean.job51;

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
public class JobInfoForm51 {
    private String result;
    private JobInfoBodyForm51 resultbody;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public JobInfoBodyForm51 getResultbody() {
        return resultbody;
    }

    public void setResultbody(JobInfoBodyForm51 resultbody) {
        this.resultbody = resultbody;
    }

    @Override
    public String toString() {
        return "JobInfoForm51{" +
                "result='" + result + '\'' +
                ", resultbody=" + resultbody +
                '}';
    }

    public JobInfoForm51 parse(InputStream is){
        XmlPullParser parser = Xml.newPullParser();
        this.resultbody = new JobInfoBodyForm51();
        List<JobItemForm51> items = new ArrayList<>();
        JobItemForm51 jobItemForm51 = new JobItemForm51();

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
                        } else if (parser.getName().equals("uselandmarkstatus")) {
                            eventType = parser.next();
                            resultbody.setUselandmarkstatus(parser.getText());
                        } else if (parser.getName().equals("uselandmarkmessage")) {
                            eventType = parser.next();
                            resultbody.setUselandmarkmessage(parser.getText());
                        } else if (parser.getName().equals("totalcount")) {
                            eventType = parser.next();
                            resultbody.setTotalcount(parser.getText());
                        } else if (parser.getName().equals("maxapplynum")) {
                            eventType = parser.next();
                            resultbody.setMaxapplynum(parser.getText());
                        } else if (parser.getName().equals("enablelandmarks")) {
                            eventType = parser.next();
                            resultbody.setEnablelandmarks(parser.getText());
                        }else if (parser.getName().equals("item")) {

                        } else if (parser.getName().equals("jobid")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {

                                jobItemForm51.setJobid(parser.getText());
                            }
                        } else if (parser.getName().equals("jobname")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setJobname(parser.getText());
                            }
                        } else if (parser.getName().equals("coid")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setCoid(parser.getText());
                            }
                        } else if (parser.getName().equals("cddr")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setCddr(parser.getText());
                            }
                        } else if (parser.getName().equals("coname")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setConame(parser.getText());
                            }
                        } else if (parser.getName().equals("issuedate")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setIssuedate(parser.getText());
                            }
                        } else if (parser.getName().equals("jobarea")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setJobarea(parser.getText());
                            }
                        } else if (parser.getName().equals("degree")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setDegree(parser.getText());
                            }
                        } else if (parser.getName().equals("workyear")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setWorkyear(parser.getText());
                            }
                        } else if (parser.getName().equals("cotype")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setCotype(parser.getText());
                            }
                        } else if (parser.getName().equals("jobinfo")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setJobinfo(parser.getText());
                            }
                        } else if (parser.getName().equals("isintern")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setIsintern(parser.getText());
                            }
                        } else if (parser.getName().equals("providesalary")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setProvidesalary(parser.getText());
                            }
                        } else if (parser.getName().equals("lon")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setLon(parser.getText());
                            }
                        } else if (parser.getName().equals("lat")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setLat(parser.getText());
                            }
                        } else if (parser.getName().equals("isurgency")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setIsurgency(parser.getText());
                            }
                        } else if (parser.getName().equals("istop")) {
                            eventType = parser.next();
                            if (jobItemForm51 != null) {
                                jobItemForm51.setIstop(parser.getText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item")) {
                            if(jobItemForm51!=null){
                                items.add(jobItemForm51);
                            }
                            jobItemForm51 = new JobItemForm51();
                        }else if(parser.getName().equals("resultbody")){
                            this.resultbody.setItems(items);
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
