package com.cxgc.udpiotserver;


import com.cxgc.Database.DAO.DDIDao;
        import com.cxgc.Database.DAO.DaoUtil;
        import com.cxgc.Database.DAO.SIDao;
        import com.cxgc.Database.DAO.SIMDao;
        import com.cxgc.Database.model.DeviceDynamicInformation;
        import com.cxgc.Database.model.StaticInformation;
        import com.cxgc.Database.model.StaticInformationMaterial;


        import java.sql.Time;
        import java.text.SimpleDateFormat;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.LinkedList;
        import java.util.List;

/**
 * Created by CHUANGXINGONGCHANG.HeRui on 2018/5/28.
 */ 
public class DataSummarizing {

    private static final double thresholdFuel = 0;//油量阈值
    private static  final long  jetLag = 28800000;
    private LinkedList<DataSummarizingMaterial> dataSummarizingMaterials = new LinkedList<>();
    private  int numCounter = 0;//代表个数，Datasummarizing第一次接收数据后就记为1
    private  double originalFuel = 0;

    //用于存储 本次 油量增量计数器的数值
    private  int increasingTrendAccumulator = 0;
    //用于存储 上一次 油量增量计数器的数值
    private int lastIncreasingTA = 0;

    //用于标志 是否 需要考虑上一个数据包 尾部的 增加量
    private boolean considerLastDeltaFuel = false;

    //油量增量变量
    private double increasingDeltaFuel = 0;
    //油量增量抖动值变量
    private double jitterIncreasingDF = 0;
    //油量增量临时变量
    private double tempIncreasingDeltaFuel = 0;
    //用于存储 上一次 油量增量的数值
    private double lastIncreasingDeltaFuel = 0;


