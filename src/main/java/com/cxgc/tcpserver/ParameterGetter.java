package com.cxgc.tcpserver;

import com.cxgc.Database.DAO.DaoUtil;

import java.util.ArrayList;
import java.util.List;
//:
/**
 * Created by HeRui on 2018/5/21.
 * to dispatch command to different getter
 */
public class ParameterGetter {
    /**
     * to get DDI parameters
     * @param  iotDeviceId:  the device id to distinguish them from each other
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp :the particular time(date) stamp which the information stop with
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information stop with
     * @return a list of parameter corresponding to the command
     */
        public List<Object> getDDIParametersFromCommand (String iotDeviceId, String startDateStamp,
                                                   String stopDateStamp, String startTimeStamp, String stopTimeStamp){
        List<Object> args = new ArrayList<Object>();
        if (!iotDeviceId.equals("empty")) {
            args.add(iotDeviceId);
        }
        if (!startDateStamp.equals("empty")) {
            args.add(DaoUtil.strToDate(startDateStamp));
        }
        if (!stopDateStamp.equals("empty")) {
            args.add(DaoUtil.strToDate(stopDateStamp));
        }
        if (!startTimeStamp.equals("empty")) {
            args.add(DaoUtil.strToTime(startTimeStamp));
        }
        if (!stopTimeStamp.equals("empty")) {
            args.add(DaoUtil.strToTime(stopTimeStamp));
        }
//        for(Object o :args)
//        {
//            System.out.println(o);
//        }
        return args;

    }
    /**
     * to get DSI parameters
     * @param  iotDeviceId:  the device id to distinguish them from each other
     * @param  SIMId: the id of SIM card along with the device
     * @param  RFId: the magnet id along with the device
     * @param  projectInformation:  to determine which project the device belongs to
     * @param  numberPlate: the number plate of the vehicle which fixed with device
     * @param  inControl: to determine whether the device is still governed by the company
     * @return a list of parameter corresponding to the command
     */
    public List<Object> getDSIParametersFromCommand (String iotDeviceId, String SIMId, String RFId,
                                                      String projectInformation, String numberPlate, String inControl){
        List<Object> args = new ArrayList<Object>();
        if (!iotDeviceId.equals("empty")) {
            args.add(iotDeviceId);
        }
        if (!SIMId.equals("empty")) {
            args.add(SIMId);
        }
        if (!RFId.equals("empty")) {
            args.add(RFId);
        }
        if (!projectInformation.equals("empty")) {
            args.add(projectInformation);
        }
        if (!numberPlate.equals("empty")) {
            args.add(numberPlate);
        }
        if (!inControl.equals("empty")) {
            args.add(DaoUtil.strToBoolean(inControl));
        }
//        for(Object o :list)
//        {
//            System.out.println(o);
//        }
        return args;

    }
    /**
     * to get PDI parameters
     * @param  projectName:  the project name
     * @param  projectProvince: the province the project located in
     * @param  projectCity: the city the project located at
     * @return a list of parameter corresponding to the command
     */
    public List<Object> getPDIParametersFromCommand (String projectName, String projectProvince, String projectCity){
        List<Object> args = new ArrayList<Object>();
        if (!projectName.equals("empty")) {
            args.add(projectName);
        }
        if (!projectProvince.equals("empty")) {
            args.add(projectProvince);
        }
        if (!projectCity.equals("empty")) {
            args.add(projectCity);
        }

//        for(Object o :list)
//        {
//            System.out.println(o);
//        }
        return args;

    }
    /**
     * to get SI parameters
     * @param  iotDeviceId:  the device id to distinguish them from each other
     * @param  currentDate: the exact date when the data summarized
     * @return a list of parameter corresponding to the command
     */
    public List<Object> getSIParametersFromCommand (String iotDeviceId, String currentDate){
        List<Object> args = new ArrayList<Object>();
        if (!iotDeviceId.equals("empty")) {
            args.add(iotDeviceId);
        }
        if (!currentDate.equals("empty")) {
            args.add(DaoUtil.strToDate(currentDate));
        }


//        for(Object o :list)
//        {
//            System.out.println(o);
//        }
        return args;

    }
    /**
     * to get SI parameters
     * @return a list of parameter corresponding to the command
     * caution: there is not any need of parameter to generate since we want to get all of them
     */
    public List<Object> getUserParametersFromCommand (){
        List<Object> args = new ArrayList<Object>();

        return args;

    }
    /**
     * to get II parameters
     * @param  iotDeviceId:  the device id to distinguish them from each other
     * @param  startDateStamp: the particular time(date) stamp which the information start with
     * @param stopDateStamp :the particular time(date) stamp which the information stop with
     * @param startTimeStamp: the particular time(hour) stamp which the information start with
     * @param stopTimeStamp: the particular time(hour) stamp which the information stop with
     * @return a list of parameter corresponding to the command
     */
    public List<Object> getIIParametersFromCommand (String iotDeviceId, String startDateStamp,
                                                     String stopDateStamp, String startTimeStamp, String stopTimeStamp){
        List<Object> args = new ArrayList<Object>();
        if (!iotDeviceId.equals("empty")) {
            args.add(iotDeviceId);
        }
        if (!startDateStamp.equals("empty")) {
            args.add(DaoUtil.strToDate(startDateStamp));
        }
        if (!stopDateStamp.equals("empty")) {
            args.add(DaoUtil.strToDate(stopDateStamp));
        }
        if (!startTimeStamp.equals("empty")) {
            args.add(DaoUtil.strToTime(startTimeStamp));
        }
        if (!stopTimeStamp.equals("empty")) {
            args.add(DaoUtil.strToTime(stopTimeStamp));
        }
//        for(Object o :args)
//        {
//            System.out.println(o);
//        }
        return args;

    }
}
///:~