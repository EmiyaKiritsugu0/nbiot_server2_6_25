package com.cxgc.Database.mapper;

import com.cxgc.Database.model.DeviceDynamicInformation;
import com.cxgc.Database.model.StaticInformation;
import com.cxgc.Database.model.StaticInformationMaterial;

import java.util.List;

/**
 * Created by HeRui on 2018/5/28.
 */
public interface SIMMapper {




        public List<StaticInformationMaterial> findById(String iotDeviceId);//查询某台设备信息

        public int updateTimeStamp(String iotDeviceId,java.sql.Timestamp timeStamp);

        public int updateNumCounter(String iotDeviceId,int NumCounter);

        public int updateIncreasingTrendAccumulatorNumCounter(String iotDeviceId,int increasingTrendAccumulator);

         public int add(StaticInformationMaterial staticInformationMaterial);


}
