package com.cxgc.tcpserver;

import java.lang.reflect.Field;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/5/22.
 * to collect User data from database
 */
public class UserDataCollection {



    private String userAccountNameData = new String("#") ;
    private String userActualNameData = new String("#") ;
    private String userJobNumberData= new String("#") ;
    private String userPriorityLevelData= new String("#") ;
    private String userSexData= new String("#") ;// true represents male while false represents female.
    private String userPositionData= new String("#") ;
    private String userCompanyData= new String("#") ;
    private String userIdData= new String("#") ;
    private String userEmailData= new String("#") ;
    private String userProjectData= new String("#") ;
    private String certainFieldData= new String("#") ;

    public String getUserAccountNameData() {
        return userAccountNameData;
    }

    public void setUserAccountNameData(String userAccountNameData) {
        this.userAccountNameData = userAccountNameData;
    }

    public String getUserActualNameData() {
        return userActualNameData;
    }

    public void setUserActualNameData(String userActualNameData) {
        this.userActualNameData = userActualNameData;
    }

    public String getUserJobNumberData() {
        return userJobNumberData;
    }

    public void setUserJobNumberData(String userJobNumberData) {
        this.userJobNumberData = userJobNumberData;
    }

    public String getUserPriorityLevelData() {
        return userPriorityLevelData;
    }

    public void setUserPriorityLevelData(String userPriorityLevelData) {
        this.userPriorityLevelData = userPriorityLevelData;
    }

    public String getUserSexData() {
        return userSexData;
    }

    public void setUserSexData(String userSexData) {
        this.userSexData = userSexData;
    }

    public String getUserPositionData() {
        return userPositionData;
    }

    public void setUserPositionData(String userPositionData) {
        this.userPositionData = userPositionData;
    }

    public String getUserCompanyData() {
        return userCompanyData;
    }

    public void setUserCompanyData(String userCompanyData) {
        this.userCompanyData = userCompanyData;
    }

    public String getUserIdData() {
        return userIdData;
    }

    public void setUserIdData(String userIdData) {
        this.userIdData = userIdData;
    }

    public String getUserEmailData() {
        return userEmailData;
    }

    public void setUserEmailData(String userEmailData) {
        this.userEmailData = userEmailData;
    }

    public String getUserProjectData() {
        return userProjectData;
    }

    public void setUserProjectData(String userProjectData) {
        this.userProjectData = userProjectData;
    }

    public String getCertainFieldData() {
        return certainFieldData;
    }

    public void setCertainFieldData(String certainFieldData) {
        this.certainFieldData = certainFieldData;
    }

    public UserDataCollection() {
    }

    public UserDataCollection(String userAccountNameData, String userActualNameData, String userJobNumberData,
                              String userPriorityLevelData, String userSexData, String userPositionData,
                              String userCompanyData, String userIdData, String userEmailData, String userProjectData,
                              String certainFieldData) {
        this.userAccountNameData = userAccountNameData;
        this.userActualNameData = userActualNameData;
        this.userJobNumberData = userJobNumberData;
        this.userPriorityLevelData = userPriorityLevelData;
        this.userSexData = userSexData;
        this.userPositionData = userPositionData;
        this.userCompanyData = userCompanyData;
        this.userIdData = userIdData;
        this.userEmailData = userEmailData;
        this.userProjectData = userProjectData;
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

        Field[] fields = Class.forName("com.cxgc.Database.model.User").getDeclaredFields();//取得所有类成员变量

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
                        if (fields[i].getName().equals("userAccountName")) {
                            userAccountNameData = sumData(userAccountNameData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userActualName")) {
                            userActualNameData = sumData(userActualNameData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userJobNumber")) {
                            userJobNumberData = sumData(userJobNumberData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userPriorityLevel")) {
                            userPriorityLevelData = sumData(userPriorityLevelData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userSex")) {
                            userSexData = sumData(userSexData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userPosition")) {
                            userPositionData = sumData(userPositionData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userCompany")) {
                            userCompanyData = sumData(userCompanyData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userId")) {
                            userIdData = sumData(userIdData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userEmail")) {
                            userEmailData = sumData(userEmailData, fields[i], object);
                        }
                        if (fields[i].getName().equals("userProject")) {
                            userProjectData = sumData(userProjectData, fields[i], object);
                        }

                    }
                }
        }


        UserDataCollection userDataCollection = new UserDataCollection(userAccountNameData,userActualNameData,
                userJobNumberData,userPriorityLevelData,userSexData,userPositionData,userCompanyData,userIdData,userEmailData,userProjectData,certainFieldData);


        String response ;

        response = UserDataCollection2XML(userDataCollection);

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

        StringBuilder strSb = new StringBuilder();
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
     * @param  userDataCollection:  data
     * @return the string in form of XML
     */
    private String UserDataCollection2XML(UserDataCollection userDataCollection){

        StringBuilder responseSb  = new StringBuilder().append("<p0>"
                ).append(userDataCollection.getCertainFieldData()).append("</p0><p1>").append(
                userDataCollection.getUserAccountNameData()).append("</p1><p2>").append(
                userDataCollection.getUserActualNameData()).append("</p2><p3>").append(
                userDataCollection.getUserJobNumberData()).append("</p3><p4>").append(
                userDataCollection.getUserPriorityLevelData()).append("</p4><p5>").append(
                userDataCollection.getUserSexData()).append("</p5><p6>").append(
                userDataCollection.getUserPositionData()).append("</p6><p7>").append(
                userDataCollection.getUserCompanyData()).append("</p7><p8>").append(
                userDataCollection.getUserIdData()).append("</p8><p9>").append(
                userDataCollection.getUserEmailData()).append("</p9><p10>").append(
                userDataCollection.getUserProjectData()).append("</p10>");
        return responseSb.toString();
    }
}
///:~