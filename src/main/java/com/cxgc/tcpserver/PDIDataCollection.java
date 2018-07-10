package com.cxgc.tcpserver;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/5/21.
 * to collect PDI data from database
 */
public class PDIDataCollection {

    private String projectNameData = new String("#");
    private String projectProvinceData = new String("#");
    private String projectCityData = new String("#");
    private String projectStateData = new String("#");
    private String projectDetailData = new String("#");
    private String projectStartDateData = new String("#");
    private String projectEndDateData = new String("#");
    private String projectStaffAmountData = new String("#");
    private String projectDeviceTypeAmountData = new String("#");
    private String certainFieldData = new String("#");


    public String getProjectNameData() {
        return projectNameData;
    }

    public void setProjectNameData(String projectNameData) {
        this.projectNameData = projectNameData;
    }

    public String getProjectProvinceData() {
        return projectProvinceData;
    }

    public void setProjectProvinceData(String projectProvinceData) {
        this.projectProvinceData = projectProvinceData;
    }

    public String getProjectCityData() {
        return projectCityData;
    }

    public void setProjectCityData(String projectCityData) {
        this.projectCityData = projectCityData;
    }

    public String getProjectStateData() {
        return projectStateData;
    }

    public void setProjectStateData(String projectStateData) {
        this.projectStateData = projectStateData;
    }

    public String getProjectDetailData() {
        return projectDetailData;
    }

    public void setProjectDetailData(String projectDetailData) {
        this.projectDetailData = projectDetailData;
    }

    public String getProjectStartDateData() {
        return projectStartDateData;
    }

    public void setProjectStartDateData(String projectStartDateData) {
        this.projectStartDateData = projectStartDateData;
    }

    public String getProjectEndDateData() {
        return projectEndDateData;
    }

    public void setProjectEndDateData(String projectEndDateData) {
        this.projectEndDateData = projectEndDateData;
    }

    public String getProjectStaffAmountData() {
        return projectStaffAmountData;
    }

    public void setProjectStaffAmountData(String projectStaffAmountData) {
        this.projectStaffAmountData = projectStaffAmountData;
    }

    public String getProjectDeviceTypeAmountData() {
        return projectDeviceTypeAmountData;
    }

    public void setProjectDeviceTypeAmountData(String projectDeviceTypeAmountData) {
        this.projectDeviceTypeAmountData = projectDeviceTypeAmountData;
    }

    public String getCertainFieldData() {
        return certainFieldData;
    }

    public void setCertainFieldData(String certainFieldData) {
        this.certainFieldData = certainFieldData;
    }


    public PDIDataCollection() {
    }

    public PDIDataCollection(String projectNameData, String projectProvinceData, String projectCityData,
                             String projectStateData, String projectDetailData, String projectStartDateData,
                             String projectEndDateData, String projectStaffAmountData, String projectDeviceTypeAmountData,
                             String certainFieldData) {
        this.projectNameData = projectNameData;
        this.projectProvinceData = projectProvinceData;
        this.projectCityData = projectCityData;
        this.projectStateData = projectStateData;
        this.projectDetailData = projectDetailData;
        this.projectStartDateData = projectStartDateData;
        this.projectEndDateData = projectEndDateData;
        this.projectStaffAmountData = projectStaffAmountData;
        this.projectDeviceTypeAmountData = projectDeviceTypeAmountData;
        this.certainFieldData = certainFieldData;
    }
    /**
     * to rearrange data queried from database
     * @param  object: the list of data
     * @param  parameter : the certain field you want to get, use 'all' to get all of them
     * @return the string in form of XML
     */
    public  String getObjectValue(List<?> object, String parameter) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        Field[] fields = Class.forName("com.cxgc.Database.model.ProjectDepartmentInformation").getDeclaredFields();//取得所有类成员变量

        //遍历所有类成员变量
//        for (Field f:fields
//             ) {
//            System.out.println(f.getName());
//        }
//        //遍历所有方法
//        Method[] methods = c.getMethods();
//        for (Method m:methods
//             ) {
//            System.out.println(m.getName());
//        }


        //取消每个属性的安全检查
        for(Field f:fields){
            f.setAccessible(true);
        }



        if(!object.isEmpty()) {

                if (!(parameter.equals("all"))) {
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getName().equals(parameter)) {
                            certainFieldData = sumData(certainFieldData, fields[i], object);
                        }
                    }
                }
                else{
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getName().equals("projectName")) {
                            projectNameData = sumData(projectNameData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectProvince")) {
                            projectProvinceData = sumData(projectProvinceData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectCity")) {
                            projectCityData = sumData(projectCityData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectEndDate")) {
                            projectEndDateData = sumData(projectEndDateData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectState")) {
                            projectStateData = sumData(projectStateData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectDetail")) {
                            projectDetailData = sumData(projectDetailData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectStartDate")) {
                            projectStartDateData = sumData(projectStartDateData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectStaffAmount")) {
                            projectStaffAmountData = sumData(projectStaffAmountData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectDeviceTypeAmount")) {
                            projectDeviceTypeAmountData = sumData(projectDeviceTypeAmountData, fields[i], object);
                        }
                    }
                }
        }



        PDIDataCollection pdiDataCollection = new PDIDataCollection(projectNameData,projectProvinceData,
                projectCityData,projectStateData,projectDetailData,projectStartDateData,projectEndDateData,projectStaffAmountData,projectDeviceTypeAmountData,certainFieldData);


        String response = PDIDataCollection2XML(pdiDataCollection);



        return response;


    }
    /**
     * to summarize data
     * @param  str:  the input string starting with '#'
     * @param  field: the exact field you wang to get
     * @param  object: the list of data
     * @return a string contains the certain field information
     */
    private String sumData(String str , Field field , List<?> object){

        StringBuilder strSb = new StringBuilder().append(str);
        for (int j = 0; j < object.size(); j++) {

            try {
                //str = str + field.get(object.get(j)) + "#";
                strSb.append(field.get(object.get(j))).append("#");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error occured in DSIDataCollection" + e);
            }
        }

        return strSb.toString();
    }
    /**
     * to parse data to XML string
     * @param  pdiDataCollection:  data
     * @return the string in form of XML
     */
    private String PDIDataCollection2XML(PDIDataCollection pdiDataCollection){


        StringBuilder responseSb = new StringBuilder().append("<p0>").append(pdiDataCollection.getCertainFieldData()).append("</p0><p1>").append(
                pdiDataCollection.getProjectNameData()).append("</p1><p2>").append(
                pdiDataCollection.getProjectProvinceData()).append("</p2><p3>").append(
                pdiDataCollection.getProjectCityData()).append( "</p3><p4>").append(
                pdiDataCollection.getProjectEndDateData()).append("</p4><p5>").append(
                pdiDataCollection.getProjectStateData()).append("</p5><p6>").append(
                pdiDataCollection.getProjectDetailData()).append("</p6><p7>").append(
                pdiDataCollection.getProjectStartDateData()).append("</p7><p8>").append(
                pdiDataCollection.getProjectStaffAmountData()).append("</p8><p9>").append(
                pdiDataCollection.getProjectDeviceTypeAmountData()).append("</p9><p10>").append(
                "</p10>");

        return responseSb.toString();
    }
}

///:~