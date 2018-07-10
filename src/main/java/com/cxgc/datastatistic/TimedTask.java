package com.cxgc.datastatistic;

import com.cxgc.Database.DAO.DDIDao;
import com.cxgc.Database.DAO.DaoUtil;
import com.cxgc.Database.DAO.SIDao;
import com.cxgc.Database.model.DeviceDynamicInformation;
import com.cxgc.Database.model.StaticInformation;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimedTask {

    private static int increasCounter = 0;
    private static int increasDeltaFuel = 0;
    private static double resultFuel;

    public static void main(String[] args) {
        // run in a second
        final long timeInterval = 1000 * 60 * 60;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true) {

                    Date initialTime = new Date();
                    Calendar calendar1 = Calendar.getInstance();
                    calendar1.setTime(initialTime);
                    calendar1.add(Calendar.HOUR_OF_DAY, 1);
                    Date endTime = calendar1.getTime();

                    SimpleDateFormat formatter = new SimpleDateFormat("HH:00:00");

                    SimpleDateFormat formatter3 = new SimpleDateFormat("yyyy-MM-dd");

                    String mInitialTime = formatter.format(initialTime);
                    String mEndTime = formatter.format(endTime);
                    String mDate = formatter3.format(initialTime);

                    java.sql.Date sqlDate = DaoUtil.strToDate(mDate);
                    java.sql.Time sqlStartTime = DaoUtil.strToTime(mInitialTime);
                    java.sql.Time sqlEndTime = DaoUtil.strToTime(mEndTime);

                    System.out.println(sqlDate + " " + sqlStartTime + " " + sqlEndTime);



                    increasCounter = 0;

                    List<DeviceDynamicInformation> devDynamicInfos = new ArrayList<>();
                    // ------- code for task to run
                    System.out.println("Hello !!");
                    // ------- ends here

                    try {
                        devDynamicInfos = new DDIDao().findByIdAndHour("7", sqlDate, sqlStartTime, sqlEndTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    for(int i = 0; i < 360; i++) {
                        double tempDeltaFuel = devDynamicInfos.get(i).getDeltaFuel();

                        //有可能为加油
                        if(tempDeltaFuel > 0){
                            increasCounter ++;
                            increasDeltaFuel += tempDeltaFuel;
                        }else{
                            //出现加油
                            if(increasCounter > 10){
                                //代表为加油，不累计到result中

                            }else{
                                //代表为抖动，累计到result中
                                resultFuel += increasDeltaFuel;

                            }
                            resultFuel += tempDeltaFuel;
                            increasCounter = 0;
                            increasDeltaFuel = 0;
                        }
                    }

                    double queryFuel;
                    double queryDistance;
                    double queryUsingRate;
                    Time queryRunTime;

                    try{
                        List<StaticInformation> siList = new SIDao().findByIdAndParticularDate(devDynamicInfos.get(0).getIotDeviceId()
                                ,new java.sql.Date(devDynamicInfos.get(0).getInformationDate().getTime()));

                        if(siList.isEmpty()){
                            new SIDao().add(new StaticInformation(devDynamicInfos.get(0).getIotDeviceId(),
                                    new java.sql.Date(devDynamicInfos.get(0).getInformationDate().getTime()) ,0 ,0 ,
                                    DaoUtil.strToTime("00:00:00") ,0));

                            queryFuel = 0.0;
                            queryDistance = 0.0;
                            queryUsingRate = 0.0;
                            queryRunTime = DaoUtil.strToTime("00:00:00");
                        }else {
                            queryFuel = siList.get(0).getDailyFuelCost();
                            queryDistance = siList.get(0).getDailyDistance();
                            queryRunTime = siList.get(0).getDailyRunTime();
                            queryUsingRate = siList.get(0).getDailyUsingRate();
                        }

                        new SIDao().updateDailyFuelCost(devDynamicInfos.get(0).getIotDeviceId(),
                                new java.sql.Date(devDynamicInfos.get(0).getInformationDate().getTime()), resultFuel + queryFuel);
                        System.out.println("DataSummarizing:" + "id:" + devDynamicInfos.get(0).getIotDeviceId() + " resultFuel:" + resultFuel + " queryFuel" + queryFuel + "date:" + new Date(devDynamicInfos.get(0).getInformationDate().getTime()));

                /*        new SIDao().updateDailyDistance(devDynamicInfos.get(0).getIotDeviceId(),
                                new java.sql.Date(devDynamicInfos.get(0).getDate().getTime()), resultDistance + queryDistance);
                        new SIDao().updateDailyRunTime(devDynamicInfos.get(0).getIotDeviceId(),
                                new java.sql.Date(devDynamicInfos.get(0).getDate().getTime()), new java.sql.Time(resultFlag.getTime()+queryFlag.getTime() + jetLag));
                        new SIDao().updateDailyUsingRate(devDynamicInfos.get(0).getIotDeviceId(),
                                new java.sql.Date(devDynamicInfos.get(0).getDate().getTime()), resultUsingRate + queryUsingRate);*/
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    try {
                        Thread.sleep(timeInterval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 5);
        calendar.set(Calendar.SECOND, 0);
        Date scheduledDate = calendar.getTime();


        while(new Date().before(scheduledDate)){
            //延时等待
        }

        Thread thread = new Thread(runnable);
        thread.start();
    }
}