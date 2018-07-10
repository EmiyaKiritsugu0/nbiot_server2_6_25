package com.cxgc.udpiotserver;

import java.util.Date;

/**
 * Created by HeRui on 2018/5/24.
 */
 public class GPSInspector {

    private Date date;
    private String iotDeviceId;
    private double longitude;
    private double latitude;

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

    public GPSInspector(Date date, String iotDeviceId, double longitude, double latitude) {
        this.date = date;
        this.iotDeviceId = iotDeviceId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    @Override
    public boolean equals(Object object){
        if(this == object) {
            return true;
        }
        if(object == null) {
            return false;
        }
        if(this.getClass() != object.getClass()){
            return false;}
        final GPSInspector gpsInspector = (GPSInspector) object;
        if(this.date.getTime() != gpsInspector.date.getTime()) {
            return false;
        }
        if(!this.iotDeviceId.equals(gpsInspector.iotDeviceId)) {
            return false;
        }
        if(this.longitude != gpsInspector.longitude) {
            return false;
        }
        if(this.latitude != gpsInspector.latitude) {
            return false;
        }
        return true;
    }
}