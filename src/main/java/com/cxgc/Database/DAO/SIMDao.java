package com.cxgc.Database.DAO;

import com.cxgc.Database.mapper.*;
import com.cxgc.Database.model.StaticInformationMaterial;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by HeRui on 2018/5/28.
 */
public class SIMDao {

    /**
     * to add a StaticInformationMaterial to the database
     * @param  object: StaticInformationMaterial
     * write succeed in console when the process succeed
     */
    public void add(StaticInformationMaterial object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMMapper mapper = sqlSession.getMapper(SIMMapper.class);
            result = mapper.add(object);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }

    /**
     * to find a list of particular SIIM info from the database by id
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @return : a instance of list contains all the info of StaticInformationMaterial which meet the condition
     */
    public List<StaticInformationMaterial> findById(String iotDeviceId){

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMMapper mapper = sqlSession.getMapper(SIMMapper.class);
            return mapper.findById(iotDeviceId);
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to update timeStamp  the SIIM info of a certain device in the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param   timeStamp: the timeStamp which contains the latest time info
     * write succeed in console when the process succeed
     */
    public void updateTimeStamp(String iotDeviceId,java.sql.Timestamp timeStamp) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMMapper mapper = sqlSession.getMapper(SIMMapper.class);

            result = mapper.updateTimeStamp(iotDeviceId,timeStamp);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to update numCounter  the SIIM info of a certain device in the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param   NumCounter: the current value of num counter
     * write succeed in console when the process succeed
     */
    public void updateNumCounter(String iotDeviceId,int NumCounter)throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMMapper mapper = sqlSession.getMapper(SIMMapper.class);

            result = mapper.updateNumCounter(iotDeviceId,NumCounter);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to update increasingTrendAccumulator  the SIIM info of a certain device in the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @param   increasingTrendAccumulator: the current value of num increasingTrendAccumulator
     * write succeed in console when the process succeed
     */
    public void updateIncreasingTrendAccumulatorNumCounter(String iotDeviceId ,int increasingTrendAccumulator)throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            SIMMapper mapper = sqlSession.getMapper(SIMMapper.class);

            result = mapper.updateIncreasingTrendAccumulatorNumCounter(iotDeviceId ,increasingTrendAccumulator);
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
      //      System.out.println("processing succeed!");
        }else
        {
      //      System.out.println("processing failed!");
        }
    }
}