    public void updateDataSummarizingMaterials(DataSummarizingMaterial dataSummarizingMaterial){
        if(dataSummarizingMaterials.size() < CommonUtils.dataSummarizingMax){
            dataSummarizingMaterials.offer(dataSummarizingMaterial);
            System.out.println("DataSummarizing:" + "id:" + dataSummarizingMaterials.get(0).getIotDeviceId() + "dsize" + dataSummarizingMaterials.size());
            numCounter++;
            System.out.println("DataSummarizing:" + "id:" + dataSummarizingMaterials.get(0).getIotDeviceId() + "numcounter" + numCounter);
        }else if(dataSummarizingMaterials.size() == CommonUtils.dataSummarizingMax){
            System.out.println("DataSummarizing:" + "id:" + dataSummarizingMaterials.get(0).getIotDeviceId() + "dsize" + dataSummarizingMaterials.size());
            updateSI();
        }

        try{
            //如果没有数据行 可能更新会报错

            if(dataSummarizingMaterials.size() != 0) {
                new SIMDao().updateNumCounter(dataSummarizingMaterials.get(0).getIotDeviceId(), numCounter);
                new SIMDao().updateTimeStamp(dataSummarizingMaterials.get(0).getIotDeviceId(), new java.sql.Timestamp(dataSummarizingMaterials.getLast().getDate().getTime()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
     * numcounter为CommonUtils.dataSummarizingMax时开始转换，即每小时转换一次
     */
    private void updateSI(){

        double resultFuel;
        double resultDistance;
        double resultUsingRate;
        Time resultFlag;


        //在CommonUtils.dataSummarizingMax条数据中找第一个日期不同的元素的序号，若为0证明不存在，否则存放于checkNextDay中
        int checkNextDay = checkNextDay();

        //index存储了本次处理队列中的元素的个数
        int index;
        if(checkNextDay == 0){
            index = CommonUtils.dataSummarizingMax;
        }else {
            index = checkNextDay;
        }


        //计算当天的油量
        resultFuel = sumFuel(index);
        //计算位移
        resultDistance = sumDistance(index);
        //计算运行时间
        resultFlag = sumTime(index);
        resultUsingRate = getUsingRate(resultFlag);




        double queryFuel;
        double queryDistance;
        double queryUsingRate;
        Time queryFlag;

        try{
            List<StaticInformation> siList = new SIDao().findByIdAndParticularDate(dataSummarizingMaterials.get(0).getIotDeviceId()
                    ,new java.sql.Date(dataSummarizingMaterials.get(0).getDate().getTime()));

            if(siList.isEmpty()){
                new SIDao().add(new StaticInformation(dataSummarizingMaterials.get(0).getIotDeviceId(),
                        new java.sql.Date(dataSummarizingMaterials.get(0).getDate().getTime()) ,0 ,0 ,
                        DaoUtil.strToTime("00:00:00") ,0));

                queryFuel = 0.0;
                queryDistance = 0.0;
                queryUsingRate = 0.0;
                queryFlag = DaoUtil.strToTime("00:00:00");
            }else {
                queryFuel = siList.get(0).getDailyFuelCost();
                queryDistance = siList.get(0).getDailyDistance();
                queryFlag = siList.get(0).getDailyRunTime();
                queryUsingRate = siList.get(0).getDailyUsingRate();
            }

            new SIDao().updateDailyFuelCost(dataSummarizingMaterials.get(0).getIotDeviceId(),
                    new java.sql.Date(dataSummarizingMaterials.get(0).getDate().getTime()), resultFuel + queryFuel);
            System.out.println("DataSummarizing:" + "id:" + dataSummarizingMaterials.get(0).getIotDeviceId() + " resultFuel:" + resultFuel + " queryFuel" + queryFuel + "date:" + new Date(dataSummarizingMaterials.get(0).getDate().getTime()));

            new SIDao().updateDailyDistance(dataSummarizingMaterials.get(0).getIotDeviceId(),
                    new java.sql.Date(dataSummarizingMaterials.get(0).getDate().getTime()), resultDistance + queryDistance);
            new SIDao().updateDailyRunTime(dataSummarizingMaterials.get(0).getIotDeviceId(),
                    new java.sql.Date(dataSummarizingMaterials.get(0).getDate().getTime()), new java.sql.Time(resultFlag.getTime()+queryFlag.getTime() + jetLag));
            new SIDao().updateDailyUsingRate(dataSummarizingMaterials.get(0).getIotDeviceId(),
                    new java.sql.Date(dataSummarizingMaterials.get(0).getDate().getTime()), resultUsingRate + queryUsingRate);
        }catch (Exception e){
            e.printStackTrace();
        }


/*
在此处使用更新 会导致出现：只有dataSummarizingMaterials的内容满CommonUtils.dataSummarizingMax时才更新数据库t_sim表的numcounter/timestamplatest
       try{
            //如果没有数据行 可能更新会报错

            new SIMDao().updateNumCounter(dataSummarizingMaterials.get(0).getIotDeviceId(), numCounter);
            new SIMDao().updateTimeStamp(dataSummarizingMaterials.get(0).getIotDeviceId(),new java.sql.Timestamp(dataSummarizingMaterials.getLast().getDate().getTime()));
        }catch (Exception e){
            e.printStackTrace();
        }*/

        for(int i = 0; i < index; i++) {
            dataSummarizingMaterials.remove();
            //System.out.println("DataSummarizing:" + "id:" + dataSummarizingMaterials.get(0).getIotDeviceId() + "remove " + "index:" + index);
        }
        numCounter = CommonUtils.dataSummarizingMax - index;
    }

    private int checkNextDay(){

        java.sql.Date initDate =new java.sql.Date(dataSummarizingMaterials.get(0).getDate().getTime());
        for(int i = 1; i < dataSummarizingMaterials.size() ; i++){

            if(! initDate.toString().equals(new java.sql.Date(dataSummarizingMaterials.get(i).getDate().getTime()).toString())){

                return i;

            }
        }
        return 0;
    }

    private double sumFuel(int index){

        List<Double> sum = new ArrayList<Double>();


        //*从数据库拿出上次的  正deltafuel计数器值， 正delta增加油量值， 最后一条containingFuel,此处暂时尚未进行从数据库取deltafuel
        try{
            //拿出队列中第一个deviceDynamicInformation，查找最新只会返回一个元素，故dataSummarizingMaterials只包含一个元素
            List<DeviceDynamicInformation>  deviceDynamicInformations = new DDIDao().findLatest(dataSummarizingMaterials.get(0).getIotDeviceId());
            //记为最初油量
            originalFuel = deviceDynamicInformations.get(0).getContainingFuel();

            //拿出
            List<StaticInformationMaterial> staticInformationMaterials = new SIMDao().findById(dataSummarizingMaterials.get(0).getIotDeviceId());
            lastIncreasingTA = staticInformationMaterials.get(0).getIncreasingTrendAccumulator();
        }catch (Exception e){
            e.printStackTrace();
        }

        sum.add( dataSummarizingMaterials.get(0).getFuelAverage() - originalFuel);

        //如果第一条相对于数据库中的（即上一个CommonUtils.dataSummarizingMax条数据包中的）containingFuel仍然为增加
        if(dataSummarizingMaterials.get(0).getFuelAverage() - originalFuel > thresholdFuel){

            //上升趋势累加器值应为 lastIncreasingTA的值加一，不用lastIncreasingTA++，避免lastIncreasingTA被自增
            increasingTrendAccumulator = lastIncreasingTA + 1;
            tempIncreasingDeltaFuel = lastIncreasingDeltaFuel;
        }else{

            increasingTrendAccumulator = 0;
            tempIncreasingDeltaFuel = 0;
        }

        //遍历队列中数据判断是否给车加油，若此队列中数据日期只包含某一天，则index为CommonUtils.dataSummarizingMax；若此队列中数据日期包含多天（即两天），则index为前一天数据个数
        for(int i = 0; i < index - 1; i++){
            double tempDeltaFuel = dataSummarizingMaterials.get(i+1).getFuelAverage() - dataSummarizingMaterials.get(i).getFuelAverage();
            System.out.println("DataSummarizing:" + "tempdeltaFuel " + tempDeltaFuel + "date:" + new Date(dataSummarizingMaterials.get(i).getDate().getTime()));

            //大于阈值(0)代表有可能在加油
            if( tempDeltaFuel > thresholdFuel){

                increasingTrendAccumulator += 1;
                tempIncreasingDeltaFuel += tempDeltaFuel;
            }
            else{//小于阈值代表有可能加油结束，有可能只是抖动

                sum.add(tempDeltaFuel);
                //证明有加油，并且加油在i时停止
                if(increasingTrendAccumulator >= 5){

                    //加油值在increasingDeltaFuel中存储
                    increasingTrendAccumulator = 0;
                    //加油值保留在increasingDeltaFuel中
                    increasingDeltaFuel = tempIncreasingDeltaFuel;
                    //油量增加临时累计量重置
                    tempIncreasingDeltaFuel = 0;
                }
                else{

                    //证明为抖动，应该保留抖动值并在求和过程中考虑
                    increasingTrendAccumulator = 0;
                    //抖动值保留在jitterIncreasingDF中
                    jitterIncreasingDF = tempIncreasingDeltaFuel;
                    tempIncreasingDeltaFuel = 0;
                }
            }
        }

        try{
            //保留本数据包油量增加计数器值到数据库
            new SIMDao().updateIncreasingTrendAccumulatorNumCounter(dataSummarizingMaterials.get(0).getIotDeviceId() ,increasingTrendAccumulator);
        }catch (Exception e){
            e.printStackTrace();
        }
        if(increasingTrendAccumulator > 0){
            //未存入数据库，会导致重启服务器后丢失，但暂时不想更改数据库，故未存入数据库
            lastIncreasingDeltaFuel = tempIncreasingDeltaFuel;
        }

        double result = 0;
        for(int i = 0; i < sum.size(); i++){
            result += sum.get(i);
        }
        //将抖动值考虑进来
        result += jitterIncreasingDF;

        return result;
    }


    private double sumDistance(int index){

        double result = 0;

        for(int i = 0; i < index; i++){
            result += (dataSummarizingMaterials.get(i).getDeltaDistance());
            System.out.println("DataSummarizing:" + "id:" + dataSummarizingMaterials.get(0).getIotDeviceId() + "deltadistance" + dataSummarizingMaterials.get(i).getDeltaDistance()+ "date:" + new Date(dataSummarizingMaterials.get(i).getDate().getTime()));
        }

        return result;
    }


    private java.sql.Time sumTime(int index){
        int result = 0;
        for(int i = 0; i < index ; i++){
            result += (dataSummarizingMaterials.get(i).getWorkingFlag());
        }
        return new java.sql.Time(DaoUtil.strToTime("00:00:00").getTime() + (long)result * 1000 * 10);
    }

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


    public void add(DataSummarizingMaterial dataSummarizingMaterial) {

        dataSummarizingMaterials.offer(dataSummarizingMaterial);

    }

    public int getNumCounter() {
        return numCounter;
    }

    public void setNumCounter(int numCounter) {
        this.numCounter = numCounter;
    }

    public int getIncreasingTrendAccumulator() {
        return increasingTrendAccumulator;
    }

    public void setIncreasingTrendAccumulator(int increasingTrendAccumulator) {
        this.increasingTrendAccumulator = increasingTrendAccumulator;
    }
}
