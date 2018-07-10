package com.cxgc.FirstQuery;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class FirstServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException{
        if(msg instanceof ByteBuf){
            ByteBuf bbuf = (ByteBuf)msg;

            //   cap
            //   byte[] b = new byte[74];

            //   Charset utf8 = Charset.forName("UTF-8");
            // bbuf.getBytes(0, b);
             System.out.println("accept:" + bbuf.toString(CharsetUtil.UTF_8) + " at:" + new Date());

       /*
            //得到CommonUserCommand对象
            QueryCommand queryCommand = new FirstDom4jUtil(bbuf.toString(CharsetUtil.UTF_8)).commandDecode();
         *//*   System.out.println("FirstServerHandler" + queryCommand.getContent() + "|" +  queryCommand.getDate()+ "|" + queryCommand.getPasswd() + "|" + queryCommand.getId() + "|"
                    + queryCommand.getTime() + "|" + queryCommand.getUser_id());//根据command的属性值判断逻辑*//*

            //以下实现通过命令执行数据库相关操作
            if(queryCommand.getUser_id().equals("000") && queryCommand.getPasswd().equals("designhouse")){
                if(queryCommand.getContent().equals("QUERYDATE")){
                    try {
                        queryFluxByDate(strToDate(queryCommand.getDate()), ctx);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else if(queryCommand.getContent().equals("QUERYINSTANTDATE")){
                    try {
                        queryInstantFluxByDate(strToDate(queryCommand.getDate()), ctx);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }*/


        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx){
        //ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
        //System.out.println("channelActive");
        System.out.println("A client has connected at:" + new Date());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        //System.out.println("channelInactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
        //cause.printStackTrace();//用于提示错误信息
        System.out.println("A client has disconnected at:" + new Date());
        ctx.close();
    }

    public void queryFluxByDate(java.sql.Date date, ChannelHandlerContext ctx) throws SQLException{
        FluxData fluxdata=null;
        Connection con=DBinit.getConnection();//首先拿到数据库的连接
        String sql="" +
                "select * from device "+
                "where date_sql=?";//参数用?表示，相当于占位符;用mysql的日期函数current_date()来获取当前日期
        //预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        psmt.setDate(1, date);
        //执行SQL语句
        /*psmt.execute();*///execute()方法是执行更改数据库操作（包括新增、修改、删除）;executeQuery()是执行查询操作
        ResultSet rs = psmt.executeQuery();//返回一个结果集
        //遍历结果集
        // int i = 0;//.//
        while(rs.next()){

            fluxdata=new FluxData();
            fluxdata.setId(rs.getInt("id"));
            fluxdata.setFlux(rs.getString("flux"));
            fluxdata.setDate_sql(rs.getDate("date_sql"));
            fluxdata.setTime_sql(rs.getTime("time_sql"));
            String message = "<protocol><file type = \"" + "tota" + "\" id = \"" + fluxdata.getId() + "\" content = \"" +fluxdata.getFlux() + "\" date = \"" + fluxdata.getDate_sql() + "\" time = \"" + fluxdata.getTime_sql() + "\" /></protocol>";
            //printmessege_cs("<protocol><file type = \"" + "tota" + "\" id = \"" + g.getId() + "\" content = \"" +g.getFlux() + "\" date = \"" + g.getDate() + "\" time = \"" + g.getTime() + "\" /></protocol>");
            //System.out.println("message:" + message);

            //System.out.println(" querydevice_date_cs输出为：<protocol><file type = \"" + "tota" + "\" id = \"" + g.getId() + "\" content = \"" +g.getFlux() + "\" date = \"" + g.getDate() + "\" time = \"" + g.getTime() + "\" /></protocol>");
            try {
                byte[] responseByteArray = message.getBytes("UTF-8");
                ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
                out.writeBytes(responseByteArray);
                ctx.writeAndFlush(out);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }


    public void queryInstantFluxByDate(java.sql.Date date, ChannelHandlerContext ctx) throws SQLException{
        FluxData fluxdata=null;
        Connection con=DBinit.getConnection();//首先拿到数据库的连接
        String sql="" +
                "select * from device "+
                "where date_sql=?";//参数用?表示，相当于占位符;用mysql的日期函数current_date()来获取当前日期
        //预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        //先对应SQL语句，给SQL语句传递参数
        psmt.setDate(1, date);
        //执行SQL语句
        /*psmt.execute();*///execute()方法是执行更改数据库操作（包括新增、修改、删除）;executeQuery()是执行查询操作
        ResultSet rs = psmt.executeQuery();//返回一个结果集
        //遍历结果集
        // int i = 0;//.//
        while(rs.next()){

            fluxdata=new FluxData();
            fluxdata.setId(rs.getInt("id"));
            fluxdata.setFlux(rs.getString("flux"));
            fluxdata.setDate_sql(rs.getDate("date_sql"));
            fluxdata.setTime_sql(rs.getTime("time_sql"));
            String message = "<protocol><file type = \"" + "tota" + "\" id = \"" + fluxdata.getId() + "\" content = \"" +fluxdata.getFlux() + "\" date = \"" + fluxdata.getDate_sql() + "\" time = \"" + fluxdata.getTime_sql() + "\" /></protocol>";
            //printmessege_cs("<protocol><file type = \"" + "tota" + "\" id = \"" + g.getId() + "\" content = \"" +g.getFlux() + "\" date = \"" + g.getDate() + "\" time = \"" + g.getTime() + "\" /></protocol>");
            //System.out.println("message:" + message);

            //System.out.println(" querydevice_date_cs输出为：<protocol><file type = \"" + "tota" + "\" id = \"" + g.getId() + "\" content = \"" +g.getFlux() + "\" date = \"" + g.getDate() + "\" time = \"" + g.getTime() + "\" /></protocol>");
            try {
                byte[] responseByteArray = message.getBytes("UTF-8");
                ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
                out.writeBytes(responseByteArray);
                ctx.writeAndFlush(out);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    public java.sql.Date strToDate(String strDate) {
        String str = strDate;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }
}
