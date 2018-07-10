package com.cxgc.Database.model;

import java.sql.Date;
import java.sql.Time;

public class HelmetDynamicInformation {
    public double getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(double heartRate) {
        this.heartRate = heartRate;
    }

    public double getLowPressure() {
        return lowPressure;
    }

    public void setLowPressure(double lowPressure) {
        this.lowPressure = lowPressure;
    }

    public double getHighPressure() {
        return highPressure;
    }

    public void setHighPressure(double highPressure) {
        this.highPressure = highPressure;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public double getContainingElectricity() {
        return containingElectricity;
    }

    public void setContainingElectricity(double containingElectricity) {
        this.containingElectricity = containingElectricity;
    }

    public Date getInformationDate() {
        return informationDate;
    }

    public void setInformationDate(Date informationDate) {
        this.informationDate = informationDate;
    }

    public Time getInformationTime() {
        return informationTime;
    }

    public void setInformationTime(Time informationTime) {
        this.informationTime = informationTime;
    }

    public String getHelmetId() {
        return helmetId;
    }

    public void setHelmetId(String helmetId) {
        this.helmetId = helmetId;
    }

    public String getGPSInformation() {
        return GPSInformation;
    }

    public void setGPSInformation(String GPSInformation) {
        this.GPSInformation = GPSInformation;
    }

    public double getDeltaDistanceSum() {
        return deltaDistanceSum;
    }

    public void setDeltaDistanceSum(double deltaDistanceSum) {
        this.deltaDistanceSum = deltaDistanceSum;
    }

    private double heartRate;
    private double lowPressure;
    private double highPressure;
    private double currentSpeed;
    private double containingElectricity;
    private java.sql.Date informationDate;
    private java.sql.Time informationTime;
    private String helmetId;
    private String GPSInformation;
    private double deltaDistanceSum;

    public HelmetDynamicInformation(double heartRate, double lowPressure, double highPressure, double currentSpeed, double containingElectricity,
                                    java.sql.Date informationDate, java.sql.Time informationTime, String helmetId, String GPSInformation,
                                    double deltaDistanceSum){
        this.heartRate = heartRate;
        this.lowPressure = lowPressure;
        this.highPressure = highPressure;
        this.currentSpeed = currentSpeed;
        this.containingElectricity = containingElectricity;
        this.informationDate = informationDate;
        this.informationTime = informationTime;
        this.helmetId = helmetId;
        this.GPSInformation = GPSInformation;
        this.deltaDistanceSum = deltaDistanceSum;
    }
}
