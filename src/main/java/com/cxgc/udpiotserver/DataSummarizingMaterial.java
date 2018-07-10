package com.cxgc.udpiotserver;

import java.util.Date;

/**
 * Created by CHUANGXINGONGCHANG.HeRui on 2018/5/28.
 */
public class DataSummarizingMaterial {

    private Date date;
    private String iotDeviceId;
    private int workingFlag;
    private double deltaDistance;
    private double fuelAverage;
    private double latestfuel;

    public double getLatestfuel() {
        return latestfuel;
    }

    public void setLatestfuel(double latestfuel) {
        this.latestfuel = latestfuel;
    }



    public DataSummarizingMaterial(Date date, String iotDeviceId, int workingFlag, double deltaDistance, double fuelAverage) {
        this.date = date;
        this.iotDeviceId = iotDeviceId;
        this.workingFlag = workingFlag;
        this.deltaDistance = deltaDistance;
        this.fuelAverage = fuelAverage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public int getWorkingFlag() {
        return workingFlag;
    }

    public void setWorkingFlag(int workingFlag) {
        this.workingFlag = workingFlag;
    }

    public double getDeltaDistance() {
        return deltaDistance;
    }

    public void setDeltaDistance(double deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    public double getFuelAverage() {
        return fuelAverage;
    }

    public void setFuelAverage(double fuelAverage) {
        this.fuelAverage = fuelAverage;
    }
}
