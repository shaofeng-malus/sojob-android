package com.malus.pushshow.bean.job51;

/********************
 * 作者：malus
 * 日期：16/4/1
 * 时间：下午8:45
 * 注释：
 ********************/
public class CompDetailBody {
    private String poster;
    private String coid;
    private String coname;
    private String cotype;
    private String cosize;
    private String indtype1;
    private String indtype2;
    private String caddr;
    private String coinfo;
    private String lon;
    private String lat;
    private String cojumpurl;
    private String logo;

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getCoid() {
        return coid;
    }

    public void setCoid(String coid) {
        this.coid = coid;
    }

    public String getConame() {
        return coname;
    }

    public void setConame(String coname) {
        this.coname = coname;
    }

    public String getCotype() {
        return cotype;
    }

    public void setCotype(String cotype) {
        this.cotype = cotype;
    }

    public String getCosize() {
        return cosize;
    }

    public void setCosize(String cosize) {
        this.cosize = cosize;
    }

    public String getIndtype1() {
        return indtype1;
    }

    public void setIndtype1(String indtype1) {
        this.indtype1 = indtype1;
    }

    public String getIndtype2() {
        return indtype2;
    }

    public void setIndtype2(String indtype2) {
        this.indtype2 = indtype2;
    }

    public String getCaddr() {
        return caddr;
    }

    public void setCaddr(String caddr) {
        this.caddr = caddr;
    }

    public String getCoinfo() {
        return coinfo;
    }

    public void setCoinfo(String coinfo) {
        this.coinfo = coinfo;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getCojumpurl() {
        return cojumpurl;
    }

    public void setCojumpurl(String cojumpurl) {
        this.cojumpurl = cojumpurl;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "CompDetailBody{" +
                "poster='" + poster + '\'' +
                ", coid='" + coid + '\'' +
                ", coname='" + coname + '\'' +
                ", cotype='" + cotype + '\'' +
                ", cosize='" + cosize + '\'' +
                ", indtype1='" + indtype1 + '\'' +
                ", indtype2='" + indtype2 + '\'' +
                ", caddr='" + caddr + '\'' +
                ", coinfo='" + coinfo + '\'' +
                ", lon='" + lon + '\'' +
                ", lat='" + lat + '\'' +
                ", cojumpurl='" + cojumpurl + '\'' +
                ", logo='" + logo + '\'' +
                '}';
    }
}
