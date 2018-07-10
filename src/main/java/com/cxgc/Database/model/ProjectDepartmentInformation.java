package com.cxgc.Database.model;

import java.sql.Date;

/**
 * Created by HeRui on 2018/5/21.
 */
public class ProjectDepartmentInformation {

    private String projectName;
    private String projectProvince;
    private String projectCity;
    private String projectState;
    private String projectDetail;
    private Date projectStartDate;
    private Date projectEndDate;
    private double projectStaffAmount;
    private double projectDeviceTypeAmount;
    //项目信息（名字），省份，市，状态，详情，开工日期，结束日期，员工数量，设备种类数


    public  ProjectDepartmentInformation(){super();}

    public ProjectDepartmentInformation(String projectName, String projectProvince, String projectCity, String projectState, String projectDetail, Date projectStartDate, Date projectEndDate, double projectStaffAmount, double projectDeviceTypeAmount) {
        this.projectName = projectName;
        this.projectProvince = projectProvince;
        this.projectCity = projectCity;
        this.projectState = projectState;
        this.projectDetail = projectDetail;
        this.projectStartDate = projectStartDate;
        this.projectEndDate = projectEndDate;
        this.projectStaffAmount = projectStaffAmount;
        this.projectDeviceTypeAmount = projectDeviceTypeAmount;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectProvince() {
        return projectProvince;
    }

    public void setProjectProvince(String projectProvince) {
        this.projectProvince = projectProvince;
    }

    public String getProjectCity() {
        return projectCity;
    }

    public void setProjectCity(String projectCity) {
        this.projectCity = projectCity;
    }

    public String getProjectState() {
        return projectState;
    }

    public void setProjectState(String projectState) {
        this.projectState = projectState;
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }

    public Date getProjectStartDate() {
        return projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }

    public Date getProjectEndDate() {
        return projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }

    public double getProjectStaffAmount() {
        return projectStaffAmount;
    }

    public void setProjectStaffAmount(double projectStaffAmount) {
        this.projectStaffAmount = projectStaffAmount;
    }

    public double getProjectDeviceTypeAmount() {
        return projectDeviceTypeAmount;
    }

    public void setProjectDeviceTypeAmount(double projectDeviceTypeAmount) {
        this.projectDeviceTypeAmount = projectDeviceTypeAmount;
    }
}
