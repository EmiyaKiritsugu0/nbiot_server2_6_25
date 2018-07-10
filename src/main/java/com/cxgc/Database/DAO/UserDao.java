package com.cxgc.Database.DAO;

import com.cxgc.Database.mapper.DBConnectorSingleton;
import com.cxgc.Database.mapper.UserMapper;
import com.cxgc.Database.model.User;

import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/3/10.
 * DAO layer which you can use when interacting with database.
 */
public class UserDao {

    /**
     * to add a user to the database
     * @param  object: user info
     * write succeed in console when the process succeed
     */
    public void add(User object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            result = mapper.add(object);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a particular user info from the database
     * @param  jobNumber: job_number of user info
     * @return : a instance of list which contains User info
     */
  public List<User> findByJobNumber(String jobNumber) throws Exception {

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findByJobNumber(jobNumber);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find all the user info from the database
     * @return : a instance of list which contains all the info of User
     */
    public List<User> findAll() throws Exception {
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

            return mapper.findAll();
        } finally {
            sqlSession.close();
        }
    }

    /**
     * to alter any column of  the user info in the database
     * @param  object: a hash map which using method lies below.
     * write succeed in console when the process succeed
     */
    public void update(HashMap object) throws Exception {
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);

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
            map.put("user_job_number", "'"+job_number+"'");
         */
    /**
     * to delete a user info in the database
     * @param  jobNumber: the job number of the user which you want to delete.
     * write succeed in console when the process succeed
     */
    public void delete(String jobNumber) throws Exception {

        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            result = mapper.delete(jobNumber);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a particular user info from the database by account name
     * @param  accountName: accountName of user info
     * @return a instance of list which contains User info
     */
    public List<User> findByAccountName(String accountName) throws Exception {

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findByAccountName(accountName);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a particular user info from the database by user id
     * @param  id: id of user info
     * @return a instance of list which contains User info
     */
    public List<User> findById(String id) throws Exception {

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            UserMapper mapper = sqlSession.getMapper(UserMapper.class);
            return mapper.findById(id);
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
        //    System.out.println("processing failed!");
        }
    }
}
///:~