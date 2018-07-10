package com.cxgc.Database.mapper;


import com.cxgc.Database.model.StaticInformation;
import com.cxgc.Database.model.StaticInformationMaterial;

import java.sql.Time;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/3/10.
 * to create a mapper which is implement in Mapper,xml
 */
public interface SIMapper {

    public List<StaticInformation> findByIdAndParticularDate(String iotDeviceId, java.sql.Date currentDate);//查询某台设备在某日期的信息

    public List<StaticInformation> findById(String iotDeviceId);//查询某台设备信息

    public List<StaticInformation> findByParticularDate(java.sql.Date currentDate);//查询某日期的信息

    public int updateDailyFuelCost(String iotDeviceId,java.sql.Date currentDate,double dailyFuelCost);

    public int updateDailyDistance(String iotDeviceId,java.sql.Date currentDate,double dailyDistance);

    public int updateDailyRunTime(String iotDeviceId,java.sql.Date currentDate,Time dailyRunTime);

    public int updateDailyUsingRate(String iotDeviceId,java.sql.Date currentDate,double dailyUsingRate);

    public int add(StaticInformation staticInformation);

}
///:~