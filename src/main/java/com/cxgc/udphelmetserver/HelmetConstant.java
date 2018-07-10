package com.cxgc.udphelmetserver;

import com.cxgc.Database.model.HelmetDynamicInformation;

import java.util.Date;

public class HelmetConstant {

    public static final int heartRatePosition = 0;
    public static final int lowPressPosition = 6;
    public static final int highPressPosition = 12;
    public static final int currentSpeedPosition = 18;
    public static final int containingElectricityPosition = 24;
    public static final int yearInitialPosition = 36;
    public static final int monthInitialPosition = 37;
    public static final int dayInitialPosition = 38;
    public static final int hourInitialPosition = 39;
    public static final int minuteInitialPosition = 40;
    public static final int secondInitialPosition = 41;
    public static final int helmetIdInitialPosition = 54;
    public static final int GPSInformationInitialPosition = 60;
    public static final int deltaDistanceInitialPosition = 108;
    public static final int helmetMsgIdPosition = 120;
    public static final int ZNMsgIdPosition = 156;

    public static void checkResult(HelmetDynamicInformation helmetDynamicInfo){
        System.out.println("当前时间:" + new Date());
        System.out.println("GPS " + helmetDynamicInfo.getGPSInformation());
        System.out.println("Id " + helmetDynamicInfo.getHelmetId());
        System.out.println("Elec " + helmetDynamicInfo.getContainingElectricity());
        System.out.println("Speed " + helmetDynamicInfo.getCurrentSpeed());
        System.out.println("Distance " + helmetDynamicInfo.getDeltaDistanceSum());
        System.out.println("HeartRate " + helmetDynamicInfo.getHeartRate());
        System.out.println("HighPressure " + helmetDynamicInfo.getHighPressure());
        System.out.println("LowPressure " + helmetDynamicInfo.getLowPressure());
    }
}
