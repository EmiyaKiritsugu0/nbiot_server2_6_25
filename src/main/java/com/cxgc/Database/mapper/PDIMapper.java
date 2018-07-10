package com.cxgc.Database.mapper;

//:
import com.cxgc.Database.model.ProjectDepartmentInformation;

import java.util.HashMap;
import java.util.List;

/**
 * Created by HeRui on 2018/3/10.
 * to create a mapper which is implement in Mapper,xml
 */
public interface PDIMapper {

    public int add(ProjectDepartmentInformation projectDepartmentInformation);//添加一条项目信息

    public int delete(String projectName);//按照项目名称删除某一个项目信息

    public int update(HashMap map);//修改项目信息某一字段信息

    public List<ProjectDepartmentInformation> findByProjectName(String projectName);//按照项目名称查询项目部信息

    public List<ProjectDepartmentInformation> findByProjectProvince(String projectProvince);//按照项目所在市查询项目部信息

    public List<ProjectDepartmentInformation> findByProjectCity(String projectCity);//按照项目所在省查询项目部信息



}
///:~