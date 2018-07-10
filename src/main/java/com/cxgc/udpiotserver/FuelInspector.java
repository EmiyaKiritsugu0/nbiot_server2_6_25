package com.cxgc.udpiotserver;

import java.util.Date;

/**
 * Created by HeRui on 2018/5/24.
 */
public class FuelInspector {

    private Date date;
    private String iotDeviceId;
    private double longitude;
    private double latitude;
    private double speed;
    private double fuel;

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

    public double getFuel() {
        return fuel;
    }

    public void setFuel(double fuel) {
        this.fuel = fuel;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public FuelInspector(Date date, String iotDeviceId, double longitude, double latitude, double speed, double fuel) {
        this.date = date;
        this.iotDeviceId = iotDeviceId;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.fuel = fuel;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (this.getClass() != object.getClass())
            return false;
        final FuelInspector fuelInspector = (FuelInspector) object;
        if (this.date.getTime() != fuelInspector.date.getTime())
            return false;
        if (!this.iotDeviceId.equals(fuelInspector.iotDeviceId))
            return false;
        if (this.longitude != fuelInspector.longitude)
            return false;
        if (this.latitude != fuelInspector.latitude)
            return false;
        if (this.fuel != fuelInspector.fuel)
            return false;
        if (this.speed != fuelInspector.speed)
            return false;
        return true;
    }
}
