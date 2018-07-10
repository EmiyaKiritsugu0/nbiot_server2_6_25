package com.cxgc.tcpserver;

import java.lang.reflect.Field;
import java.sql.Time;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/5/21.
 * to collect SI data from database
 */
public class SIDataCollection {
    private String iotDeviceIdData = new String("#");
    private String currentDateData = new String("#");
    private String dailyFuelCostData = new String("#");
    private String dailyDistanceData = new String("#");
    private String dailyRunTimeData = new String("#");
    private String dailyUsingRateData = new String("#");
    private String certainFieldData = new String("#");

    public String getiotDeviceIdData() {
        return iotDeviceIdData;
    }

    public void setiotDeviceIdData(String iotDeviceIdData) {
        this.iotDeviceIdData = iotDeviceIdData;
    }

    public String getCurrentDateData() {
        return currentDateData;
    }

    public void setCurrentDateData(String currentDateData) {
        this.currentDateData = currentDateData;
    }

    public String getDailyFuelCostData() {
        return dailyFuelCostData;
    }

    public void setDailyFuelCostData(String dailyFuelCostData) {
        this.dailyFuelCostData = dailyFuelCostData;
    }

    public String getDailyDistanceData() {
        return dailyDistanceData;
    }

    public void setDailyDistanceData(String dailyDistanceData) {
        this.dailyDistanceData = dailyDistanceData;
    }

    public String getDailyRunTimeData() {
        return dailyRunTimeData;
    }

    public void setDailyRunTimeData(String dailyRunTimeData) {
        this.dailyRunTimeData = dailyRunTimeData;
    }

    public String getDailyUsingRateData() {
        return dailyUsingRateData;
    }

    public void setDailyUsingRateData(String dailyUsingRateData) {
        this.dailyUsingRateData = dailyUsingRateData;
    }

    public String getCertainFieldData() {
        return certainFieldData;
    }

    public void setCertainFieldData(String certainFieldData) {
        this.certainFieldData = certainFieldData;
    }

    public SIDataCollection() {
    }

    public SIDataCollection(String iotDeviceIdData, String currentDateData, String dailyFuelCostData,
                            String dailyDistanceData, String dailyRunTimeData, String dailyUsingRateData,
                            String certainFieldData) {
        this.iotDeviceIdData = iotDeviceIdData;
        this.currentDateData = currentDateData;
        this.dailyFuelCostData = dailyFuelCostData;
        this.dailyDistanceData = dailyDistanceData;
        this.dailyRunTimeData = dailyRunTimeData;
        this.dailyUsingRateData = dailyUsingRateData;
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

        Field[] fields = Class.forName("com.cxgc.Database.model.StaticInformation").getDeclaredFields();//取得所有类成员变量

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
                            sumData(certainFieldData, fields[i], object);

                        }
                    }
                }
                else {
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getName().equals("iotDeviceId")) {
                            iotDeviceIdData = sumData(iotDeviceIdData, fields[i], object);
                        }
                        if (fields[i].getName().equals("currentDate")) {
                            currentDateData = sumData(currentDateData, fields[i], object);
                        }
                        if (fields[i].getName().equals("dailyFuelCost")) {
                            dailyFuelCostData = sumData(dailyFuelCostData, fields[i], object);
                        }
                        if (fields[i].getName().equals("dailyDistance")) {
                            dailyDistanceData = sumData(dailyDistanceData, fields[i], object);
                        }
                        if (fields[i].getName().equals("dailyRunTime")) {
                            dailyRunTimeData = sumData(dailyRunTimeData, fields[i], object);
                        }
                        if (fields[i].getName().equals("dailyUsingRate")) {
                            dailyUsingRateData = sumData(dailyUsingRateData, fields[i], object);
                        }

                    }
                }
        }


        SIDataCollection siDataCollection = new SIDataCollection(iotDeviceIdData,currentDateData,
                dailyFuelCostData,dailyDistanceData,dailyRunTimeData,dailyUsingRateData,certainFieldData);


        String response = SIDataCollection2XML(siDataCollection);



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
     * @param  siDataCollection:  data
     * @return the string in form of XML
     */
    private String SIDataCollection2XML(SIDataCollection siDataCollection){

        StringBuilder responseSb = new StringBuilder()
                .append("<p0>").append(siDataCollection.getCertainFieldData()).append("</p0><p1>").append(
                siDataCollection.getiotDeviceIdData()).append("</p1><p2>").append(
                siDataCollection.getCurrentDateData()).append("</p2><p3>").append(
                siDataCollection.getDailyFuelCostData()).append("</p3><p4>").append(
                siDataCollection.getDailyDistanceData()).append("</p4><p5>").append(
                siDataCollection.getDailyRunTimeData()).append("</p5><p6>").append(
                siDataCollection.getDailyUsingRateData()).append("</p6><p7>").append(
                "</p7><p8>").append(
                "</p8><p9>").append(
                "</p9><p10>").append(
                "</p10>");

        return responseSb.toString();
    }
}
///:~