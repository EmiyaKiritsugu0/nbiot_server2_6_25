package com.cxgc.udpiotserver;

import com.cxgc.Database.DAO.DaoUtil;
import com.cxgc.Database.DAO.SIDao;
import com.cxgc.Database.model.StaticInformation;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DataSummarizeZHB {
    private double increasFuel = 0;
    private double decreasFuel = 0;
    private int decreasCounter = 0;
    private int increasCounter = 0;

    //需要执行：
    public void updateDataSummarizingMaterials(DataMaterialZHB dataMaterialZHB){
        if(dataMaterialZHB.getDeltaFuel() > 0){
            
            increasFuel += dataMaterialZHB.getDeltaFuel();
            increasCounter++;

            //重置油量减少计数器
            decreasCounter = 0;
            decreasFuel = 0.0;
            

        }else if(dataMaterialZHB.getDeltaFuel() < 0){
            
            decreasFuel += dataMaterialZHB.getDeltaFuel();
            decreasCounter++;
            updateSql(dataMaterialZHB, dataMaterialZHB.getDeltaFuel());
            System.out.println("");
            
            //可能为加油或抖动
            if(increasCounter > 0){
                //在加油
                if(increasCounter > 2){//
                    increasCounter = 0;
                    increasFuel = 0;
                    System.out.println("加油");
                }else{//抖动
                    increasCounter = 0;
                    updateSql(dataMaterialZHB, increasFuel);
                    increasFuel = 0;
                }
            }

        }else if(dataMaterialZHB.getDeltaFuel() == 0){
            //无需执行存储

            //重置油量减少计数器
            decreasCounter = 0;
            decreasFuel = 0.0;

            //可能为加油或抖动
            if(increasCounter > 0){
                //在加油
                if(increasCounter > 2){
                    increasCounter = 0;
                    increasFuel = 0;
                    System.out.println("加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油加油");
                }else{//抖动
                    increasCounter = 0;
                    updateSql(dataMaterialZHB, increasFuel);
                    increasFuel = 0;
                }
            }
        }

      /*  System.out.println("DataSummarizeZHB:" + "increasCounter" + increasCounter + " increasFuel" + increasFuel);
        System.out.println("DataSummarizeZHB  dataMaterialZHB.getDeltaFuel()" + dataMaterialZHB.getDeltaFuel());
        System.out.println(" ");*/

        if(decreasCounter > 15){

            System.out.println("DataSummarizeZHB:" + "偷油！" + "ID" + dataMaterialZHB.getIotDeviceId() + "时间：" + dataMaterialZHB.getDate() + "发现日期：" + new Date());
        }

    }
    
    
    private void updateSql(DataMaterialZHB dataMaterialZHB, double deltaFuel){
        double queryFuel = 0.0;
        double queryDistance = 0.0;
        double queryUsingRate = 0.0;
        Time queryFlag = DaoUtil.strToTime("00:00:00");
        double resultUsingRate;


        
        try {
            List<StaticInformation> siList = new SIDao().findByIdAndParticularDate(dataMaterialZHB.getIotDeviceId()
                    , new java.sql.Date(dataMaterialZHB.getDate().getTime()));

            if (siList.isEmpty()) {
                new SIDao().add(new StaticInformation(dataMaterialZHB.getIotDeviceId(),
                        new java.sql.Date(dataMaterialZHB.getDate().getTime()), 0, 0,
                        DaoUtil.strToTime("00:00:00"), 0));

                queryFuel = 0.0;
                queryDistance = 0.0;
                queryUsingRate = 0.0;
                queryFlag = DaoUtil.strToTime("00:00:00");
            } else {
                queryFuel = siList.get(0).getDailyFuelCost();
                queryDistance = siList.get(0).getDailyDistance();
                queryFlag = siList.get(0).getDailyRunTime();
                queryUsingRate = siList.get(0).getDailyUsingRate();
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if(dataMaterialZHB.getWorkingFlag()){
            resultUsingRate = iotConstant.resultUsingRate;
        }else {
            resultUsingRate = 0;
        }

        //resultUsingRate = getUsingRate(queryFlag);

        try {
            new SIDao().updateDailyUsingRate(dataMaterialZHB.getIotDeviceId(),
                    new java.sql.Date(dataMaterialZHB.getDate().getTime()),resultUsingRate + queryUsingRate);
            new SIDao().updateDailyFuelCost(dataMaterialZHB.getIotDeviceId(),
                    new java.sql.Date(dataMaterialZHB.getDate().getTime()), deltaFuel + queryFuel);
            new SIDao().updateDailyDistance(dataMaterialZHB.getIotDeviceId(),
                    new java.sql.Date(dataMaterialZHB.getDate().getTime()), dataMaterialZHB.getDeltaDistance() + queryDistance);

            //如果workingFlag不为true，则无须更新
            if(dataMaterialZHB.getWorkingFlag()){
                new SIDao().updateDailyRunTime(dataMaterialZHB.getIotDeviceId(),
                        new java.sql.Date(dataMaterialZHB.getDate().getTime())
                        , new java.sql.Time(iotConstant.timeInterval + queryFlag.getTime() + iotConstant.jetLag));
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
