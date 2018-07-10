package com.cxgc.udpiotserver;

import com.cxgc.Database.DAO.DDIDao;
import com.cxgc.Database.DAO.DaoUtil;
import com.cxgc.Database.DAO.SIDao;
import com.cxgc.Database.DAO.SIMDao;
import com.cxgc.Database.model.DeviceDynamicInformation;
import com.cxgc.Database.model.StaticInformationMaterial;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

public class NettyUdpServerZHB {

    //private static Map<String,DataSummarizing> dataSummarizing4MapMap = new HashMap();
    private static Map<String, DataSummarizeZHB> dataSummarizeZHBMap = new HashMap<>();
    //private static int t = 0;


    public static void main(String args[]){

        //以下为重定向输出到日志
        try {
            PrintStream mytxt = new PrintStream("D:/log.txt");
            PrintStream out = System.out;

            System.setOut(mytxt);//将输出重定向为./log.txt

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioDatagramChannel.class).handler(
                new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception{
                        byte[] command = new byte[1024];
                        packet.content().getBytes(0, command);

                        ctx.writeAndFlush(new DatagramPacket(Unpooled.
                                copiedBuffer(new String(new byte[]{command[iotConstant.messageIdInitialPosition]}), CharsetUtil.UTF_8), packet.sender()));
                        System.out.println("收到数据");
                        getDDInfo(command);


                    }
                }
        );

        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8802));
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    System.out.println("Channel bound");
                }else {
                    System.out.println("Bind attempt failed");
                    future.cause().printStackTrace();
                }
            }
        });
    }

    private static void getDDInfo(byte[] command){
        double latestFuel = 0.0;


        Date basicDate = DaoUtil.dateCombination(DaoUtil.strToDate(ByteCompile.byte2Date(command[iotConstant.yearInitialPosition],
                command[iotConstant.monthInitialPosition], command[iotConstant.dayInitialPosition])),
                DaoUtil.strToTime(ByteCompile.byte2Time(command[iotConstant.hourInitialPosition], command[iotConstant.minuteInitialPosition],
                        command[iotConstant.secondInitialPosition])));

        String deviceId = ByteCompile.byte2Id(command[iotConstant.iotDeviceIdInitialPosition],command[iotConstant.iotDeviceIdInitialPosition+1],
                command[iotConstant.iotDeviceIdInitialPosition+2],command[iotConstant.iotDeviceIdInitialPosition+3],
                command[iotConstant.iotDeviceIdInitialPosition+4],command[iotConstant.iotDeviceIdInitialPosition+5]
        );

        try {
            List<DeviceDynamicInformation> ddiList = new DDIDao().findLatest(deviceId);

            if(!ddiList.isEmpty())
            {
                latestFuel = ddiList.get(0).getContainingFuel();
                //System.out.println("id:" + deviceId + "latestFuel" + latestFuel);
            }
            else{
                System.out.println("no latest fuel in sql!");
                //    deltaDistanceSum = 0;

                //若数据库无ddi数据，将数据包第一条数据作为latestFuel
                latestFuel = ((double)(command[iotConstant.containingFuelInitialPosition]& 0xff)) / 255;
                latestFuel = Double.valueOf(CommonUtils.df.format(latestFuel));
            }
        }catch (Exception e)
        {
            System.out.println(e);
        }


        for(int i = 0; i < CommonUtils.packageContainingElement; i++){
            double deltaFuel = 0.0000;

            double containingFuel = ((double)(command[iotConstant.containingFuelInitialPosition + i]& 0xff)) / 255;
            containingFuel = Double.valueOf(CommonUtils.df.format(containingFuel));


            deltaFuel =  containingFuel - latestFuel;
            double containingElectricity = ((double)(command[iotConstant.containingElectricityInitialPosition + i]& 0xff)) / 255;

            double deltaDistance = (double)(((command[iotConstant.deltaDistanceInitialPosition+ i * 2] & 0xff) << 8)|
                    ((command[iotConstant.deltaDistanceInitialPosition + 1 + i * 2] & 0xff) << 0)) / 100;//疑惑：除以一百是为了？？

            boolean rotationOrientation = ByteCompile.byte2Boolean(command[iotConstant.rotationOrientationInitialPosition + i]);

            boolean workingFlag;
            if((double)command[iotConstant.currentSpeedInitialPosition+i] >= iotConstant.speedLimit){
                workingFlag  = true;
            }else {
                workingFlag = false;
            }

            String GPSInformation =  ByteCompile.byte2GPS(
                    command[iotConstant.GPSInformationInitialPosition + 0 + i * 8],command[iotConstant.GPSInformationInitialPosition + 1 + i * 8],
                    command[iotConstant.GPSInformationInitialPosition + 2 + i * 8],command[iotConstant.GPSInformationInitialPosition + 3 + i * 8],
                    command[iotConstant.GPSInformationInitialPosition + 4 + i * 8],command[iotConstant.GPSInformationInitialPosition + 5 + i * 8],
                    command[iotConstant.GPSInformationInitialPosition + 6 + i * 8],command[iotConstant.GPSInformationInitialPosition + 7 + i * 8]
            );

            //DaoUtil.strToDate(new java.sql.Date(basicDate.getTime()).toString());
            //DaoUtil.strToTime(new java.sql.Time(basicDate.getTime()).toString());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(basicDate);
            calendar.add(Calendar.SECOND, i * 10);
            Date ddiDate = calendar.getTime();
            java.sql.Time st = new java.sql.Time(ddiDate.getTime());
            java.sql.Date sd = new java.sql.Date(ddiDate.getTime());

            addData(command[iotConstant.angleXInitialPosition+i],command[iotConstant.angleYInitialPosition+i],command[iotConstant.angleZInitialPosition+i],
                    command[iotConstant.angularSpeedXInitialPosition+i],command[iotConstant.angularSpeedYInitialPosition+i],command[iotConstant.angularSpeedZInitialPosition+i],
                    command[iotConstant.currentSpeedInitialPosition+i],command[iotConstant.xAccelerationInitialPosition+i],command[iotConstant.yAccelerationInitialPosition+i],
                    command[iotConstant.zAccelerationInitialPosition+i],containingFuel,containingElectricity,
                    sd, st, rotationOrientation,workingFlag,deviceId,GPSInformation,deltaDistance,deltaFuel
            );

            mapCheck(deviceId);
            //System.out.println("t: " + t + "NettyUdpServer " + "containingFuel" + containingFuel + "latestFuel" + latestFuel);
            dataSummarizeZHBMap.get(deviceId).updateDataSummarizingMaterials(new DataMaterialZHB(ddiDate, deviceId, workingFlag, deltaDistance, deltaFuel));

            //t++;
            latestFuel = containingFuel;
        }

        //DaoUtil.strToDate(new java.sql.Date(basicDate.getTime()).toString());
        //DaoUtil.strToTime(new java.sql.Time(basicDate.getTime()).toString());

    }



    private static void addData(byte angleX,byte angleY, byte angleZ,byte angularXSpeed,byte angularYSpeed,
                         byte angularZSpeed,byte currentSpeed, byte xAcceleration, byte yAcceleration,
                         byte zAcceleration,double containingFuel, double containingElectricity, java.sql.Date informationDate,
                         Time informationTime, boolean rotationOrientation, boolean workingFlag,
                         String iotDeviceId, String GPSInformation,double deltaDistance,double deltaFuel){

        containingFuel = Double.valueOf(CommonUtils.df.format(containingFuel));
        containingElectricity = Double.valueOf(CommonUtils.df.format(containingElectricity));
        deltaDistance = Double.valueOf(CommonUtils.df.format(deltaDistance));
        deltaFuel = Double.valueOf(CommonUtils.df.format(deltaFuel));

        DeviceDynamicInformation deviceDynamicInfo = new DeviceDynamicInformation(
                (double) angleX,(double)(angleY),(double)(angleZ),
                (double)(angularXSpeed),(double)(angularYSpeed),(double)(angularZSpeed),
                (double)(currentSpeed),(double)(xAcceleration),
                (double)(yAcceleration),(double)(zAcceleration),
                containingFuel,containingElectricity,informationDate,informationTime,
                rotationOrientation,workingFlag,iotDeviceId,GPSInformation,deltaDistance,deltaFuel
        );
        //checkResult(deviceDynamicInfo);

        DDIDao ddiDao = new DDIDao();

        try{
            ddiDao.add(deviceDynamicInfo);
        }
        catch(Exception e)
        {System.out.println(e);}
    }


    private static void mapCheck(String deviceId){

        if(!dataSummarizeZHBMap.containsKey(deviceId))
        {
            dataSummarizeZHBMap.put(deviceId, new DataSummarizeZHB());
        }
    }


}
