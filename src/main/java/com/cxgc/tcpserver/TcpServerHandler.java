package com.cxgc.tcpserver;

import com.cxgc.DOM4J.CommonUserCommand;
import com.cxgc.DOM4J.CommonUserDom4jUtil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.cxgc.Database.DAO.DaoUtil;
import com.cxgc.Database.model.DeviceDynamicInformation;
import com.cxgc.Database.model.User;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class TcpServerHandler extends ChannelInboundHandlerAdapter {
//    @Override
//    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException{
//        if(msg instanceof ByteBuf){
////            System.out.println("designhouse:");
//            ByteBuf bbuf = (ByteBuf)msg;
//
//            //   byte[] b = new byte[74];
//
//            //   Charset utf8 = Charset.forName("UTF-8");
//            // bbuf.getBytes(0, b);
//            System.out.println(bbuf.toString(CharsetUtil.UTF_8));
//
//            //得到CommonUserCommand对象
//            CommonUserCommand command = new CommonUserDom4jUtil(bbuf.toString(CharsetUtil.UTF_8)).commandDecode();
////            System.out.println(command.getUser_id() + "|" +  command.getPasswd()+ "|" + command.getContent() + "|" + command.getIot_Device_id() + "|"
////                    + command.getStartStamp() + "|" + command.getStopStamp() + "|" + command.getSomeDay() );//根据command的属性值判断逻辑
//
//            //以下实现通过命令执行数据库相关操作
//
//
//                } try{
//                    Class<?> UserDao = Class.forName("com.cxgc.Database.DAO.UserDao");
//                    Method findById = UserDao.getMethod("findById", String.class);
//                    User user =  (User)findById.invoke(UserDao.newInstance(),command.getUserid());
//
//
//
//                    if(user == null)
//                    {
//                        System.out.println("This id does not exist!");
//                    }
//                    else
//                    {
//                        if(!command.getPasswd().equals( user.getUserPassword()))
//                        {
//                            System.out.println("The password is not correct!");
//                        }
//                        else
//                        {
//
//                            Class<?> clazz = Class.forName("com.cxgc.Database.DAO.DDIDao");
//
//
//                            //其实可以不用，因为不存在同名不同参数的方法
//                            Class[] paramTypes = null;
//                            Method[] methods = clazz.newInstance().getClass().getMethods();//全部方法
//                            for (int i = 0; i< methods.length; i++) {
//                                if(command.getContent().equals(methods[i].getName())){//和传入方法名匹配
//                                    Class[] params = methods[i].getParameterTypes();
//                                    paramTypes = new Class[ params.length] ;
//                                    for (int j = 0; j < params.length; j++) {
//                                        paramTypes[j] = Class.forName(params[j].getName());
//                                    }
//                                    break;
//                                }
//                            }
//
//                            List<Object> args = new ArrayList<Object>();
//                            if(!command.getIot_Device_id().equals( "empty"))
//                            {
//                                args.add(command.getIot_Device_id());
//                            }
//                            if(!command.getSomeDay().equals("empty"))
//                            {
//                                args.add(DaoUtil.strToDate(command.getSomeDay()));
//                            }
//                            if(!command.getStartStamp().equals("empty"))
//                            {
//                                if(DaoUtil.isValidDate(command.getStartStamp()))
//                                {
//                                    args.add(DaoUtil.strToDate(command.getStartStamp()));
//                                }
//                                if(DaoUtil.isValidTime(command.getStartStamp()))
//                                {
//                                    args.add(DaoUtil.strToTime(command.getStartStamp()));
//                                }
//
//                            }
//                            if(!command.getStopStamp().equals("empty"))
//                            {
//                                if(DaoUtil.isValidDate(command.getStopStamp()))
//                                {
//                                    args.add(DaoUtil.strToDate(command.getStopStamp()));
//                                }
//                                if(DaoUtil.isValidTime(command.getStopStamp()))
//                                {
//                                    args.add(DaoUtil.strToTime(command.getStopStamp()));
//                                }
//                            }
//
//
//
//                            Method method = clazz.getMethod(command.getContent(),paramTypes);
//
//
//
//                            List<DeviceDynamicInformation> list = (List<DeviceDynamicInformation>) method.invoke(clazz.newInstance(),args.toArray());
//                            for (DeviceDynamicInformation dsi:list)
//                            {
//                                System.out.println(dsi.getInfomation_Date()+"@"+dsi.getInfomation_Time());
//                            }
//
//                        }
//
//                    }
//            catch (Exception e){
//                System.out.println(e);
//            }
//
//
//
//            //以下为发送代码回执段（仅测试用）(示例）
//            // byte[] responseByteArray = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><bookstore><book></book></bookstore>".getBytes("UTF-8");
//            byte[] responseByteArray = "designhouse".getBytes("UTF-8");
//            ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
//            out.writeBytes(responseByteArray);
//            ctx.writeAndFlush(out);
//
//        }
//    }
//
//    @Override
//    public void channelActive(ChannelHandlerContext ctx){
//        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
//       // System.out.println("channelActive");
//    }
//
//    @Override
//    public void channelInactive(ChannelHandlerContext ctx){
//        System.out.println("channelInactive");
//    }
//
//    @Override
//    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
//        //cause.printStackTrace();//用于提示错误信息
//        System.out.println("A client has disconnected");
//        ctx.close();
//    }
}
