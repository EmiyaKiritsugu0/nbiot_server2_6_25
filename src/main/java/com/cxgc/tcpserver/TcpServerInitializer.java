package com.cxgc.tcpserver;

import com.cxgc.FirstQuery.FirstServerHandler;

import java.nio.charset.Charset;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.codec.xml.XmlFrameDecoder;
import io.netty.util.CharsetUtil;

public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {

    public TcpServerInitializer(){
    }

    @Override
    public void initChannel(SocketChannel ch){
        ChannelPipeline p = ch.pipeline();
        //第二期安卓测试代码
        p.addLast("XmlHandler", new XmlFrameDecoder(10000));//////
        p.addLast("Tcp", new ContentHandler());

        //第二期C#代码
        //p.addLast("XmlHandler", new XmlFrameDecoder(1000));//这个Decoder非常重要，能将黏在一起的xml正确解析
        //p.addLast("LineBasedFrameDecoder", new LineBasedFrameDecoder(1024*1024));//对于现有的用了之后不能正常读。目前仅使用TcpServerHandler即可与C#端通信
        //p.addLast("TcpServerHandler", new TcpServerHandler());

        //第一期服务器代码
        //p.addLast("TcpnewContentHandler", new FirstServerHandler());//////

/**
 * 本想使用LineBasedFrameDecoder和StringDecoder但是碰到各种问题，时间紧暂时不用，以后回来看
        p.addLast("frameDecoder", new LineBasedFrameDecoder(80));//介绍见netty权威指南上
        p.addLast("stringDecoder", new StringDecoder(CharsetUtil.UTF_8));
        p.addLast("Tcp", new TcpServerHandler());

        p.addLast("stringEncoder", new StringEncoder(CharsetUtil.UTF_8));
 */
    }
}
