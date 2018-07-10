package com.cxgc.FirstQuery;


import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.InputSource;

import java.io.StringReader;

public class FirstDom4jUtil {

    private String decodingXML;
    public FirstDom4jUtil(String xml){
        decodingXML = xml;
    }

    /**
     * decode the input String(xml) into FluxData
     * @return the decoded command
     */
    public QueryCommand commandDecode(){
        QueryCommand cmd = getCommand(decodingXML);
        return cmd;
    }

    /**
     * 把xml转换为FluxData对象
     * @param xml
     * @return
     */
    public QueryCommand getCommand(String xml){
        QueryCommand comd = null;
        InputSource in = new InputSource(new StringReader(xml));
        in.setEncoding("UTF-8");
        SAXReader reader = new SAXReader();
        Document document;
        try{
            document = reader.read(in);
            Element root = document.getRootElement();
            comd = (QueryCommand) com.cxgc.DOM4J.XmlUtil.fromXmlToBean(root, QueryCommand.class);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("数据解析错误");

        }
        return comd;
    }
}
