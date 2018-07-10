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

        if(dataMaterialZHB.getWorkingFlag() == true){
            resultUsingRate = 10 / (24 * 60 * 60);
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
            new SIDao().updateDailyRunTime(dataMaterialZHB.getIotDeviceId(),
                    new java.sql.Date(dataMaterialZHB.getDate().getTime()), new java.sql.Time(getRunTime(dataMaterialZHB.getWorkingFlag()).getTime() + queryFlag.getTime() + iotConstant.jetLag));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static java.sql.Time getRunTime(boolean workingFlag){
        if (workingFlag){
            return new java.sql.Time(DaoUtil.strToTime("00:00:00").getTime() + (long)1 * 1000 * 10);
        }else{
            return new java.sql.Time(DaoUtil.strToTime("00:00:00").getTime() + (long)0 * 1000 * 10);
        }

    }

    /*
    白天找何睿讨论这个函数，写的太傻了。

     */
    private double getUsingRate(java.sql.Time time){

        String sDt1 = "01/01/2018" + " "+time;
        String sDt2 = "01/01/2018" + " "+"23:59:59";
        String sDt3 = "01/01/2018" + " "+"00:00:00";
        double rate = 0;
        SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        try {
            Date dt1 = sdf.parse(sDt1);
            Date dt2 = sdf.parse(sDt2);
            Date dt3 = sdf.parse(sDt3);

            rate = (double)(dt1.getTime() - dt3.getTime()) / (double)(dt2.getTime() - dt3.getTime());
        }catch (Exception e){
            e.printStackTrace();
        }

        return  rate;
    }
}
