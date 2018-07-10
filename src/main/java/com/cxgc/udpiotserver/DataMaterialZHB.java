package com.cxgc.udpiotserver;

import java.util.Date;

public class DataMaterialZHB {
    public Date getDate() {
        return date;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public boolean getWorkingFlag() {
        return workingFlag;
    }

    public double getDeltaDistance() {
        return deltaDistance;
    }

    public double getDeltaFuel() {
        return deltaFuel;
    }

    private Date date;
    private String iotDeviceId;
    private boolean workingFlag;
    private double deltaDistance;
    private double deltaFuel;

    public DataMaterialZHB(Date date, String iotDeviceId, boolean workingFlag, double deltaDistance, double deltaFuel){
        this.date = date;
        this.iotDeviceId = iotDeviceId;
        this.workingFlag = workingFlag;
        this.deltaDistance = deltaDistance;
        this.deltaFuel = deltaFuel;
    }
}
