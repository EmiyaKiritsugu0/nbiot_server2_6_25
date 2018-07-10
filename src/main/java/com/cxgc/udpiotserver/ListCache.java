package com.cxgc.udpiotserver;

import com.cxgc.Database.DAO.DaoUtil;

import java.sql.Time;
import java.util.Date;

/**
 * Created by CHUANGXINGONGCHANG.HeRui on 2018/5/28.
 */
public class ListCache {

    private Date date;
    private String iotDeviceId;
//    private double deltaDistance;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIotDeviceIdd() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

//    public double getDeltaDistance() {
//        return deltaDistance;
//    }
//
//    public void setDeltaDistance(double deltaDistance) {
//        this.deltaDistance = deltaDistance;
//    }

    public java.sql.Date getSqlDate()
    {
        return DaoUtil.strToDate(new java.sql.Date(this.date.getTime()).toString());
    }

    public Time getSqlTime()
    {
        return DaoUtil.strToTime(new java.sql.Time(this.date.getTime()).toString());
    }

    @Override
    public boolean equals(Object object){
        if(this == object)
            return true;
        if(object == null)
            return false;
        if(this.getClass() != object.getClass())
            return false;
        final ListCache listCache = (ListCache) object;
        if(this.date.getTime() != listCache.date.getTime())
            return false;
        if(!this.iotDeviceId.equals(listCache.iotDeviceId))
            return false;
//        if(this.deltaDistance != listCache.deltaDistance)
//            return false;
        return true;
    }
}
