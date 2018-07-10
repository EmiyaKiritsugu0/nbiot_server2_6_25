package com.cxgc.Database.model;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by HeRui on 2018/5/21.
 */
public class StaticInformation {

    private String iotDeviceId;
    private Date currentDate;
    private double dailyFuelCost;
    private double dailyDistance;
    private Time dailyRunTime;
    private double dailyUsingRate;
    //下位机id，当前日期，每日油耗，每日行程，每日运行时间，本日利用率


    public StaticInformation() {super();
    }

    public StaticInformation(String iotDeviceId, Date currentDate, double dailyFuelCost, double dailyDistance, Time dailyRunTime, double dailyUsingRate) {
        this.iotDeviceId = iotDeviceId;
        this.currentDate = currentDate;
        this.dailyFuelCost = dailyFuelCost;
        this.dailyDistance = dailyDistance;
        this.dailyRunTime = dailyRunTime;
        this.dailyUsingRate = dailyUsingRate;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    public double getDailyFuelCost() {
        return dailyFuelCost;
    }

    public void setDailyFuelCost(double dailyFuelCost) {
        this.dailyFuelCost = dailyFuelCost;
    }

    public double getDailyDistance() {
        return dailyDistance;
    }

    public void setDailyDistance(double dailyDistance) {
        this.dailyDistance = dailyDistance;
    }

    public Time getDailyRunTime() {
        return dailyRunTime;
    }

    public void setDailyRunTime(Time dailyRunTime) {
        this.dailyRunTime = dailyRunTime;
    }

    public double getDailyUsingRate() {
        return dailyUsingRate;
    }

    public void setDailyUsingRate(double dailyUsingRate) {
        this.dailyUsingRate = dailyUsingRate;
    }
}
