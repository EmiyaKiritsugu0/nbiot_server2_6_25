package com.cxgc.Database.model;

/**
 * Created by HeRui on 2018/3/7.
 */
public class User {

    private String userAccountName ;
    private String userActualName ;
    private String userJobNumber;
    private String userPassword;
    private int userPriorityLevel;
    private Boolean userSex;// true represents male while false represents female.
    private String userPosition;
    private String userCompany;
    private String userId;
    private String userEmail;
    private String userProject;
    //用户名，姓名，工号，密码，权限等级（身份），性别，职位，所属单位，ID号，邮箱，项目部

    public User(){
        super();
    }

    public User(String userAccountName, String userActualName, String userJobNumber, String userPassword, int userPriorityLevel, Boolean userSex, String userPosition, String userCompany, String userId, String userEmail, String userProject) {
        this.userAccountName = userAccountName;
        this.userActualName = userActualName;
        this.userJobNumber = userJobNumber;
        this.userPassword = userPassword;
        this.userPriorityLevel = userPriorityLevel;
        this.userSex = userSex;
        this.userPosition = userPosition;
        this.userCompany = userCompany;
        this.userId = userId;
        this.userEmail = userEmail;
        this.userProject = userProject;
    }

    // getters and setters

    public String getUserAccountName() {
        return userAccountName;
    }

    public void setUserAccountName(String userAccountName) {
        this.userAccountName = userAccountName;
    }

    public String getUserActualName() {
        return userActualName;
    }

    public void setUserActualName(String userActualName) {
        this.userActualName = userActualName;
    }

    public String getUserJobNumber() {
        return userJobNumber;
    }

    public void setUserJobNumber(String userJobNumber) {
        this.userJobNumber = userJobNumber;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public int getUserPriorityLevel() {
        return userPriorityLevel;
    }

    public void setUserPriorityLevel(int userPriorityLevel) {
        this.userPriorityLevel = userPriorityLevel;
    }

    public Boolean getUserSex() {
        return userSex;
    }

    public void setUserSex(Boolean userSex) {
        this.userSex = userSex;
    }

    public String getUserPosition() {
        return userPosition;
    }

    public void setUserPosition(String userPosition) {
        this.userPosition = userPosition;
    }

    public String getUserCompany() {
        return userCompany;
    }

    public void setUserCompany(String userCompany) {
        this.userCompany = userCompany;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserProject() {
        return userProject;
    }

    public void setUserProject(String userProject) {
        this.userProject = userProject;
    }

    // end


}
