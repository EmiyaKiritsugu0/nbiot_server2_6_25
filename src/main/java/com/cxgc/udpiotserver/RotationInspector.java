package com.cxgc.udpiotserver;

import java.util.Date;

/**
 * Created by HeRui on 2018/5/24.
 */

public class RotationInspector {

    private Date date;
    private String iotDeviceId;
    private double longitude;
    private double latitude;
    private boolean rotationDirection;

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

    public boolean isRotationDirection() {
        return rotationDirection;
    }

    public void setRotationDirection(boolean rotationDirection) {
        this.rotationDirection = rotationDirection;
    }

    public RotationInspector(Date date, String iotDeviceId, double longitude, double latitude, boolean rotationDirection) {
        this.date = date;
        this.iotDeviceId = iotDeviceId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.rotationDirection = rotationDirection;
    }

    @Override
    public boolean equals(Object object){
        if(this == object)
            return true;
        if(object == null)
            return false;
        if(this.getClass() != object.getClass())
            return false;
        final RotationInspector rotationInspector= (RotationInspector) object;
        if(this.date.getTime() != rotationInspector.date.getTime())
            return false;
        if(!this.iotDeviceId.equals(rotationInspector.iotDeviceId))
            return false;
        if(this.longitude != rotationInspector.longitude)
            return false;
        if(this.rotationDirection != rotationInspector.rotationDirection)
            return false;
         return true;
    }
}