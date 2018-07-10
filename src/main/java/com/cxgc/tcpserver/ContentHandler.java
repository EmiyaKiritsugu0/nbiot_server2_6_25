package com.cxgc.tcpserver;

import com.cxgc.DOM4J.CommonUserCommand;
import com.cxgc.DOM4J.CommonUserDom4jUtil;
import com.cxgc.Database.model.User;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

public class ContentHandler   extends ChannelInboundHandlerAdapter {
    //List list = new ArrayList<>();
    List<?> list = new ArrayList<>();
   /* ByteBuf bbuf = (ByteBuf)msg;
    System.out.println(bbuf.toString(CharsetUtil.UTF_8));*/
    // 接收到新的数据
  /*  @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {
        try {
            // 接收客户端的数据
            ByteBuf in = (ByteBuf) msg;
            System.out.println("channelRead:" + in.toString(CharsetUtil.UTF_8));

            // 发送到客户端
            byte[] responseByteArray = "<command><type>AngularSpeed</type><startStamp>designhouse</startStamp><stopStamp>RemainedFuel</stopStamp><content>2#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#2.5#15.5#5.26#14.3#3#523#22#3#4.2#2.5#15.5#5.26#14.3#3#523#22#3</content></command>".getBytes("UTF-8");
            ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
            out.writeBytes(responseByteArray);
            ctx.writeAndFlush(out);

        } finally {
            ReferenceCountUtil.release(msg);
        }
    }*/

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("channelActive");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        System.out.println("channelInactive");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws UnsupportedEncodingException {

        double random = (Math.random()*100);
                System.out.println( random + "  command received at"  + new Date() + " " + System.currentTimeMillis());
        String response  = "";
        if (msg instanceof ByteBuf) {
            //          System.out.println("designhouse:");
            ByteBuf bbuf = (ByteBuf) msg;

            //   cap
            //   byte[] b = new byte[74];

            //   Charset utf8 = Charset.forName("UTF-8");
            // bbuf.getBytes(0, b);
            // System.out.println(bbuf.toString(CharsetUtil.UTF_8));

            //得到CommonUserCommand对象
            CommonUserCommand command = new CommonUserDom4jUtil(bbuf.toString(CharsetUtil.UTF_8)).commandDecode();

            //以下实现通过命令执行数据库相关操作
            try {
                Class<?> UserDao = Class.forName("com.cxgc.Database.DAO.UserDao");
                Method findById = UserDao.getMethod("findById", String.class);
                List<User> userList = new ArrayList<>();
                try {
                    userList = (List<User>) findById.invoke(UserDao.newInstance(), command.getUserid());

                } catch (InvocationTargetException e) {
                    System.out.println("此处接收被调用方法内部未被捕获的异常");
                    e.printStackTrace();
                }


                if (userList == null) {
                    System.out.println("This id does not exist!");
                } else if (userList.size() > 1) {
                    System.out.println("User id should not be same!");
                } else {
                    if (!command.getPasswd().equals(userList.get(0).getUserPassword())) {
                        System.out.println("The password is not correct!");
                    } else {

//                        Class<?> clazz = Class.forName("com.cxgc.Database.DAO.DDIDao");
//
//
//                        //其实可以不用，因为不存在同名不同参数的方法
//                        Class[] paramTypes = null;
//                        Method[] methods = clazz.newInstance().getClass().getMethods();//全部方法
//                        if(command.getContent().contains("App")){
//                            for (int i = 0; i< methods.length; i++) {
//                                if (new String("findByIdAndParticularDate").equals(methods[i].getName())) {//和传入方法名匹配
//                                    Class[] params = methods[i].getParameterTypes();
//                                    paramTypes = new Class[params.length];
//                                    for (int j = 0; j < params.length; j++) {
//                                        paramTypes[j] = Class.forName(params[j].getName());
//                                    }
//                                    break;
//                                }
//                            }
//
//                        }else if(command.getContent().contains("findLatest"))
//                        {
//                            for (int i = 0; i< methods.length; i++) {
//                                if (new String("findLatest").equals(methods[i].getName())) {//和传入方法名匹配
//                                    Class[] params = methods[i].getParameterTypes();
//                                    paramTypes = new Class[params.length];
//                                    for (int j = 0; j < params.length; j++) {
//                                        paramTypes[j] = Class.forName(params[j].getName());
//                                    }
//                                    break;
//                                }
//                            }
//                        }
//                        else{
//
//
//                            for (int i = 0; i< methods.length; i++) {
//                                if (command.getContent().equals(methods[i].getName())) {//和传入方法名匹配
//                                    Class[] params = methods[i].getParameterTypes();
//                                    paramTypes = new Class[params.length];
//                                    for (int j = 0; j < params.length; j++) {
//                                        paramTypes[j] = Class.forName(params[j].getName());
//                                    }
//                                    break;
//                                }
//                            }
//
//                        }


//                        List<Object> args = new ArrayList<Object>();
//                        if(!command.getIot_Device_id().equals( "empty"))
//                        {
//                            args.add(command.getIot_Device_id());
//                        }
//                        if(!command.getSomeDay().equals("empty"))
//                        {
//                            args.add(DaoUtil.strToDate(command.getSomeDay()));
//                        }
//                        if(!command.getStartStamp().equals("empty"))
//                        {
//                            if(DaoUtil.isValidDate(command.getStartStamp()))
//                            {
//                                args.add(DaoUtil.strToDate(command.getStartStamp()));
//                            }
//                            if(DaoUtil.isValidTime(command.getStartStamp()))
//                            {
//                                args.add(DaoUtil.strToTime(command.getStartStamp()));
//                            }
//
//                        }
//                        if(!command.getStopStamp().equals("empty"))
//                        {
//                            if(DaoUtil.isValidDate(command.getStopStamp()))
//                            {
//                                args.add(DaoUtil.strToDate(command.getStopStamp()));
//                            }
//                            if(DaoUtil.isValidTime(command.getStopStamp()))
//                            {
//                                args.add(DaoUtil.strToTime(command.getStopStamp()));
//                            }
//                        }

//                        if(command.getContent().contains("App"))
//                        {
//
//                            Method method = clazz.getMethod("findByIdAndParticularDate",paramTypes);
//                            try{
//
//                                list = (List<DeviceDynamicInformation>) method.invoke(clazz.newInstance(),args.toArray());
//                            }
//
//                            catch (InvocationTargetException e) {
//                                System.out.println("此处接收被调用方法内部未被捕获的异常");
//                                e.printStackTrace();
//                            }
//
//                        }else if(command.getContent().contains("findLatest"))
//                            {
//                                Method method = clazz.getMethod("findLatest",paramTypes);
//                                try{
//
//                                    list = (List<DeviceDynamicInformation>) method.invoke(clazz.newInstance(),args.toArray());
//                                }
//
//                                catch (InvocationTargetException e) {
//                                    System.out.println("此处接收被调用方法内部未被捕获的异常");
//                                    e.printStackTrace();
//                                }
//                            }
//                        else {
//
//                                Method method = clazz.getMethod(command.getContent(),paramTypes);
//                                try{
//
//                                    list = (List<DeviceDynamicInformation>) method.invoke(clazz.newInstance(),args.toArray());
//                                }
//
//                                catch (InvocationTargetException e) {
//                                    System.out.println("此处接收被调用方法内部未被捕获的异常");
//                                    e.printStackTrace();
//                                }
//                            }


                        Class<?> daoClass = Class.forName("com.cxgc.Database.DAO." + command.getType() + "Dao");//找到那个数据库
                        System.out.println( random + "  getParamTypes at"  + new Date() + " " + System.currentTimeMillis());
                        Class[] daoParamTypes = getParamTypes("com.cxgc.Database.DAO." + command.getType() + "Dao",command.getQuerymode());//从发来的指令中找方法参数类型

                       // System.out.println("daoParamTypes.length:"+daoParamTypes.length);
                        System.out.println( random + "  getParametersFromCommand at"  + new Date() + " " + System.currentTimeMillis());
                        List<Object> args = getParametersFromCommand(command.getType(),command.getP0(),command.getP1(),
                                    command.getP2(),command.getP3(),command.getP4(),command.getP5());//找参数内容



//                        List<Object> args = getParametersFromCommand(command.getIotDeviceId(),command.getDatestart(),
//                                    command.getDatestop(),command.getTimestart(),command.getTimestop());
//                        if(args.size() == 0)
//                        {
//                            System.out.println("get it");
//                        }
                        System.out.println( random + "  getMethod at"  + new Date() + " " + System.currentTimeMillis());
                        Method method = daoClass.getMethod(command.getQuerymode(), daoParamTypes);//找方法
                        System.out.println(random + "1:"+args.size() + new Date() + " " + System.currentTimeMillis());
                        try {

                         list = (List<?>) method.invoke(daoClass.newInstance(), args.toArray());//执行从数据库取数据方法

                        } catch (InvocationTargetException e) {
                            System.out.println("此处接收被调用方法内部未被捕获的异常");
                            e.printStackTrace();

                        }
                        System.out.println(random + "2:"+args.size() + new Date() + " " + System.currentTimeMillis());
 //                       System.out.println("list:"+list.size());
//                        if(command.getType().equals("DSI") )
//                        {
//                            DeviceStaticInformation deviceStaticInformation =  (DeviceStaticInformation)list.get(0);
//                            System.out.println("hhh"+deviceStaticInformation.getEngineNumber());
//                        }

                        Class<?> dataCollectionClass = Class.forName("com.cxgc.tcpserver." + command.getType() + "DataCollection");//变成要发的东西

                        Method dataCollectionMethod = dataCollectionClass.getMethod("getObjectValue",List.class,String.class);//反射进行拼数据
                        System.out.println(random + "  dataCollection at"  + new Date() + " " + System.currentTimeMillis());
                        String data = (String)dataCollectionMethod.invoke(dataCollectionClass.newInstance(),list,command.getField());//反射进行拼数据



//                        try {
//                            dataCollection = getObjectValue(list, list.get(0).getClass().getName(), command.getField());
//                        }catch (Exception e)
//                        {
//                            System.out.println("error in getObjectValue:"+e);
//                            dataCollection = new DataCollection("#","#","#");
//                        }
                        System.out.println( random + "  response at"  + new Date() + " " + System.currentTimeMillis());
                        response = String.format("<data><field>" + command.getField() + "</field>"+data+"<type>" + command.getType() +
                                "</type><querymode>"+command.getQuerymode()+"</querymode></data>\n");
                       // System.out.println(response);
                    }
                }
            }
            catch (Exception e){
                //System.out.println("error in contenthandler:"+e.printStackTrace(););
                e.printStackTrace();
            }

//            DataCollection dataCollection;





            System.out.println( random + "  send at"  + new Date() + " " + System.currentTimeMillis());

            byte[] responseByteArray = response.getBytes("UTF-8");
            ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
            out.writeBytes(responseByteArray);
            ctx.writeAndFlush(out);

                //以下为发送代码回执段（仅测试用）(示例）
                // byte[] responseByteArray = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><bookstore><book></book></bookstore>".getBytes("UTF-8");
//                if (command.getUser_id().equals("001")) {
//                    String dataSet = new String();
//                    String response = new String();
//                    if (command.getContent().contains("AngularSpeed")) {
//                        dataSet = dataSet + "#";
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + (int) Math.sqrt(Math.pow(deviceDynamicInfo.getAngularXSpeed(), 2) +
//                                    Math.pow(deviceDynamicInfo.getAngularYSpeed(), 2) +
//                                    Math.pow(deviceDynamicInfo.getAngularZSpeed(), 2)
//                            ) + "#";
//                        }
//
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                        System.out.println(response);
//                    }
//
//                    if (command.getContent().contains("Acceleration")) {
//                        dataSet = dataSet + "#";
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + (int) Math.sqrt(Math.pow(deviceDynamicInfo.getxAcceleration(), 2) +
//                                    Math.pow(deviceDynamicInfo.getyAcceleration(), 2) +
//                                    Math.pow(deviceDynamicInfo.getzAcceleration(), 2)
//                            ) + "#";
//                        }
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                    }
//
//                    if (command.getContent().contains("RemainedFuel")) {
//                        dataSet = dataSet + "#";
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + (int) deviceDynamicInfo.getContainingFuel() + "#";
//                        }
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                    }
//
//                    if (command.getContent().contains("RemainedElectricity")) {
//                        dataSet = dataSet + "#";
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + (int) deviceDynamicInfo.getContainingElectricity() + "#";
//                        }
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                    }
//
//                    if (command.getContent().contains("CurrentSpeed")) {
//                        dataSet = dataSet + "#";
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + (int) deviceDynamicInfo.getCurrentSpeed() + "#";
//                        }
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                    }
//
//                    if (command.getContent().contains("Angle")) {
//                        dataSet = dataSet + "#";
//
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + (int) deviceDynamicInfo.getAngleX() + "#"
//                                    + (int) deviceDynamicInfo.getAngleY() + "#"
//                                    + (int) deviceDynamicInfo.getAngleZ() + "#";
//                        }
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                    }
//
//                    if (command.getContent().contains("BeiDou")) {
//                        dataSet = dataSet + "#";
//
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + ByteCompile.byte2Int(Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[0],
//                                    Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[1],
//                                    Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[2],
//                                    Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[3]
//                            ) * 90.0 / Integer.MAX_VALUE + "#"
//                                    + ByteCompile.byte2Int(Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[4],
//                                    Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[5],
//                                    Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[6],
//                                    Base64.getDecoder().decode(deviceDynamicInfo.getGPSInformation())[7]
//                            ) * 180.0 / Integer.MAX_VALUE + "#";
////                        System.out.println("after"+ByteCompile.byte2Int(deviceDynamicInfo.getGPSInformation().getBytes()[0],
////                                deviceDynamicInfo.getGPSInformation().getBytes()[1],
////                                deviceDynamicInfo.getGPSInformation().getBytes()[2],
////                                deviceDynamicInfo.getGPSInformation().getBytes()[3]
////                        )*90.0/Integer.MAX_VALUE);
////                        System.out.println("after"+ByteCompile.byte2Int(deviceDynamicInfo.getGPSInformation().getBytes()[4],
////                                deviceDynamicInfo.getGPSInformation().getBytes()[5],
////                                deviceDynamicInfo.getGPSInformation().getBytes()[6],
////                                deviceDynamicInfo.getGPSInformation().getBytes()[7]
////                        )*180.0/Integer.MAX_VALUE);
//                        }
//
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "#" + "灰罐车" + "</content></command>\n");
//                    }
//
//                    if (command.getContent().contains("WorkingFlag")) {
//                        dataSet = dataSet + "#";
//
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + deviceDynamicInfo.isWorkingFlag() + "#";
//                        }
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                    }
//
//                    if (command.getContent().contains("RotationOrientation")) {
//                        dataSet = dataSet + "#";
//
//                        for (DeviceDynamicInformation deviceDynamicInfo : list
//                                ) {
//                            dataSet = dataSet + deviceDynamicInfo.isRotationOrientation() + "#";
//                        }
//                        response = String.format("<command><type>" + command.getContent() + "</type><startStamp>" + "empty" +
//                                "</startStamp><stopStamp>" + "empty" + "</stopStamp><content>" + dataSet + "</content></command>\n");
//                    }
//
//                    byte[] responseByteArray = response.getBytes("UTF-8");
//                    ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
//                    out.writeBytes(responseByteArray);
//                    ctx.writeAndFlush(out);
//                } else if (command.getUser_id().equals("001")) {
//
//                    String dataSet = new String();
//
//                    dataSet = dataSet + "#";
//
//                    for (DeviceDynamicInformation deviceDynamicInfo : list
//                            ) {
//                        dataSet = dataSet + deviceDynamicInfo.getAngleX() + "#"
//                                + deviceDynamicInfo.getAngleY() + "#"
//                                + deviceDynamicInfo.getAngleZ() + "#"
//                                + deviceDynamicInfo.getAngularXSpeed() + "#"
//                                + deviceDynamicInfo.getAngularYSpeed() + "#"
//                                + deviceDynamicInfo.getAngularZSpeed() + "#"
//                                + deviceDynamicInfo.getCurrentSpeed() + "#"
//                                + deviceDynamicInfo.getxAcceleration() + "#"
//                                + deviceDynamicInfo.getyAcceleration() + "#"
//                                + deviceDynamicInfo.getzAcceleration() + "#"
//                                + deviceDynamicInfo.getContainingFuel() + "#"
//                                + deviceDynamicInfo.getContainingElectricity() + "#"
//                                + deviceDynamicInfo.isRotationOrientation() + "#"
//                                + deviceDynamicInfo.isWorkingFlag() + "#"
//                                + deviceDynamicInfo.getInformationDate() + "#"
//                                + deviceDynamicInfo.getInformationTime() + "#"
//                                + deviceDynamicInfo.getIotDeviceId() + "#"
//                                + deviceDynamicInfo.getGPSInformation() + "#";
//                        byte[] responseByteArray = dataSet.getBytes("UTF-8");
//                        ByteBuf out = ctx.alloc().buffer(responseByteArray.length);
//                        out.writeBytes(responseByteArray);
//                        ctx.writeAndFlush(out);
//                    }
//
//
//                } else {
//
//                }



        }

    }
    private Class[] getParamTypes (String className, String commandType){

        Class[] paramTypes = null;

        try {
            Class<?> clazz = Class.forName(className);

            try {
                Method[] methods = clazz.newInstance().getClass().getMethods();//全部方法
                for (int i = 0; i < methods.length; i++) {

                    if (commandType.equals(methods[i].getName())) {//和传入方法名匹配
                        Class[] params = methods[i].getParameterTypes();
                        paramTypes = new Class[params.length];
                        for (int j = 0; j < params.length; j++) {
                            paramTypes[j] = Class.forName(params[j].getName());

                        }
                        break;
                    }
                }

            } catch (IllegalAccessException | InstantiationException e1) {
                System.out.println("error in getParamTypes:"+e1);
            }

        } catch (ClassNotFoundException e) {
            System.out.println("error in getParamTypes:"+e);
        }
        // System.out.println("paramTypes.length:"+paramTypes.length);
        return paramTypes;
    }

    private List<Object> getParametersFromCommand (String commandType,String firstParam, String secondParam,
                                                   String thirdParam, String forthParam, String fifthParam,
                                                   String sixthParam){

        List<String> parameterGetterElements = new ArrayList<>();
        List<Object> args = new ArrayList<>();

//        parameterGetterElements.add(firstParam);
//        parameterGetterElements.add(secondParam);
//        parameterGetterElements.add(thirdParam);
//        parameterGetterElements.add(forthParam);
//        parameterGetterElements.add(fifthParam);
//        parameterGetterElements.add(sixthParam);
        if (!firstParam.equals("null")) {
            parameterGetterElements.add(firstParam);
        }
        if (!secondParam.equals("null")) {
            parameterGetterElements.add(secondParam);
        }
        if (!thirdParam.equals("null")) {
            parameterGetterElements.add(thirdParam);
        }
        if (!forthParam.equals("null")) {
            parameterGetterElements.add(forthParam);
        }
        if (!fifthParam.equals("null")) {
            parameterGetterElements.add(fifthParam);
        }
        if (!sixthParam.equals("null")) {
            parameterGetterElements.add(sixthParam);
        }
//        for(int i =0 ;i< parameterGetterElements.size();i++){
//                    System.out.println("bb"+parameterGetterElements.get(i));
//                }

        try {
            Class<?> parameterGetterClass = Class.forName("com.cxgc.tcpserver.ParameterGetter");

            //System.out.println("get" + commandType + "ParametersFromCommand");
            Class[] parameterGetterParamTypes = getParamTypes(parameterGetterClass.getName(), "get" + commandType + "ParametersFromCommand");
//                for(int i =0 ;i< parameterGetterParamTypes.length;i++){
//                    System.out.println("aa"+parameterGetterParamTypes[i]);
//                }
//System.out.println(parameterGetterParamTypes ==  null);

            Method parameterGetterParamMethod = parameterGetterClass.getMethod("get" + commandType + "ParametersFromCommand", parameterGetterParamTypes);
 //           System.out.println(parameterGetterParamMethod ==  null);
            try{
                args = (List<Object>) parameterGetterParamMethod.invoke(parameterGetterClass.newInstance(),parameterGetterElements.toArray());
//                for(int i =0 ;i< args.size();i++){
//                    System.out.println("aa"+args.get(i));
//                }
            }
            catch(InstantiationException | IllegalAccessException  | InvocationTargetException e){
                System.out.println("error in method invocation of getParametersFromCommand:"+e);
            }

        }catch(ClassNotFoundException  | NoSuchMethodException e){
            System.out.println("error in getParametersFromCommand:"+e);
        }

        return args;

    }

