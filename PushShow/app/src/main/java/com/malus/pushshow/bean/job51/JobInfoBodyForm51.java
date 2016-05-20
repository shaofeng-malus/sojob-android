package com.malus.pushshow.bean.job51;

import java.util.List;

/********************
 * 作者：malus
 * 日期：16/3/10
 * 时间：下午4:47
 * 注释：
 ********************/
public class JobInfoBodyForm51 {
    private String uselandmarkstatus;
    private String uselandmarkmessage;
    private String totalcount;
    private String maxapplynum;
    private String enablelandmarks;
    private List<JobItemForm51> items;

    public String getUselandmarkstatus() {
        return uselandmarkstatus;
    }

    public void setUselandmarkstatus(String uselandmarkstatus) {
        this.uselandmarkstatus = uselandmarkstatus;
    }

    public String getUselandmarkmessage() {
        return uselandmarkmessage;
    }

    public void setUselandmarkmessage(String uselandmarkmessage) {
        this.uselandmarkmessage = uselandmarkmessage;
    }

    public String getTotalcount() {
        return totalcount;
    }

    public void setTotalcount(String totalcount) {
        this.totalcount = totalcount;
    }

    public String getMaxapplynum() {
        return maxapplynum;
    }

    public void setMaxapplynum(String maxapplynum) {
        this.maxapplynum = maxapplynum;
    }

    public String getEnablelandmarks() {
        return enablelandmarks;
    }

    public void setEnablelandmarks(String enablelandmarks) {
        this.enablelandmarks = enablelandmarks;
    }

    public List<JobItemForm51> getItems() {
        return items;
    }

    public void setItems(List<JobItemForm51> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "JobInfoBodyForm51{" +
                "uselandmarkstatus='" + uselandmarkstatus + '\'' +
                ", uselandmarkmessage='" + uselandmarkmessage + '\'' +
                ", totalcount='" + totalcount + '\'' +
                ", maxapplynum='" + maxapplynum + '\'' +
                ", enablelandmarks='" + enablelandmarks + '\'' +
                ", items=" + items +
                '}';
    }
}
