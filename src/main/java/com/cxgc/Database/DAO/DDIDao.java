package com.cxgc.Database.DAO;

import com.cxgc.Database.mapper.DBConnectorSingleton;
import com.cxgc.Database.mapper.DDIMapper;
import com.cxgc.Database.model.DeviceDynamicInformation;

import org.apache.ibatis.session.SqlSession;

import java.sql.Time;
import java.util.List;
//:
/**
 * Created by CHUANGXINGONGCHANG.HeRui on 2018/5/28.
 * DAO layer which you can use when interacting with database.
 */
public class DDIDao {
    /**
     * to add a device dynamic info to the database
     * @param  object: device dynamic information
     * write succeed in console when the process succeed
     */
    public void add(DeviceDynamicInformation object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            result = mapper.add(object);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }

    /**
     * to find a list of particular device dynamic info from the database by id,particular day,start and stop time stamp(time)
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param startDateStamp: the particular day when device dynamic information is acquired
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information start with
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<DeviceDynamicInformation> findByIdAndHour(String iotDeviceId, java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByIdAndHour(iotDeviceId,startDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find a list of particular device dynamic info from the database by id,start and stop time stamp(date)
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp: the particular time(date) stamp which the information start with
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
     public List<DeviceDynamicInformation> findByIdAndDate(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByIdAndDate(iotDeviceId,startDateStamp,stopDateStamp);
        } finally {
            sqlSession.close();
        }
     }

    /**
     * to find a list of particular device dynamic info from the database by id,particular day
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param startDateStamp: the particular day when device dynamic information is acquired
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
     public List<DeviceDynamicInformation> findByIdAndParticularDate(String iotDeviceId, java.sql.Date startDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByIdAndParticularDate(iotDeviceId,startDateStamp);
        } finally {
            sqlSession.close();
        }
     }

    /**
     * to find a list of particular device dynamic info from the database by id, start time stamp,stop time stamp for both time and date
     * @param iotDeviceId the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp :the particular time(date) stamp which the information stop with
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information stop with
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<DeviceDynamicInformation> findByIdAndDateAndHour(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp,Time startTimeStamp, Time stopTimeStamp){
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByIdAndDateAndHour(iotDeviceId,startDateStamp,stopDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
    }


    /**
     * to find a particular device dynamic info from the database
     * @param  startDateStamp: the particular day when device dynamic information is acquired
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information start with
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
     public List<DeviceDynamicInformation> findByHour(java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByHour(startDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
     }

    /**
     * to find a list of particular dynamic info of device from the database by start and stop time stamp
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param  stopDateStamp: the particular time(date) stamp which the information start with
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<DeviceDynamicInformation> findByDate(java.sql.Date startDateStamp, java.sql.Date stopDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByDate(startDateStamp,stopDateStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find a list of particular device dynamic info from the database by particular day
     * @param startDateStamp: the day by which the dynamic information of device is finded
     * @return a instance of list contains all the info of DeviceDynamicInformation which meet the condition
     */
    public List<DeviceDynamicInformation> findByParticularDate(java.sql.Date startDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByParticularDate(startDateStamp);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular device dynamic info from the database by  start time stamp,stop time stamp for both time and date
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp :the particular time(date) stamp which the information stop with
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information stop with
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<DeviceDynamicInformation> findByDateAndHour(java.sql.Date startDateStamp, java.sql.Date stopDateStamp,Time startTimeStamp, Time stopTimeStamp){
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findByDateAndHour(startDateStamp,stopDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find the latest device dynamic info from the database
     * @param iotDeviceId the id of the device you want to query
     * @return:  the latest device dynamic info
     */
    public List<DeviceDynamicInformation> findLatest(String iotDeviceId) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findLatest(iotDeviceId);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find the latest several device dynamic info from the database
     * @param iotDeviceId the id of the device you want to query
     * @param num the number of info you want to query
     * @return
     * @throws Exception
     */
    public List<DeviceDynamicInformation> findLatestByNum(String iotDeviceId, int num) throws Exception{
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try{
            DDIMapper mapper = sqlSession.getMapper(DDIMapper.class);
            return mapper.findLatestByNum(iotDeviceId, num);
        }finally {
            sqlSession.close();
        }
    }

    /**
     * to print result on the console
     * @param  result: the return of the database
     */
    public void checkResults(int result){
        if(result>0)
        {
         //   System.out.println("processing succeed!");
        }else
        {
         //   System.out.println("processing failed!");
        }
    }
}
///:~