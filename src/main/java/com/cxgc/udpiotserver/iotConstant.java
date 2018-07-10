package com.cxgc.udpiotserver;

import com.cxgc.Database.model.DeviceDynamicInformation;

public class iotConstant {

    public static final int angleXInitialPosition = 0;
    public static final int angleYInitialPosition = 6;
    public static final int angleZInitialPosition = 12;
    public static final int angularSpeedXInitialPosition = 18;
    public static final int angularSpeedYInitialPosition = 24;
    public static final int angularSpeedZInitialPosition = 30;
    public static final int currentSpeedInitialPosition = 36;
    public static final int xAccelerationInitialPosition = 42;
    public static final int yAccelerationInitialPosition = 48;
    public static final int zAccelerationInitialPosition = 54;
    public static final int containingFuelInitialPosition = 60;
    public static final int containingElectricityInitialPosition = 66;
    public static final int yearInitialPosition = 72;
    public static final int monthInitialPosition = 73;
    public static final int dayInitialPosition = 74;
    public static final int hourInitialPosition = 75;
    public static final int minuteInitialPosition = 76;
    public static final int secondInitialPosition = 77;
    public static final int rotationOrientationInitialPosition = 78;
    public static final int workingFlagInitialPosition = 84;
    public static final int iotDeviceIdInitialPosition = 90;
    public static final int GPSInformationInitialPosition = 96;
    public static final int deltaDistanceInitialPosition = 144;
    public static final int messageIdInitialPosition = 156;
    public static final double speedLimit = 8;

    public static final long jetLag = 28800000;

    public static final double resultUsingRate = 1.1574074074074075E-4;
    public static final int timeInterval = 10000;

    public static void checkResult(DeviceDynamicInformation deviceDynamicInfo){
       /* System.out.println("anglex"+deviceDynamicInfo.getAngleX());
        System.out.println("angley"+deviceDynamicInfo.getAngleY());
        System.out.println("anglez"+deviceDynamicInfo.getAngleZ());
        System.out.println("angularx"+deviceDynamicInfo.getAngularXSpeed());
        System.out.println("angulary"+deviceDynamicInfo.getAngularYSpeed());
        System.out.println("angularz"+deviceDynamicInfo.getAngularZSpeed());
        System.out.println("cuspeed"+deviceDynamicInfo.getCurrentSpeed());
        System.out.println("accex"+deviceDynamicInfo.getxAcceleration());
        System.out.println("accey"+deviceDynamicInfo.getyAcceleration());
        System.out.println("accez"+deviceDynamicInfo.getzAcceleration());*/

        System.out.println("id "+deviceDynamicInfo.getIotDeviceId());
        System .out.println("fuel "+deviceDynamicInfo.getContainingFuel());
        System.out.println("elect "+deviceDynamicInfo.getContainingElectricity());
        System.out.println("date "+deviceDynamicInfo.getInformationDate());
        //System.out.println("time"+deviceDynamicInfo.getInformationTime());
//        System.out.println("orient"+deviceDynamicInfo.isRotationOrientation());
//        System.out.println("flag"+deviceDynamicInfo.isWorkingFlag());
        System.out.println("gps "+deviceDynamicInfo.getGPSInformation());

        System.out.println("deldis "+deviceDynamicInfo.getDeltaDistanceSum());
        System.out.println("delfuel "+deviceDynamicInfo.getDeltaFuel());


    }
}