//    private List<Object> getDDIParametersFromCommand (String iotDeviceId, String startDateStamp,
//                                                   String stopDateStamp, String startTimeStamp, String stopTimeStamp){
//        List<Object> args = new ArrayList<Object>();
//        if (!iotDeviceId.equals("empty")) {
//            args.add(iotDeviceId);
//        }
//        if (!startDateStamp.equals("empty")) {
//            args.add(DaoUtil.strToDate(startDateStamp));
//        }
//        if (!stopDateStamp.equals("empty")) {
//            args.add(DaoUtil.strToDate(stopDateStamp));
//        }
//        if (!startTimeStamp.equals("empty")) {
//            args.add(DaoUtil.strToTime(startTimeStamp));
//        }
//        if (!stopTimeStamp.equals("empty")) {
//            args.add(DaoUtil.strToTime(stopTimeStamp));
//        }
////        for(Object o :list)
////        {
////            System.out.println(o);
////        }
//        return args;
//
//    }
//
//    private List<Object> getDSIParametersFromCommand (String iotDeviceId, String SIMId, String RFId,
//                                                      String projectInformation, String numberPlate, String inControl){
//        List<Object> args = new ArrayList<Object>();
//        if (!iotDeviceId.equals("empty")) {
//            args.add(iotDeviceId);
//        }
//        if (!SIMId.equals("empty")) {
//            args.add(SIMId);
//        }
//        if (!RFId.equals("empty")) {
//            args.add(RFId);
//        }
//        if (!projectInformation.equals("empty")) {
//            args.add(projectInformation);
//        }
//        if (!numberPlate.equals("empty")) {
//            args.add(numberPlate);
//        }
//        if (!inControl.equals("empty")) {
//            args.add(DaoUtil.strToBoolean(inControl));
//        }
////        for(Object o :list)
////        {
////            System.out.println(o);
////        }
//        return args;
//
//    }
//
//    private List<Object> getPDIParametersFromCommand (String projectName, String projectProvince, String projectCity){
//        List<Object> args = new ArrayList<Object>();
//        if (!projectName.equals("empty")) {
//            args.add(projectName);
//        }
//        if (!projectProvince.equals("empty")) {
//            args.add(projectProvince);
//        }
//        if (!projectCity.equals("empty")) {
//            args.add(projectCity);
//        }
//
////        for(Object o :list)
////        {
////            System.out.println(o);
////        }
//        return args;
//
//    }

