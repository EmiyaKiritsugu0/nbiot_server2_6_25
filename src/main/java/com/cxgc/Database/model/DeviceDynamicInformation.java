package com.cxgc.Database.model;

import java.sql.Date;
import java.sql.Time;


public class DeviceDynamicInformation {

    private double angleX;//x轴角度
    private double angleY;//y轴角度
    private double angleZ;//z轴角度
    private double angularXSpeed; //x轴角速度
    private double angularYSpeed; //y轴角速度
    private double angularZSpeed;//z轴角速度
//    private double angularXAcceleration;//x轴角加速度
//    private double angularYAcceleration;//y轴角加速度
//    private double angularZAcceleration;//z轴角加速度
    private double currentSpeed;//线速度
    private double xAcceleration;//x轴加速度
    private double yAcceleration;//y轴加速度
    private double zAcceleration;//z轴加速度
    private double containingFuel;//剩余燃料
    private double containingElectricity;//剩余电量
    private Date informationDate;//记录时间，年月日
    private Time informationTime;//记录时间，时分秒
    private boolean rotationOrientation;//罐车转筒旋转方向，顺时针为正
    private boolean workingFlag;//设备是否工作,工作为正
    private String iotDeviceId;//设备id
    private String GPSInformation;//GPS信息
    private double deltaDistance;//相对运动距离
    private double deltaFuel;//相对燃料消耗

    public double getDeltaFuel() {
        return deltaFuel;
    }

    public void setDeltaFuel(double deltaFuel) {
        this.deltaFuel = deltaFuel;
    }



    public double getDeltaDistanceSum() {
        return deltaDistance;
    }

    public void setDeltaDistanceSum(double deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    public double getAngleX() {
        return angleX;
    }

    public void setAngleX(double angleX) {
        this.angleX = angleX;
    }

    public double getAngleY() {
        return angleY;
    }

    public void setAngleY(double angleY) {
        this.angleY = angleY;
    }

    public double getAngleZ() {
        return angleZ;
    }

    public void setAngleZ(double angleZ) {
        this.angleZ = angleZ;
    }

    public double getAngularXSpeed() {
        return angularXSpeed;
    }

    public void setAngularXSpeed(double angularXSpeed) {
        this.angularXSpeed = angularXSpeed;
    }

    public double getAngularYSpeed() {
        return angularYSpeed;
    }

    public void setAngularYSpeed(double angularYSpeed) {
        this.angularYSpeed = angularYSpeed;
    }

    public double getAngularZSpeed() {
        return angularZSpeed;
    }

    public void setAngularZSpeed(double angularZSpeed) {
        this.angularZSpeed = angularZSpeed;
    }

    public double getCurrentSpeed() {
        return currentSpeed;
    }

    public void setCurrentSpeed(double currentSpeed) {
        this.currentSpeed = currentSpeed;
    }

    public double getxAcceleration() {
        return xAcceleration;
    }

    public void setxAcceleration(double xAcceleration) {
        this.xAcceleration = xAcceleration;
    }

    public double getyAcceleration() {
        return yAcceleration;
    }

    public void setyAcceleration(double yAcceleration) {
        this.yAcceleration = yAcceleration;
    }

    public double getzAcceleration() {
        return zAcceleration;
    }

    public void setzAcceleration(double zAcceleration) {
        this.zAcceleration = zAcceleration;
    }

    public double getContainingFuel() {
        return containingFuel;
    }

    public void setContainingFuel(double containingFuel) {
        this.containingFuel = containingFuel;
    }

    public double getContainingElectricity() {
        return containingElectricity;
    }

    public void setContainingElectricity(double containingElectricity) {
        this.containingElectricity = containingElectricity;
    }

    public Date getInformationDate() {
        return informationDate;
    }

    public void setInformationDate(Date informationDate) {
        this.informationDate = informationDate;
    }

    public Time getInformationTime() {
        return informationTime;
    }

    public void setInformationTime(Time informationTime) {
        this.informationTime = informationTime;
    }

    public boolean isRotationOrientation() {
        return rotationOrientation;
    }

    public void setRotationOrientation(boolean rotationOrientation) {
        this.rotationOrientation = rotationOrientation;
    }

    public boolean isWorkingFlag() {
        return workingFlag;
    }

    public void setWorkingFlag(boolean workingFlag) {
        this.workingFlag = workingFlag;
    }

    public String getIotDeviceId() {
        return iotDeviceId;
    }

    public void setIotDeviceId(String iotDeviceId) {
        this.iotDeviceId = iotDeviceId;
    }

    public String getGPSInformation() {
        return GPSInformation;
    }

    public void setGPSInformation(String GPSInformation) {
        this.GPSInformation = GPSInformation;
    }

    public DeviceDynamicInformation(){
        super();
    }

    public DeviceDynamicInformation(double angleX, double angleY, double angleZ, double angularXSpeed, double angularYSpeed, double angularZSpeed, double currentSpeed,
                                    double xAcceleration, double yAcceleration, double zAcceleration, double containingFuel, double containingElectricity, Date informationDate,
                                    Time informationTime, boolean rotationOrientation, boolean workingFlag, String iotDeviceId, String GPSInformation, double deltaDistance,
                                    double deltaFuel) {
        this.angleX = angleX;
        this.angleY = angleY;
        this.angleZ = angleZ;
        this.angularXSpeed = angularXSpeed;
        this.angularYSpeed = angularYSpeed;
        this.angularZSpeed = angularZSpeed;
        this.currentSpeed = currentSpeed;
        this.xAcceleration = xAcceleration;
        this.yAcceleration = yAcceleration;
        this.zAcceleration = zAcceleration;
        this.containingFuel = containingFuel;
        this.containingElectricity = containingElectricity;
        this.informationDate = informationDate;
        this.informationTime = informationTime;
        this.rotationOrientation = rotationOrientation;
        this.workingFlag = workingFlag;
        this.iotDeviceId = iotDeviceId;
        this.GPSInformation = GPSInformation;
        this.deltaDistance = deltaDistance;
        this.deltaFuel = deltaFuel;
    }
}
