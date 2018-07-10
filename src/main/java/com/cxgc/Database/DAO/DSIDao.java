package com.cxgc.Database.DAO;

import com.cxgc.Database.mapper.DBConnectorSingleton;
import com.cxgc.Database.mapper.DSIMapper;
import com.cxgc.Database.model.DeviceStaticInformation;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/3/10.
 * DAO layer which you can use when interacting with database.
 */
public class DSIDao {

    /**
     * to add a device static info to the database
     * @param  object: device static information
     * write succeed in console when the process succeed
     */
    public void add(DeviceStaticInformation object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            result = mapper.add(object);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }

    /**
     * to find a list of particular device static info from the database
     * @param  iotDeviceId: the id of iot device which is used to distinguish them from each other( we set it to be the device id)
     * @return : a instance of list which contains DeviceStaticInformation
     */
    public List<DeviceStaticInformation> findByIotDeviceId(String iotDeviceId) throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            return mapper.findByIotDeviceId(iotDeviceId);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find all the device static info from the database
     * @return : a instance of list contains all the info of DeviceStaticInformation
     */
    public List<DeviceStaticInformation> findAll() throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);

            return mapper.findAll();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to alter any column of  the device static info in the database
     * @param  object: a hashmap which using method lies below.
     * write succeed in console when the process succeed
     */
    public void update(HashMap object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);

            result = mapper.update(object);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    /* using way:
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("field",field);//field为字段名称，根据需要添加
        map.put("val", "'"+value+"'"); //String 类型时需要加单引号，value 为值
        map.put("iotDeviceId", "'"+iotDeviceId+"'");
     */
    /**
     * to delete a  device static info in the database
     * @param  iotDeviceId: the iot device id of the info which you want to delete.
     * write succeed in console when the process succeed
     */
    public void delete(String iotDeviceId) throws Exception {

        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            result = mapper.delete(iotDeviceId);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }
    /**
     * to find a list of particular device static info from the database
     * @param  SIMId: the SIM card Id contained in the database
     * @return : a instance of list which contains DeviceStaticInformation
     */
    public List<DeviceStaticInformation> findBySIMId(String SIMId)throws Exception{

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            return mapper.findBySIMId(SIMId);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular device static info from the database
     * @param  RFId: the magnet Id contained in the database
     * @return : a instance of list which contains DeviceStaticInformation
     */
    public List<DeviceStaticInformation> findByRFId(String RFId){
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            return mapper.findByRFId(RFId);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular device static info from the database
     * @param  projectInformation: the name of the project which the device belongs to
     * @return : a instance of list which contains DeviceStaticInformation
     */
    public List<DeviceStaticInformation> findByProjectInformation(String projectInformation){

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            return mapper.findByProjectInformation(projectInformation);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular device static info from the database
     * @param  engineNumber: the engine number of the vehicle on which the device sets
     * @return : a instance of list which contains DeviceStaticInformation
     */
    public List<DeviceStaticInformation> findByEngineNumber(String engineNumber){

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            return mapper.findByEngineNumber(engineNumber);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular device static info from the database
     * @param  inControl: to figure out whether the vehicle on which the device sets is still governed by the company
     * @return : a instance of list which contains DeviceStaticInformation
     */
    public List<DeviceStaticInformation> findByInControl(boolean inControl){

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            DSIMapper mapper = sqlSession.getMapper(DSIMapper.class);
            return mapper.findByInControl(inControl);
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
        //    System.out.println("processing succeed!");
        }else
        {
       //     System.out.println("processing failed!");
        }
    }


}
///:~