package com.cxgc.Database.DAO;

import com.cxgc.Database.mapper.DBConnectorSingleton;
import com.cxgc.Database.mapper.SIMapper;
import com.cxgc.Database.model.StaticInformation;
import org.apache.ibatis.session.SqlSession;

import java.sql.Time;
import java.util.List;
//:
/**
 * Created by CHUANGXINGONGCHANG.HeRui on 2018/5/28.
 * DAO layer which you can use when interacting with database.
 */
public class SIDao {

    /**
     * to find a list of particular static info from the database by id, currentDate
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param currentDate: the particular day when static information is acquired
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<StaticInformation> findByIdAndParticularDate(String iotDeviceId, java.sql.Date currentDate){

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);
            return mapper.findByIdAndParticularDate(iotDeviceId,currentDate);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular static info from the database by id
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<StaticInformation> findById(String iotDeviceId){

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);
            return mapper.findById(iotDeviceId);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular static info from the database by currentDate
     * @param currentDate: the particular day when static information is acquired
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<StaticInformation> findByParticularDate(java.sql.Date currentDate){

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);
            return mapper.findByParticularDate(currentDate);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to update dailyFuelCost  the SI info of a certain device in the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param currentDate the particular date distinguishing different day
     * @param   dailyFuelCost dailyFuelCost
     * write succeed in console when the process succeed
     */
    public void updateDailyFuelCost(String iotDeviceId,java.sql.Date currentDate,double dailyFuelCost) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);

            result = mapper.updateDailyFuelCost(iotDeviceId,currentDate,dailyFuelCost);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to update dailyDistance  the SI info of a certain device in the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param currentDate the particular date distinguishing different day
     * @param   dailyDistance dailyDistance
     * write succeed in console when the process succeed
     */
    public void updateDailyDistance(String iotDeviceId,java.sql.Date currentDate,double dailyDistance) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);

            result = mapper.updateDailyDistance(iotDeviceId,currentDate,dailyDistance);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }


    /**
     * to update dailyRunTime  the SI info of a certain device in the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param currentDate the particular date distinguishing different day
     * @param   dailyRunTime dailyRunTime
     * write succeed in console when the process succeed
     */
    public void updateDailyRunTime(String iotDeviceId,java.sql.Date currentDate,Time dailyRunTime) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);

            result = mapper.updateDailyRunTime(iotDeviceId,currentDate,dailyRunTime);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to update dailyUsingRate  the SI info of a certain device in the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param currentDate the particular date distinguishing different day
     * @param   dailyUsingRate dailyUsingRate
     * write succeed in console when the process succeed
     */
    public void updateDailyUsingRate(String iotDeviceId,java.sql.Date currentDate,double dailyUsingRate) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);

            result = mapper.updateDailyUsingRate(iotDeviceId,currentDate,dailyUsingRate);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to add a StaticInformation to the database
     * @param  object: StaticInformation
     * write succeed in console when the process succeed
     */
    public void add(StaticInformation object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMapper mapper = sqlSession.getMapper(SIMapper.class);
            result = mapper.add(object);
            checkResults(result);
            sqlSession.commit();
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
       //     System.out.println("processing failed!");
        }
    }

}
///:~