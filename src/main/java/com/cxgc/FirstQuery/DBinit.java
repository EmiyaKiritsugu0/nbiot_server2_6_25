package com.cxgc.FirstQuery;
/**
 * Created by Think on 2017/10/22.
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

class DBinit {

   private static final String URL="jdbc:mysql://127.0.0.1:3306/aaa?&useSSL=false";
   private static final String NAME="root";
   private static final String PASSWORD="wangmo";

   private static Connection conn=null;

   static{
       try {

           Class.forName("com.mysql.jdbc.Driver");

           conn = DriverManager.getConnection(URL, NAME, PASSWORD);
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
   }

   public static Connection getConnection(){
       return conn;
   }


}