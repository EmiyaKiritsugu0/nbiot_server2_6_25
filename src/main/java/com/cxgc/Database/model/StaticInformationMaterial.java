package com.cxgc.Database.model;

import java.sql.Timestamp;

/**
 * Created by CHUANGXINGONGCHANG.HeRui on 2018/5/28.
 */
public class StaticInformationMaterial {
    private String iotDeviceId ;
    private java.sql.Timestamp timeStampLatest;
    private  int numCounter = 0;
    private  int increasingTrendAccumulator = 0;

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public Timestamp getTimeStampLatest() {
        return timeStampLatest;
    }

    public void setTimeStampLatest(Timestamp timeStampLatest) {
        this.timeStampLatest = timeStampLatest;
    }

    public int getNumCounter() {
        return numCounter;
    }

    public void setNumCounter(int numCounter) {
        this.numCounter = numCounter;
    }

    public int getIncreasingTrendAccumulator() {
        return increasingTrendAccumulator;
    }

    public void setIncreasingTrendAccumulator(int increasingTrendAccumulator) {
        this.increasingTrendAccumulator = increasingTrendAccumulator;
    }
    public StaticInformationMaterial() {super();
    }

    public StaticInformationMaterial(String iotDeviceId, Timestamp timeStampLatest, int numCounter, int increasingTrendAccumulator) {
        this.iotDeviceId = iotDeviceId;
        this.timeStampLatest = timeStampLatest;
        this.numCounter = numCounter;
        this.increasingTrendAccumulator = increasingTrendAccumulator;
    }
}
