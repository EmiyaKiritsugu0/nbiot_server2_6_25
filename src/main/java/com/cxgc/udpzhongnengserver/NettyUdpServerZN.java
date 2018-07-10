package com.cxgc.udpzhongnengserver;

import com.cxgc.Database.DAO.DDIDao;
import com.cxgc.Database.DAO.DaoUtil;
import com.cxgc.Database.model.DeviceDynamicInformation;
import com.cxgc.udphelmetserver.HelmetConstant;
import com.cxgc.udpiotserver.ByteCompile;
import com.cxgc.udpiotserver.CommonUtils;
import com.cxgc.udpiotserver.DataMaterialZHB;
import com.cxgc.udpiotserver.iotConstant;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

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

public class NettyUdpServerZN {
    public static void main(String args[]){

        System.out.println("中能测试服务器开始");

        //以下为重定向输出到日志
        try {
            PrintStream mytxt = new PrintStream("D:/znlog.txt");
            PrintStream out = System.out;

            System.setOut(mytxt);//将输出重定向为./log.txt

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }

        System.out.println("中能测试服务器开始");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioDatagramChannel.class).handler(
                new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception{
                        System.out.println("接收到数据" + new Date());
                        byte[] command = new byte[1024];
                        packet.content().getBytes(0, command);

                        System.out.println("send response!");

                        //System.out.println(" 收到" + command.toString());

                        String aresponse = new String(new byte[]{command[HelmetConstant.ZNMsgIdPosition]});
                        System.out.println("send response!!!" + aresponse);
                        ctx.writeAndFlush(new DatagramPacket(Unpooled.
                                copiedBuffer(aresponse, CharsetUtil.UTF_8), packet.sender()));
                        getZNDDInfo(command);


                    }
                }
        );

        ChannelFuture future = bootstrap.bind(new InetSocketAddress(8805));
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

    private static void getZNDDInfo(byte[] command){

        double latestFuel = 0.0;

        Date basicDate = DaoUtil.dateCombination(DaoUtil.strToDate(ByteCompile.byte2Date(command[iotConstant.yearInitialPosition],
                command[iotConstant.monthInitialPosition], command[iotConstant.dayInitialPosition])),
                DaoUtil.strToTime(ByteCompile.byte2Time(command[iotConstant.hourInitialPosition], command[iotConstant.minuteInitialPosition],
                        command[iotConstant.secondInitialPosition])));

        String deviceId = ByteCompile.byte2Id(command[iotConstant.iotDeviceIdInitialPosition],command[iotConstant.iotDeviceIdInitialPosition+1],
                command[iotConstant.iotDeviceIdInitialPosition+2],command[iotConstant.iotDeviceIdInitialPosition+3],
                command[iotConstant.iotDeviceIdInitialPosition+4],command[iotConstant.iotDeviceIdInitialPosition+5]
        );



        for(int i = 0; i < CommonUtils.packageContainingElement; i++){
            double deltaFuel = 0.0000;

            double containingFuel = ((double)(command[iotConstant.containingFuelInitialPosition + i]& 0xff)) / 255;
            containingFuel = Double.valueOf(CommonUtils.df.format(containingFuel));


            deltaFuel =  containingFuel - latestFuel;
            double containingElectricity = ((double)(command[iotConstant.containingElectricityInitialPosition + i]& 0xff)) / 255;

            double deltaDistance = (double)(((command[iotConstant.deltaDistanceInitialPosition+ i * 2] & 0xff) << 8)|
                    ((command[iotConstant.deltaDistanceInitialPosition + 1 + i * 2] & 0xff) << 0)) / 100;//疑惑：除以一百是为了？？

            boolean rotationOrientation = ByteCompile.byte2Boolean(command[iotConstant.rotationOrientationInitialPosition + i]);

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

            showData(command[iotConstant.angleXInitialPosition+i],command[iotConstant.angleYInitialPosition+i],command[iotConstant.angleZInitialPosition+i],
                    command[iotConstant.angularSpeedXInitialPosition+i],command[iotConstant.angularSpeedYInitialPosition+i],command[iotConstant.angularSpeedZInitialPosition+i],
                    command[iotConstant.currentSpeedInitialPosition+i],command[iotConstant.xAccelerationInitialPosition+i],command[iotConstant.yAccelerationInitialPosition+i],
                    command[iotConstant.zAccelerationInitialPosition+i],containingFuel,containingElectricity,
                    sd, st, rotationOrientation,deviceId,GPSInformation,deltaDistance,deltaFuel
            );


            latestFuel = containingFuel;
        }

        //DaoUtil.strToDate(new java.sql.Date(basicDate.getTime()).toString());
        //DaoUtil.strToTime(new java.sql.Time(basicDate.getTime()).toString());

    }

    private static void showData(byte angleX, byte angleY, byte angleZ, byte angularXSpeed, byte angularYSpeed,
                                 byte angularZSpeed, byte currentSpeed, byte xAcceleration, byte yAcceleration,
                                 byte zAcceleration, double containingFuel, double containingElectricity, java.sql.Date informationDate,
                                 Time informationTime, boolean rotationOrientation,
                                 String iotDeviceId, String GPSInformation, double deltaDistance, double deltaFuel){

        System.out.println("angle:" + " x:" + angleX + " y:" + angleY + " z:" + angleZ);
        System.out.println("angularSpeed" + " x:" + angularXSpeed + " y:" + angularYSpeed + " z:" + angularZSpeed);
        System.out.println("acceleration" + " x:" + xAcceleration + " y:" + yAcceleration + " z:" + zAcceleration);
        System.out.println("currentSpeed: " + currentSpeed);
        System.out.println("containingElectricity: " + containingElectricity);
        System.out.println("containingFuel: " + containingFuel);
        System.out.println("informationDate: " + informationDate);
        System.out.println("informationTime: " + informationTime);
        System.out.println("rotationOrientation: " + rotationOrientation);
        System.out.println("iotDeviceId: " + iotDeviceId);
        System.out.println("GPSInformation: " + GPSInformation);
        System.out.println("deltaDistance: " + deltaDistance);
        System.out.println("deltaFuel: " + deltaFuel);
        System.out.println(" ");
    }
}
