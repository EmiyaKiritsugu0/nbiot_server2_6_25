package com.cxgc.Database.model;

import java.sql.Date;

/**
 * Created by HeRui on 2018/3/7.
 * describe device static information
 *
 */
public class DeviceStaticInformation {


    private String SIMId;//sim卡id
    private String iotDeviceId;//IOTid
    private String RFId;//磁条id
    private String projectInformation;// 项目信息
    private String numberPlate; //车牌号
    private String engineNumber; //发动机号
    private Boolean inControl; //设备是否还在中铁管辖下
    private String temp;//临时
    private Date startDate;// 进场时间


    public DeviceStaticInformation(){
        super();
    }

    public DeviceStaticInformation(String SIMId, String iotDeviceId, String RFId, String projectInformation, String numberPlate, String engineNumber, Boolean inControl, String temp, Date startDate) {
        this.SIMId = SIMId;
        this.iotDeviceId = iotDeviceId;
        this.RFId = RFId;
        this.projectInformation = projectInformation;
        this.numberPlate = numberPlate;
        this.engineNumber = engineNumber;
        this.inControl = inControl;
        this.temp = temp;
        this.startDate = startDate;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getSIMId() {
        return SIMId;
    }

    public void setSIMId(String SIMId) {
        this.SIMId = SIMId;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public String getRFId() {
        return RFId;
    }

    public void setRFId(String RFId) {
        this.RFId = RFId;
    }

    public String getProjectInformation() {
        return projectInformation;
    }

    public void setProjectInformation(String projectInformation) {
        this.projectInformation = projectInformation;
    }

    public String getNumberPlate() {
        return numberPlate;
    }

    public void setNumberPlate(String numberPlate) {
        this.numberPlate = numberPlate;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    public Boolean getInControl() {
        return inControl;
    }

    public void setInControl(Boolean inControl) {
        this.inControl = inControl;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    //end
}
