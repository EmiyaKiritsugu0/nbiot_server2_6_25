package com.cxgc.Database.mapper;
//:
/**
 * Created by HeRui on 2018/3/10.
 * to create a mapper which is implement in Mapper,xml
 */
import com.cxgc.Database.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserMapper {

    public int add(User user);//增加用户，用户的工号不能相同

    public int update(HashMap map);//修改用户某一字段信息，不能改工号


    public int delete(String jobNumber);//按照工号删除某一个用户

    public List<User> findByJobNumber(String jobNumber);//按照工号查找某一个用户信息

    public List<User> findAll();//找到所有用户信息

    public List<User> findByAccountName(String accountName);//按照用户名搜索

    public List<User> findById(String id);//按照用户id搜索

}
///:~