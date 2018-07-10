package com.cxgc.Database.mapper;

import com.cxgc.Database.model.DeviceDynamicInformation;
import com.cxgc.Database.model.InspectorInformation;
import com.cxgc.Database.model.ProjectDepartmentInformation;

import java.sql.Time;
import java.util.HashMap;
import java.util.List;

/**
 * Created by HeRui on 2018/5/24.
 * @see DDIMapper very similar
 */
public interface IIMapper {

    public int add(InspectorInformation inspectorInformation);//添加一条项目信息

    public List<InspectorInformation> findByIdAndHour(String iotDeviceId, java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp);//查询某台设备在某天某个时间段的信息

    public List<InspectorInformation> findByIdAndDate(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp);//查询某台设备在某日期段的信息

    public List<InspectorInformation> findByIdAndParticularDate(String iotDeviceId, java.sql.Date startDateStamp);//查询某台设备在某日期的信息

    public List<InspectorInformation> findByIdAndDateAndHour(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp,Time startTimeStamp, Time stopTimeStamp);//查询某台设备在某日期与时间段的信息

    public List<InspectorInformation> findByHour(java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp);//查询所有设备在某天某个时间段的信息

    public List<InspectorInformation> findByDate(java.sql.Date startDateStamp, java.sql.Date stopDateStamp);//查询所有设备在某日期段的信息

    public List<InspectorInformation> findByParticularDate(java.sql.Date startDateStamp);//查询所有设备在某日期的信息

    public List<InspectorInformation> findByDateAndHour(java.sql.Date startDateStamp, java.sql.Date stopDateStamp,Time startTimeStamp, Time stopTimeStamp);//查询所有设备在某日期与时间段的信息

    public List<InspectorInformation> findLatest(String iotDeviceId);
}
