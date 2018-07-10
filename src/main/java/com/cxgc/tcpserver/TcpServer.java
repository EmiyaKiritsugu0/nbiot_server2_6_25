package com.cxgc.tcpserver;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Date;

import com.cxgc.datastatistic.TimedTask;
import com.cxgc.udphelmetserver.NettyUdpServerSZY;
import com.cxgc.udpiotserver.NettyUdpServerZHB;


import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;

public class TcpServer {
    public static void main(String[] args) throws Exception{

        NettyUdpServerZHB.main(args);
        //NettyUdpServerSZY.main(args);
        //TimedTask.main(args);

        //以下为重定向输出到日志
        try {
            PrintStream mytxt = new PrintStream("D:/log.txt");
            PrintStream out = System.out;

            System.setOut(mytxt);//将输出重定向为./log.txt

        }catch(FileNotFoundException e){
            e.printStackTrace();
        }


      System.out.println("server start at:" + new Date());


        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new TcpServerInitializer());
                //    .option(ChannelOption.SO_BACKLOG, 128)//这两条属性如果不加竟然接收不到消息。。暂时不知为啥，有空赶紧查一下
                //    .childOption(ChannelOption.SO_KEEPALIVE, true);//这两条属性如果不加竟然接收不到消息。。暂时不知为啥，有空赶紧查一下
                /*    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childAttr(AttributeKey.newInstance("childAttr"), "childAttrValue");*/

            Channel ch = b.bind(8080).sync().channel();
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
