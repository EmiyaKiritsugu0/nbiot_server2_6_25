package com.cxgc.udphelmetserver;

import com.cxgc.Database.DAO.DaoUtil;
import com.cxgc.Database.model.HelmetDynamicInformation;
import com.cxgc.udpiotserver.ByteCompile;
import com.cxgc.udpiotserver.CommonUtils;

import java.net.InetSocketAddress;
import java.util.Calendar;
import java.util.Date;

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

public class NettyUdpServerSZY {

    public static void main(String args[]){
        System.out.println("开始");
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup()).channel(NioDatagramChannel.class).handler(
                new SimpleChannelInboundHandler<DatagramPacket>() {
                    @Override
                    public void channelRead0(ChannelHandlerContext ctx, DatagramPacket packet) throws Exception{
                        System.out.println("接收到数据");
                        byte[] command = new byte[1024];
                        packet.content().getBytes(0, command);

                        System.out.println("send response!");
                        String aresponse = new String(new byte[]{command[HelmetConstant.helmetMsgIdPosition]});
                        System.out.println("send response!!!" + aresponse);
                        ctx.writeAndFlush(new DatagramPacket(Unpooled.
                                copiedBuffer(aresponse, CharsetUtil.UTF_8), packet.sender()));
                        getHelmetDDInfo(command);


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

    private static void getHelmetDDInfo(byte[] command){
        String helmetId = ByteCompile.byte2Id(command[HelmetConstant.helmetIdInitialPosition], command[HelmetConstant.helmetIdInitialPosition + 1],
                command[HelmetConstant.helmetIdInitialPosition + 2], command[HelmetConstant.helmetIdInitialPosition + 3],
                command[HelmetConstant.helmetIdInitialPosition + 4], command[HelmetConstant.helmetIdInitialPosition + 5]);

        Date helmetBasicDate = DaoUtil.dateCombination(DaoUtil.strToDate(ByteCompile.byte2Date(command[HelmetConstant.yearInitialPosition],
                command[HelmetConstant.monthInitialPosition], command[HelmetConstant.dayInitialPosition])),
                DaoUtil.strToTime(ByteCompile.byte2Time(command[HelmetConstant.hourInitialPosition], command[HelmetConstant.minuteInitialPosition],
                        command[HelmetConstant.secondInitialPosition])));



        //DaoUtil.strToDate(new java.sql.Date(basicDate.getTime()).toString());
        //DaoUtil.strToTime(new java.sql.Time(basicDate.getTime()).toString());

        for(int i = 0; i < CommonUtils.packageContainingElement; i++) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(helmetBasicDate);
        calendar.add(Calendar.SECOND, i * 10);
        Date ddiDate = calendar.getTime();
        java.sql.Time st = new java.sql.Time(ddiDate.getTime());
        java.sql.Date sd = new java.sql.Date(ddiDate.getTime());

            double deltaDistance = (double)(((command[HelmetConstant.deltaDistanceInitialPosition + i * 2] & 0xff) << 8)|
                    ((command[HelmetConstant.deltaDistanceInitialPosition + 1 + i * 2] & 0xff) << 0)) / 100;

            String GPSInformation =  ByteCompile.byte2GPS(
                    command[HelmetConstant.GPSInformationInitialPosition + 0 + i * 8],command[HelmetConstant.GPSInformationInitialPosition + 1 + i * 8],
                    command[HelmetConstant.GPSInformationInitialPosition + 2 + i * 8],command[HelmetConstant.GPSInformationInitialPosition + 3 + i * 8],
                    command[HelmetConstant.GPSInformationInitialPosition + 4 + i * 8],command[HelmetConstant.GPSInformationInitialPosition + 5 + i * 8],
                    command[HelmetConstant.GPSInformationInitialPosition + 6 + i * 8],command[HelmetConstant.GPSInformationInitialPosition + 7 + i * 8]
            );

            addHelmetData(command[HelmetConstant.heartRatePosition + i], command[HelmetConstant.lowPressPosition + i], command[HelmetConstant.highPressPosition + i],
                    command[HelmetConstant.currentSpeedPosition + i], command[HelmetConstant.containingElectricityPosition + i], sd, st, helmetId, GPSInformation, deltaDistance);
        }



    }



    private static void addHelmetData(byte heartRate, byte lowPressure, byte highPressure, byte currentSpeed, double containingElectricity,
                                      java.sql.Date informationDate, java.sql.Time informationTime, String iotDeviceId, String GPSInformation,
                                      double deltaDistanceSum){


        containingElectricity = Double.valueOf(CommonUtils.df.format(containingElectricity));
        deltaDistanceSum = Double.valueOf(CommonUtils.df.format(deltaDistanceSum));

        HelmetDynamicInformation helmetDynamicInfo = new HelmetDynamicInformation(
                (double)heartRate, (double)lowPressure, (double)highPressure, (double)currentSpeed, containingElectricity, informationDate, informationTime,
                iotDeviceId, GPSInformation, deltaDistanceSum);
        HelmetConstant.checkResult(helmetDynamicInfo);


    }


}