//    private List<Object> getSIParametersFromCommand (String iotDeviceId, String currentDate){
//        List<Object> args = new ArrayList<Object>();
//        if (!iotDeviceId.equals("empty")) {
//            args.add(iotDeviceId);
//        }
//        if (!currentDate.equals("empty")) {
//            args.add(DaoUtil.strToDate(currentDate));
//        }
//
//
////        for(Object o :list)
////        {
////            System.out.println(o);
////        }
//        return args;
//
//    }

//    public DataCollection getObjectValue(List<?> object,String className, String parameter) throws ClassNotFoundException,
//            IllegalAccessException, InstantiationException {
//
//            Object obj = Class.forName(className).newInstance();
//            Class c = obj.getClass();
//            Field[] fields = c.getDeclaredFields();//取得所有类成员变量
//
//        //遍历所有类成员变量
////        for (Field f:fields
////             ) {
////            System.out.println(f.getName());
////        }
////        //遍历所有方法
////        Method[] methods = c.getMethods();
////        for (Method m:methods
////             ) {
////            System.out.println(m.getName());
////        }
//
//
//            //取消每个属性的安全检查
//            for(Field f:fields){
//                f.setAccessible(true);
//            }
//        String dataSet = new String("#");
//        String dataSetTime = new String("#");
//        String dataSetDate = new String("#");
//
//        if(!object.isEmpty()){
//            for (int i = 0; i < fields.length; i++)
//            {
//                if(!(parameter.equals("informationDate")||parameter.equals("informationTime")))
//                {
//                    if(fields[i].getName().equals(parameter))
//                    {
//                        //System.out.println("get it");
//                        for (int j = 0; j < object.size(); j++) {
//
//                            try {
//                                dataSet = dataSet +fields[i].get(object.get(j)) + "#";
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                System.out.println("e1");
//                            }
//                        }
//                    }
//                    if(fields[i].getName().equals("informationDate"))
//                    {
//                        for (int j = 0; j < object.size(); j++) {
//
//                            try {
//                                dataSetDate = dataSetDate +fields[i].get(object.get(j)) + "#";
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                System.out.println("e2");
//                            }
//                        }
//                    }
//                    if(fields[i].getName().equals("informationTime"))
//                    {
//                        for (int j = 0; j < object.size(); j++) {
//
//                            try {
//                                dataSetTime = dataSetTime +fields[i].get(object.get(j)) + "#";
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                System.out.println("e3");
//                            }
//                        }
//                    }
//                }
//                else if(parameter.equals("informationDate"))
//                {
//                    if(fields[i].getName().equals(parameter)) {
//                        for (int j = 0; j < object.size(); j++) {
//
//                            try {
//                                dataSet = dataSet + fields[i].get(object.get(j)) + "#";
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                System.out.println("e4");
//                            }
//                        }
//                    }
//                }
//                else if(parameter.equals("informationTime"))
//                {
//                    if(fields[i].getName().equals(parameter)) {
//                        for (int j = 0; j < object.size(); j++) {
//
//                            try {
//                                dataSet = dataSet + fields[i].get(object.get(j)) + "#";
//
//                            } catch (Exception e) {
//                                e.printStackTrace();
//                                System.out.println("e5");
//                            }
//                        }
//                    }
//                }
//
//
//
//            }
//        }
//
//            DataCollection dataCollection = new DataCollection(dataSetTime,dataSetDate,dataSet);
//            return dataCollection;
//
//        }
//
//
//}
//class DataCollection
//{
//    private String contentTime;
//    private String contentDate;
//    private String contentData;
//
//    public String getContentTime() {
//        return contentTime;
//    }
//
//    public void setContentTime(String contentTime) {
//        this.contentTime = contentTime;
//    }
//
//    public String getContentDate() {
//        return contentDate;
//    }
//
//    public void setContentDate(String contentDate) {
//        this.contentDate = contentDate;
//    }
//
//    public String getContentData() {
//        return contentData;
//    }
//
//    public void setContentData(String contentData) {
//        this.contentData = contentData;
//    }
//
//    public DataCollection() {
//
//    }
//
//    public DataCollection(String contentTime, String contentDate, String contentData) {
//        this.contentTime = contentTime;
//        this.contentDate = contentDate;
//        this.contentData = contentData;
//    }
}