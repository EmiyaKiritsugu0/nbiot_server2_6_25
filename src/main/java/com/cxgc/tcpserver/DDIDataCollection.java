package com.cxgc.tcpserver;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/5/21.
 *  to collect DDI data from database
 */
public class DDIDataCollection {

    private String contentTime;
    private String contentDate;
    private String contentData;

    public String getContentTime() {
        return contentTime;
    }

    public void setContentTime(String contentTime) {
        this.contentTime = contentTime;
    }

    public String getContentDate() {
        return contentDate;
    }

    public void setContentDate(String contentDate) {
        this.contentDate = contentDate;
    }

    public String getContentData() {
        return contentData;
    }

    public void setContentData(String contentData) {
        this.contentData = contentData;
    }

    public DDIDataCollection() {

    }

    public DDIDataCollection(String contentTime, String contentDate, String contentData) {
        this.contentTime = contentTime;
        this.contentDate = contentDate;
        this.contentData = contentData;
    }
    /**
     * to rearrange data queried from database
     * @param  object: the list of data
     * @param  parameter : the certain field you want to get
     * @return the string in form of XML
     */
    public  String getObjectValue(List<?> object, String parameter) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        Field[] fields = Class.forName("com.cxgc.Database.model.DeviceDynamicInformation").getDeclaredFields();//取得所有类成员变量

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
        String dataSet = new String("#");
        String dataSetTime = new String("#");
        String dataSetDate = new String("#");

        StringBuilder dataSetSb = new StringBuilder("#");
        StringBuilder dataSetTimeSb = new StringBuilder("#");
        StringBuilder dataSetDateSb = new StringBuilder("#");

        if(!object.isEmpty()){
            for (int i = 0; i < fields.length; i++)
            {
                if(!(parameter.equals("informationDate")||parameter.equals("informationTime")||parameter.equals("all")))
                {
                    if(fields[i].getName().equals(parameter))
                    {
                        //System.out.println("get it");

                        dataSet = sumData(dataSet,fields[i],object);
                    }
                    if(fields[i].getName().equals("informationDate"))
                    {

                        dataSetDate = sumData(dataSetDate,fields[i],object);
                    }
                    if(fields[i].getName().equals("informationTime"))
                    {

                        dataSetTime = sumData(dataSetTime,fields[i],object);
                    }
                }
                else if(parameter.equals("informationDate"))
                {
                    if(fields[i].getName().equals(parameter)) {
                        dataSet = sumData(dataSet,fields[i],object);
                    }

                }
                else if(parameter.equals("informationTime"))
                {
                    if(fields[i].getName().equals(parameter)) {
                        dataSet = sumData(dataSet, fields[i], object);
                    }
                }
                else if(parameter.equals("all"))
                {

                    if(fields[i].getName().equals("containingFuel")) {
                        dataSet = sumData(dataSet, fields[i], object);
                    }
                    if(fields[i].getName().equals("containingElectricity")) {
                        dataSet = sumData(dataSet, fields[i], object);
                    }
                    if(fields[i].getName().equals("GPSInformation")) {
                        dataSet = sumData(dataSet, fields[i], object);
                    }
                    if(fields[i].getName().equals("currentSpeed")) {
                        dataSet = sumData(dataSet, fields[i], object);
                    }

                    if(fields[i].getName().equals("informationDate"))
                    {

                        dataSetDate = sumData(dataSetDate,fields[i],object);
                    }
                    if(fields[i].getName().equals("informationTime"))
                    {

                        dataSetTime = sumData(dataSetTime,fields[i],object);
                    }

                }



            }
        }

        DDIDataCollection ddidataCollection = new DDIDataCollection(dataSetTime,dataSetDate,dataSet);

        String response ;

        response = DDIDataCollection2XML(ddidataCollection);

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

        String str1 = str;
        StringBuilder str1Sb = new StringBuilder().append(str);
        for (int j = 0; j < object.size(); j++) {

            try {

                //str1 = str1 + field.get(object.get(j)) + "#";
                str1Sb.append(field.get(object.get(j))).append("#");

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error occured in DSIDataCollection" + e);
            }
        }
        str1 = str1Sb.toString();
        return str1;
    }
    /**
     * to parse data to XML string
     * @param  ddidataCollection:  data
     * @return the string in form of XML
     */
    private String DDIDataCollection2XML(DDIDataCollection ddidataCollection){

        String response ;

        response = "<p0>"+ddidataCollection.getContentData()+"</p0><p1>"+
                ddidataCollection.getContentDate()+"</p1><p2>"+
                ddidataCollection.getContentTime()+"</p2><p3>"+
                "</p3><p4>"+
                "</p4><p5>"+
                "</p5><p6>"+
                "</p6><p7>"+
                "</p7><p8>"+
                "</p8><p9>"+
                "</p9><p10>"+
                "</p10>";

        return response;
    }


}
///:~
