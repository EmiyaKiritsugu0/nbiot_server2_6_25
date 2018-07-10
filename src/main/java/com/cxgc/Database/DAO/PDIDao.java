package com.cxgc.Database.DAO;

import com.cxgc.Database.mapper.DBConnectorSingleton;
import com.cxgc.Database.mapper.PDIMapper;
import com.cxgc.Database.model.ProjectDepartmentInformation;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/3/10.
 * DAO layer which you can use when interacting with database.
 */
public class PDIDao {

    /**
     * to add a project department information to the database
     * @param  projectDepartmentInformation: project department information
     * write succeed in console when the process succeed
     */
    public void add(ProjectDepartmentInformation projectDepartmentInformation) throws Exception{
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            PDIMapper mapper = sqlSession.getMapper(PDIMapper.class);
            result = mapper.add(projectDepartmentInformation);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

    }
    /**
     * to delete a project department information in the database
     * @param  projectName: the project name of the project which you want to delete.
     * write succeed in console when the process succeed
     */
    public void delete(String projectName)throws Exception{
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            PDIMapper mapper = sqlSession.getMapper(PDIMapper.class);
            result = mapper.delete(projectName);
            checkResults(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to alter any column of  the project department information in the database
     * @param  map: a hash map which using method lies below.
     * write succeed in console when the process succeed
     */
    public void update(HashMap map)throws Exception{
        int result;
        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            PDIMapper mapper = sqlSession.getMapper(PDIMapper.class);

            result = mapper.update(map);
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
               map.put("projectName", "'"+projectName+"'");
            */
    /**
     * to find a list of particular project department information from the database by project name
     * @param  projectName: the name of project which you want to query
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<ProjectDepartmentInformation> findByProjectName(String projectName)throws Exception{

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            PDIMapper mapper = sqlSession.getMapper(PDIMapper.class);
            return mapper.findByProjectName(projectName);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular project department information from the database by project province
     * @param  projectProvince: the province of project which you want to query
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<ProjectDepartmentInformation> findByProjectProvince(String projectProvince)throws Exception{

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            PDIMapper mapper = sqlSession.getMapper(PDIMapper.class);
            return mapper.findByProjectProvince(projectProvince);
        } finally {
            sqlSession.close();
        }
    }
    /**
     * to find a list of particular project department information from the database by project city
     * @param  projectCity: the city of project which you want to query
     * @return : a instance of list contains all the info of DeviceStaticInformation which meet the condition
     */
    public List<ProjectDepartmentInformation> findByProjectCity(String projectCity)throws Exception{

        SqlSession sqlSession = DBConnectorSingleton.getInstance().getSessionFactory().openSession();

        try {
            PDIMapper mapper = sqlSession.getMapper(PDIMapper.class);
            return mapper.findByProjectCity(projectCity);
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
       //     System.out.println("processing failed!");
        }
    }
}
///:~