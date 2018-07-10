package com.cxgc.tcpserver;

import java.io.UnsupportedEncodingException;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * 本想使用LineBasedFrameDecoder和StringDecoder但是碰到各种问题，时间紧暂时不用，以后回来看
 */
public class TcpServerHandler2 extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException{
        String body = (String)msg;
        System.out.println("server receive:" + body);
        //ByteBuf resp = Unpooled.copiedBuffer(body.getBytes());
        ctx.writeAndFlush(body);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        //ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        ctx.writeAndFlush("netty");
        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        System.out.println("channelInactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        //cause.printStackTrace();//用于提示错误信息
        System.out.println("A client has disconnected");
        ctx.close();
    }
}
