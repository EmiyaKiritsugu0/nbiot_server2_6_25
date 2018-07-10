package com.cxgc.Database.mapper;

import com.cxgc.Database.model.DeviceDynamicInformation;

import java.sql.Time;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/3/16.
 * Naming Convention:
 * if you want to know information about a exact device, the command name contains Id
 * if you want to query details about information collected in some hours ,  the command name contains Hour
 * caution: findByIdAndDate acquiesce in the opinion that you want to get the information collected in one day
 * if you want to  gather information in some days , the command name contains Date
 */
public interface DDIMapper {


    public int add(DeviceDynamicInformation deviceDynamicInformation);//添加一条设备动态信息

    public List<DeviceDynamicInformation> findByIdAndHour(String iotDeviceId, java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp);//查询某台设备在某天某个时间段的信息

    public List<DeviceDynamicInformation> findByIdAndDate(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp);//查询某台设备在某日期段的信息

    public List<DeviceDynamicInformation> findByIdAndParticularDate(String iotDeviceId, java.sql.Date startDateStamp);//查询某台设备在某日期的信息

    public List<DeviceDynamicInformation> findByIdAndDateAndHour(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp,Time startTimeStamp, Time stopTimeStamp);//查询某台设备在某日期与时间段的信息

    public List<DeviceDynamicInformation> findByHour(java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp);//查询所有设备在某天某个时间段的信息

    public List<DeviceDynamicInformation> findByDate(java.sql.Date startDateStamp, java.sql.Date stopDateStamp);//查询所有设备在某日期段的信息

    public List<DeviceDynamicInformation> findByParticularDate(java.sql.Date startDateStamp);//查询所有设备在某日期的信息

    public List<DeviceDynamicInformation> findByDateAndHour(java.sql.Date startDateStamp, java.sql.Date stopDateStamp,Time startTimeStamp, Time stopTimeStamp);//查询所有设备在某日期与时间段的信息

    public List<DeviceDynamicInformation> findLatest(String iotDeviceId);

    public List<DeviceDynamicInformation> findLatestByNum(String iotDeviceId, int num);



}
///:~