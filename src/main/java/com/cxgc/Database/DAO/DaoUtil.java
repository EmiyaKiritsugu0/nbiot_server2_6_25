package com.cxgc.Database.DAO;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
//:

/**
 * @author  HeRui on 2018/3/16.
 * this class contains some method to assist you with calling the database method.
 */
public class DaoUtil {
    /**
     * to transform a string to sql.date
     * @param  strDate: the date string you wang to transform
     * @return : the date in sql.date
     */
    public static java.sql.Date strToDate(String strDate) {
        String str = strDate;

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        java.sql.Date date = new java.sql.Date(d.getTime());
        return date;
    }
    /**
     * to transform a string to sql.time
     * @param  strTime: the time string you wang to transform
     * @return : the time in sql.time
     */
    public static Time strToTime(String strTime) {

        String str = strTime;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        Date d = null;
        try {
            d = format.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Time time = new Time(d.getTime());
        return Time.valueOf(str);
    }

    /**
     * to transform a string to boolean
     * @param  strBool: the boolean string you wang to transform
     * @return : the boolean
     */
    public static boolean strToBoolean(String strBool) {
        String str = strBool;

         if(str.toUpperCase().equals("TRUE")){
             return true;
         }
         else{
             return  false;
         }
    }
    /**
     * to transform a string to sql.time
     * @param  strDate: the date string you wang to transform
     * @return : the boolean for judging whether the string is in the type of sql.date
     */
    public static boolean isValidDate(String strDate){

        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            format.setLenient(false);   // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.parse(strDate);
        } catch (ParseException e) {
            convertSuccess=false;
        }
            return convertSuccess;
        }
    /**
     * to transform a string to sql.time
     * @param  strTime: the time string you wang to transform
     * @return : the boolean for judging whether the string is in the type of sql.time
     */
    public static boolean isValidTime(String strTime){

        boolean convertSuccess=true;
        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");
        try {
            format.setLenient(false);   // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2007/02/29会被接受，并转换成2007/03/01
            format.parse(strTime);
        } catch (ParseException e) {
            convertSuccess=false;
        }
        return convertSuccess;
    }
    /**
     * to generate a complete time format
     * @param  date: the date in java.util.Date format
     * @param time: the time in java,sql.Time format
     * @return : the Date in java.util.Date containing information of the parameters
     */
    public  static Date dateCombination(Date date, Time time)
    {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = null;
        try {
            d = format.parse(date.toString()+" "+time.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    public static String strToHex(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = (int) s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

}///:~



