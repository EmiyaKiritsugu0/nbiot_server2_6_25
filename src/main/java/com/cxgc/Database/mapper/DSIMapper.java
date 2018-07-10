package com.cxgc.Database.mapper;
//:
import com.cxgc.Database.model.DeviceStaticInformation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HeRui on 2018/3/10.
 * to create a mapper which is implement in Mapper,xml
 */

public interface DSIMapper {

    public int add(DeviceStaticInformation deviceStaticInformation);//增加设备静态信息，下位机id不能相同

    public int update(HashMap map);//修改设备静态信息某一字段信息，不能改下位机id


    public int delete(String iotDeviceId);//按照下位机id删除某一个设备静态信息

    public List<DeviceStaticInformation> findByIotDeviceId(String iotDeviceId);//按照下位机IOTid查找某一个设备静态信息

    public List<DeviceStaticInformation> findBySIMId(String SIMId);//按照下位机SIMid查找某一个设备静态信息

    public List<DeviceStaticInformation> findByRFId(String RFId);//按照下位机RFid查找某一个设备静态信息

    public List<DeviceStaticInformation> findByProjectInformation(String projectInformation);//按照下位机项目信息查找设备静态信息

    public List<DeviceStaticInformation> findByEngineNumber(String engineNumber);//按照设备车牌号查找某一个设备静态信息

    public List<DeviceStaticInformation> findByInControl(boolean inControl);//按照设备是否还在中铁管辖下查找设备静态信息

    public List<DeviceStaticInformation> findAll();//找到所有设备静态信息


}
///:~