package com.cxgc.tcpserver;

import java.lang.reflect.Field;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * Created by HeRui on 2018/5/25.
 * to collect II data from database
 */
public class IIDataCollection {
    private String iotDeviceIdData = new String("#") ;  //iotID
    private String informationDateData = new String("#") ;//记录时间，年月日
    private String informationTimeData = new String("#") ;//记录时间，时分秒
    private String longitudeData = new String("#") ;  //经度
    private String latitudeData = new String("#") ; //纬度
    private String positionExceptionData = new String("#") ; //位置异常
    private String fuelExceptionData = new String("#") ; //燃料异常
    private String rotationExceptionData = new String("#") ; //旋转异常
    private String certainField = new String("#") ;

    public String getIotDeviceIdData() {
        return iotDeviceIdData;
    }

    public void setIotDeviceIdData(String iotDeviceIdData) {
        this.iotDeviceIdData = iotDeviceIdData;
    }

    public String getInformationDateData() {
        return informationDateData;
    }

    public void setInformationDateData(String informationDateData) {
        this.informationDateData = informationDateData;
    }

    public String getInformationTimeData() {
        return informationTimeData;
    }

    public void setInformationTimeData(String informationTimeData) {
        this.informationTimeData = informationTimeData;
    }

    public String getLongitudeData() {
        return longitudeData;
    }

    public void setLongitudeData(String longitudeData) {
        this.longitudeData = longitudeData;
    }

    public String getLatitudeData() {
        return latitudeData;
    }

    public void setLatitudeData(String latitudeData) {
        this.latitudeData = latitudeData;
    }

    public String getPositionExceptionData() {
        return positionExceptionData;
    }

    public void setPositionExceptionData(String positionExceptionData) {
        this.positionExceptionData = positionExceptionData;
    }

    public String getFuelExceptionData() {
        return fuelExceptionData;
    }

    public void setFuelExceptionData(String fuelExceptionData) {
        this.fuelExceptionData = fuelExceptionData;
    }

    public String getRotationExceptionData() {
        return rotationExceptionData;
    }

    public void setRotationExceptionData(String rotationExceptionData) {
        this.rotationExceptionData = rotationExceptionData;
    }



    public String getCertainField() {
        return certainField;
    }

    public void setCertainField(String certainField) {
        this.certainField = certainField;
    }


    public IIDataCollection() {
    }

    public IIDataCollection(String iotDeviceIdData, String informationDateData, String informationTimeData, String longitudeData, String latitudeData, String positionExceptionData, String fuelExceptionData, String rotationExceptionData, String certainField) {
        this.iotDeviceIdData = iotDeviceIdData;
        this.informationDateData = informationDateData;
        this.informationTimeData = informationTimeData;
        this.longitudeData = longitudeData;
        this.latitudeData = latitudeData;
        this.positionExceptionData = positionExceptionData;
        this.fuelExceptionData = fuelExceptionData;
        this.rotationExceptionData = rotationExceptionData;
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

        Field[] fields = Class.forName("com.cxgc.Database.model.InspectorInformation").getDeclaredFields();//取得所有类成员变量

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
                    if(parameter.equals("positionException") || parameter.equals("fuelException") || parameter.equals("rotationException")) {
                           if (fields[i].getName().equals(parameter)) {
                               certainField = sumData(certainField, fields[i], object);

                           }
                           if (fields[i].getName().equals("iotDeviceId")) {

                               iotDeviceIdData = sumData(iotDeviceIdData, fields[i], object);
                           }
                           if (fields[i].getName().equals("informationDate")) {
                               informationDateData = sumData(informationDateData, fields[i], object);
                           }
                           if (fields[i].getName().equals("informationTime")) {
                               informationTimeData = sumData(informationTimeData, fields[i], object);
                           }
                           if (fields[i].getName().equals("longitude")) {
                               longitudeData = sumData(longitudeData, fields[i], object);
                           }
                           if (fields[i].getName().equals("latitude")) {
                               latitudeData = sumData(latitudeData, fields[i], object);
                           }
                    }
                }
            }else {
                for (int i = 0; i < fields.length; i++) {
                    if (fields[i].getName().equals("iotDeviceId")) {

                        iotDeviceIdData = sumData(iotDeviceIdData, fields[i], object);
                    }
                    if (fields[i].getName().equals("informationDate")) {
                        informationDateData =  sumData(informationDateData, fields[i], object);
                    }
                    if (fields[i].getName().equals("informationTime")) {
                        informationTimeData =  sumData(informationTimeData, fields[i], object);
                    }
                    if (fields[i].getName().equals("longitude")) {
                        longitudeData =  sumData(longitudeData, fields[i], object);
                    }
                    if (fields[i].getName().equals("latitude")) {
                        latitudeData = sumData(latitudeData, fields[i], object);
                    }
                    if (fields[i].getName().equals("positionException")) {
                        positionExceptionData = sumData(positionExceptionData, fields[i], object);
                    }
                    if (fields[i].getName().equals("fuelException")) {
                        fuelExceptionData = sumData(fuelExceptionData, fields[i], object);
                    }
                    if (fields[i].getName().equals("rotationException")) {
                        rotationExceptionData = sumData(rotationExceptionData, fields[i], object);
                    }

                }
            }
        }




            IIDataCollection iiDataCollection = new IIDataCollection(iotDeviceIdData,informationDateData,informationTimeData,
                    longitudeData,latitudeData,positionExceptionData,fuelExceptionData,rotationExceptionData,certainField);


        String response ;

        response = IIDataCollection2XML(iiDataCollection);

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
        for (int j = 0; j < object.size(); j++) {

            try {

                str1 = str1 + field.get(object.get(j)) + "#";

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("error occurred in IIDataCollection" + e);
            }
        }
        return str1;
    }
    /**
     * to parse data to XML string
     * @param  iiDataCollection:  data
     * @return the string in form of XML
     */
    private String IIDataCollection2XML(IIDataCollection iiDataCollection){




        StringBuilder responseSb = new StringBuilder().append("<p0>").append(iiDataCollection.getCertainField()).append("</p0><p1>").append(
                iiDataCollection.getIotDeviceIdData()).append("</p1><p2>").append(
                iiDataCollection.getInformationDateData()).append("</p2><p3>").append(
                iiDataCollection.getInformationTimeData()).append("</p3><p4>").append(
                iiDataCollection.getLongitudeData()).append("</p4><p5>").append(
                iiDataCollection.getLatitudeData()).append("</p5><p6>").append(
                iiDataCollection.getPositionExceptionData()).append("</p6><p7>").append(
                iiDataCollection.getFuelExceptionData()).append("</p7><p8>").append(
                iiDataCollection.getRotationExceptionData()).append("</p8><p9>").append(
                "</p9><p10>").append(
                "</p10>");
        return responseSb.toString();
    }
}
