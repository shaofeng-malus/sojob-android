package com.malus.pushshow.bean;

import java.io.Serializable;
import java.util.List;

/********************
 * 作者：malus
 * 日期：16/2/23
 * 时间：下午2:06
 * 注释：
 ********************/
public class JobInfoFormZL implements Serializable{
    private List<JobItemFormZL> Positions;
    private int Count;
    private int StatusCode;
    private String StatusDescription;

    public List<JobItemFormZL> getPositions() {
        return Positions;
    }

    public void setPositions(List<JobItemFormZL> positions) {
        Positions = positions;
    }

    public int getCount() {
        return Count;
    }

    public void setCount(int count) {
        Count = count;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }

    public String getStatusDescription() {
        return StatusDescription;
    }

    public void setStatusDescription(String statusDescription) {
        StatusDescription = statusDescription;
    }

    @Override
    public String toString() {
        return "JobInfoFormZL{" +
                "Positions=" + Positions +
                ", Count=" + Count +
                ", StatusCode=" + StatusCode +
                ", StatusDescription='" + StatusDescription + '\'' +
                '}';
    }
}
