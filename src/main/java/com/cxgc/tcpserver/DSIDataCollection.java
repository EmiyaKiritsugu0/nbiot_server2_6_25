package com.cxgc.tcpserver;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/5/21.
 * to collect DSI data from database
 */
public class DSIDataCollection {

    private String SIMIdData = new String("#") ;//sim卡id
    private String iotDeviceIdData = new String("#");//IOTid
    private String RFIdData = new String("#");//磁条id
    private String projectInformationData = new String("#");// 项目信息
    private String numberPlateData = new String("#"); //车牌号
    private String engineNumberData = new String("#"); //发动机号
    private String inControlData = new String("#"); //设备是否还在中铁管辖下
    private String startDateData = new String("#");// 进场时间
    private String tempData = new String("#");// 进场时间
    private String certainField = new String("#");

    public String getSIMIdData() {
        return SIMIdData;
    }

    public void setSIMIdData(String SIMIdData) {
        this.SIMIdData = SIMIdData;
    }

    public String getiotDeviceIdData() {
        return iotDeviceIdData;
    }

    public void setiotDeviceIdData(String iotDeviceIdData) {
        this.iotDeviceIdData = iotDeviceIdData;
    }

    public String getRFIdData() {
        return RFIdData;
    }

    public void setRFIdData(String RFIdData) {
        this.RFIdData = RFIdData;
    }

    public String getProjectInformationData() {
        return projectInformationData;
    }

    public void setProjectInformationData(String projectInformationData) {
        this.projectInformationData = projectInformationData;
    }

    public String getNumberPlateData() {
        return numberPlateData;
    }

    public void setNumberPlateData(String numberPlateData) {
        this.numberPlateData = numberPlateData;
    }

    public String getEngineNumberData() {
        return engineNumberData;
    }

    public void setEngineNumberData(String engineNumberData) {
        this.engineNumberData = engineNumberData;
    }

    public String getInControlData() {
        return inControlData;
    }

    public void setInControlData(String inControlData) {
        this.inControlData = inControlData;
    }

    public String getStartDateData() {
        return startDateData;
    }

    public void setStartDateData(String startDateData) {
        this.startDateData = startDateData;
    }

    public String getCertainField() {
        return certainField;
    }

    public void setCertainField(String certainField) {
        this.certainField = certainField;
    }

    public DSIDataCollection() {
    }



    public String getTempData() {
        return tempData;
    }

    public void setTempData(String tempData) {
        this.tempData = tempData;
    }

    public DSIDataCollection(String SIMIdData, String iotDeviceIdData, String RFIdData, String projectInformationData, String numberPlateData, String engineNumberData, String inControlData, String startDateData, String tempData, String certainField) {
        this.SIMIdData = SIMIdData;
        this.iotDeviceIdData = iotDeviceIdData;
        this.RFIdData = RFIdData;
        this.projectInformationData = projectInformationData;
        this.numberPlateData = numberPlateData;
        this.engineNumberData = engineNumberData;
        this.inControlData = inControlData;
        this.startDateData = startDateData;
        this.tempData = tempData;
        this.certainField = certainField;
    }

    /**
     * to rearrange data queried from database
     * @param  object: the list of data
     * @param  parameter : the certain field you want to get, use 'all' to get all of them
     * @return the string in form of XML
     */
    public  String getObjectValue(List<?> object, String parameter) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException {

        Field[] fields = Class.forName("com.cxgc.Database.model.DeviceStaticInformation").getDeclaredFields();//取得所有类成员变量

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
                            certainField =  sumData(certainField, fields[i], object);

                        }
                    }
                }else {
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getName().equals("SIMId")) {

                            SIMIdData = sumData(SIMIdData, fields[i], object);
                        }
                        if (fields[i].getName().equals("iotDeviceId")) {
                            iotDeviceIdData =  sumData(iotDeviceIdData, fields[i], object);
                        }
                        if (fields[i].getName().equals("RFId")) {
                            RFIdData =  sumData(RFIdData, fields[i], object);
                        }
                        if (fields[i].getName().equals("projectInformation")) {
                            projectInformationData =  sumData(projectInformationData, fields[i], object);
                        }
                        if (fields[i].getName().equals("numberPlate")) {
                            numberPlateData = sumData(numberPlateData, fields[i], object);
                        }
                        if (fields[i].getName().equals("engineNumber")) {
                            engineNumberData = sumData(engineNumberData, fields[i], object);
                        }
                        if (fields[i].getName().equals("inControl")) {
                            inControlData = sumData(inControlData, fields[i], object);
                        }
                        if (fields[i].getName().equals("startDate")) {
                            startDateData = sumData(startDateData, fields[i], object);
                        }
                        if (fields[i].getName().equals("temp")) {
                            tempData = sumData(tempData, fields[i], object);
                        }
                    }
                }
        }



        DSIDataCollection dsiDataCollection = new DSIDataCollection(SIMIdData,iotDeviceIdData,RFIdData,
                    projectInformationData,numberPlateData,engineNumberData,inControlData,startDateData,tempData,certainField);


        String response ;

        response = DSIDataCollection2XML(dsiDataCollection);

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
     * @param  dsiDataCollection:  data
     * @return the string in form of XML
     */
    private String DSIDataCollection2XML(DSIDataCollection dsiDataCollection){

        StringBuilder responseSb  = new StringBuilder().append("<p0>").append(dsiDataCollection.getCertainField()).append("</p0><p1>").append(
                dsiDataCollection.getSIMIdData()).append("</p1><p2>").append(
                dsiDataCollection.getiotDeviceIdData()).append("</p2><p3>").append(
                dsiDataCollection.getRFIdData()).append("</p3><p4>").append(
                dsiDataCollection.getProjectInformationData()).append("</p4><p5>").append(
                dsiDataCollection.getNumberPlateData()).append("</p5><p6>").append(
                dsiDataCollection.getEngineNumberData()).append("</p6><p7>").append(
                dsiDataCollection.getInControlData()).append("</p7><p8>").append(
                dsiDataCollection.getStartDateData()).append("</p8><p9>").append(
                dsiDataCollection.getTempData()).append("</p9><p10>").append(
                "</p10>");
        return responseSb.toString();
    }
}
///:~