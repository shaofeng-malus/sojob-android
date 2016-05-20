package com.malus.pushshow.bean;

import java.io.Serializable;

/********************
 * 作者：malus
 * 日期：16/2/23
 * 时间：下午2:05
 * 注释：
 ********************/
public class JobItemFormZL implements Serializable {
    private String Number;
    private String Name;
    private String CompanyAutoID;
    private String CompanyName;
    private String PublishTime;
    private String PublishTimeDt;
    private String CityId;
    private String WorkCity;
    private String Salary;
    private String Salary60;
    private String Education;
    private String CityDistrict;
    private String HasAppliedPosition;
    private String IsFastPosition;
    private String JobType;
    private String isFeedback;
//    private List<String> WelfareTab = new ArrayList<>();
    private String WorkingExp;
    private String Distance;
    private String feedbackRation;
    private String industry;
    private String companyLogo;
    private String emplType;

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompanyAutoID() {
        return CompanyAutoID;
    }

    public void setCompanyAutoID(String companyAutoID) {
        CompanyAutoID = companyAutoID;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getPublishTime() {
        return PublishTime;
    }

    public void setPublishTime(String publishTime) {
        PublishTime = publishTime;
    }

    public String getPublishTimeDt() {
        return PublishTimeDt;
    }

    public void setPublishTimeDt(String publishTimeDt) {
        PublishTimeDt = publishTimeDt;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getWorkCity() {
        return WorkCity;
    }

    public void setWorkCity(String workCity) {
        WorkCity = workCity;
    }

    public String getSalary() {
        return Salary;
    }

    public void setSalary(String salary) {
        Salary = salary;
    }

    public String getSalary60() {
        return Salary60;
    }

    public void setSalary60(String salary60) {
        Salary60 = salary60;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String education) {
        Education = education;
    }

    public String getCityDistrict() {
        return CityDistrict;
    }

    public void setCityDistrict(String cityDistrict) {
        CityDistrict = cityDistrict;
    }

    public String getHasAppliedPosition() {
        return HasAppliedPosition;
    }

    public void setHasAppliedPosition(String hasAppliedPosition) {
        HasAppliedPosition = hasAppliedPosition;
    }

    public String getIsFastPosition() {
        return IsFastPosition;
    }

    public void setIsFastPosition(String isFastPosition) {
        IsFastPosition = isFastPosition;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    public String getIsFeedback() {
        return isFeedback;
    }

    public void setIsFeedback(String isFeedback) {
        this.isFeedback = isFeedback;
    }

    public String getWorkingExp() {
        return WorkingExp;
    }

    public void setWorkingExp(String workingExp) {
        WorkingExp = workingExp;
    }

    public String getDistance() {
        return Distance;
    }

    public void setDistance(String distance) {
        Distance = distance;
    }

    public String getFeedbackRation() {
        return feedbackRation;
    }

    public void setFeedbackRation(String feedbackRation) {
        this.feedbackRation = feedbackRation;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getEmplType() {
        return emplType;
    }

    public void setEmplType(String emplType) {
        this.emplType = emplType;
    }

    @Override
    public String toString() {
        return "JobItemFormZL{" +
                "Number='" + Number + '\'' +
                ", Name='" + Name + '\'' +
                ", CompanyAutoID='" + CompanyAutoID + '\'' +
                ", CompanyName='" + CompanyName + '\'' +
                ", PublishTime='" + PublishTime + '\'' +
                ", PublishTimeDt='" + PublishTimeDt + '\'' +
                ", CityId='" + CityId + '\'' +
                ", WorkCity='" + WorkCity + '\'' +
                ", Salary='" + Salary + '\'' +
                ", Salary60='" + Salary60 + '\'' +
                ", Education='" + Education + '\'' +
                ", CityDistrict='" + CityDistrict + '\'' +
                ", HasAppliedPosition='" + HasAppliedPosition + '\'' +
                ", IsFastPosition='" + IsFastPosition + '\'' +
                ", JobType='" + JobType + '\'' +
                ", isFeedback='" + isFeedback + '\'' +
                ", WorkingExp='" + WorkingExp + '\'' +
                ", Distance='" + Distance + '\'' +
                ", feedbackRation='" + feedbackRation + '\'' +
                ", industry='" + industry + '\'' +
                ", companyLogo='" + companyLogo + '\'' +
                ", emplType='" + emplType + '\'' +
                '}';
    }
}
