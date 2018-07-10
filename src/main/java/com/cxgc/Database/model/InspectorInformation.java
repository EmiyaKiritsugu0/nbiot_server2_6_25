package com.cxgc.Database.model;

import com.cxgc.udpiotserver.FuelInspector;
import com.cxgc.udpiotserver.GPSInspector;
import com.cxgc.udpiotserver.RotationInspector;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by HeRui on 2018/5/24.
 */
public class InspectorInformation {

    private String iotDeviceId;  //iotID
    private Date informationDate;//记录时间，年月日
    private Time informationTime;//记录时间，时分秒
    private double longitude;  //经度
    private double latitude; //纬度
    private boolean positionException; //位置异常
    private boolean fuelException; //燃料异常
    private boolean rotationException; //旋转异常

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
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

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public boolean isPositionException() {
        return positionException;
    }

    public void setPositionException(boolean positionException) {
        this.positionException = positionException;
    }

    public boolean isFuelException() {
        return fuelException;
    }

    public void setFuelException(boolean fuelException) {
        this.fuelException = fuelException;
    }

    public boolean isRotationException() {
        return rotationException;
    }

    public void setRotationException(boolean rotationException) {
        this.rotationException = rotationException;
    }

    public InspectorInformation() {
        super();
    }

    public InspectorInformation(String iotDeviceId, Date informationDate, Time informationTime, double longitude, double latitude, boolean positionException, boolean fuelException, boolean rotationException) {
        this.iotDeviceId = iotDeviceId;
        this.informationDate = informationDate;
        this.informationTime = informationTime;
        this.longitude = longitude;
        this.latitude = latitude;
        this.positionException = positionException;
        this.fuelException = fuelException;
        this.rotationException = rotationException;
    }
    public InspectorInformation(RotationInspector rotationInspector) {
        this.iotDeviceId = rotationInspector.getIotDeviceId();
        this.informationDate = new java.sql.Date(rotationInspector.getDate().getTime());
        this.informationTime = new java.sql.Time(rotationInspector.getDate().getTime());
        this.longitude = rotationInspector.getLongitude();
        this.latitude = rotationInspector.getLatitude();
        this.positionException = false;
        this.fuelException = false;
        this.rotationException = true;
    }

    public InspectorInformation(GPSInspector gpsInspector) {
        this.iotDeviceId = gpsInspector.getIotDeviceId();
        this.informationDate = new java.sql.Date(gpsInspector.getDate().getTime());
        this.informationTime = new java.sql.Time(gpsInspector.getDate().getTime());
        this.longitude = gpsInspector.getLongitude();
        this.latitude = gpsInspector.getLatitude();
        this.positionException = true;
        this.fuelException = false;
        this.rotationException = false;
    }

    public InspectorInformation(FuelInspector fuelInspector) {
        this.iotDeviceId = fuelInspector.getIotDeviceId();
        this.informationDate = new java.sql.Date(fuelInspector.getDate().getTime());
        this.informationTime = new java.sql.Time(fuelInspector.getDate().getTime());
        this.longitude = fuelInspector.getLongitude();
        this.latitude = fuelInspector.getLatitude();
        this.positionException = false;
        this.fuelException = true;
        this.rotationException = false;
    }
}
