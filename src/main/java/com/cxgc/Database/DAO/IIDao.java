package com.cxgc.Database.DAO;

import com.cxgc.Database.mapper.DBConnectorSingleton;
import com.cxgc.Database.mapper.DDIMapper;
import com.cxgc.Database.mapper.IIMapper;
import com.cxgc.Database.model.DeviceDynamicInformation;
import com.cxgc.Database.model.InspectorInformation;
import org.apache.ibatis.session.SqlSession;

import java.sql.Time;
import java.util.List;

/**
 * Created by HeRui on 2018/5/24.
 */
public class IIDao {
    /**
     * to add a inspector info to the database
     * @param  object: inspector information
     * write succeed in console when the process succeed
     */
    public void add(InspectorInformation object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            result = mapper.add(object);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }

    /**
     * to find a list of particular inspector info from the database by id,particular day,start and stop time stamp(time)
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param startDateStamp: the particular day when inspector information is acquired
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information start with
     * @return : a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByIdAndHour(String iotDeviceId, java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByIdAndHour(iotDeviceId,startDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find a list of particular inspector info from the database by id,start and stop time stamp(date)
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp: the particular time(date) stamp which the information start with
     * @return : a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByIdAndDate(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByIdAndDate(iotDeviceId,startDateStamp,stopDateStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find a list of particular inspector  info from the database by id,particular day
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param startDateStamp: the particular day when device dynamic information is acquired
     * @return : a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByIdAndParticularDate(String iotDeviceId, java.sql.Date startDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByIdAndParticularDate(iotDeviceId,startDateStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find a list of particular inspector info from the database by id, start time stamp,stop time stamp for both time and date
     * @param iotDeviceId the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp :the particular time(date) stamp which the information stop with
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information stop with
     * @return : a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByIdAndDateAndHour(String iotDeviceId, java.sql.Date startDateStamp, java.sql.Date stopDateStamp,Time startTimeStamp, Time stopTimeStamp){
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByIdAndDateAndHour(iotDeviceId,startDateStamp,stopDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
    }


    /**
     * to find a particular inspector info from the database
     * @param  startDateStamp: the particular day when device dynamic information is acquired
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information start with
     * @return : a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByHour(java.sql.Date startDateStamp, Time startTimeStamp, Time stopTimeStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByHour(startDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find a list of particular inspector info of device from the database by start and stop time stamp
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param  stopDateStamp: the particular time(date) stamp which the information start with
     * @return : a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByDate(java.sql.Date startDateStamp, java.sql.Date stopDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByDate(startDateStamp,stopDateStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find a list of particular inspector info from the database by particular day
     * @param startDateStamp: the day by which the dynamic information of device is finded
     * @return a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByParticularDate(java.sql.Date startDateStamp) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByParticularDate(startDateStamp);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular inspector info from the database by  start time stamp,stop time stamp for both time and date
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp :the particular time(date) stamp which the information stop with
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information stop with
     * @return : a instance of list contains all the info of InspectorInformation which meet the condition
     */
    public List<InspectorInformation> findByDateAndHour(java.sql.Date startDateStamp, java.sql.Date stopDateStamp, Time startTimeStamp, Time stopTimeStamp){
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findByDateAndHour(startDateStamp,stopDateStamp,startTimeStamp,stopTimeStamp);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to find the latest inspector info from the database
     * @param iotDeviceId the id of the device you want to query
     * @return:  the latest inspector info
     */
    public List<InspectorInformation> findLatest(String iotDeviceId) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            IIMapper mapper = sqlSession.getMapper(IIMapper.class);
            return mapper.findLatest(iotDeviceId);
        } finally {
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
       //     System.out.println("processing succeed!");
        }else
        {
      //      System.out.println("processing failed!");
        }
    }
}
