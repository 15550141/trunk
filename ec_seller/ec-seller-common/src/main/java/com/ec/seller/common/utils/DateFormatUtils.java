package com.ec.seller.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



public class DateFormatUtils extends org.apache.commons.lang.time.DateFormatUtils {

    public static String format(Date date) {
        if(date==null) {
            return null;
        }
        String s = org.apache.commons.lang.time.DateFormatUtils.format(date, "yyyy-MM-dd HH:mm:ss");
        if(s==null) {
            return org.apache.commons.lang.time.DateFormatUtils.format(date, "yyyy-MM-dd");
        }
        return s;
    }
    
    
    public static final Date parseDate(String strDate, String format) {

        SimpleDateFormat df = null;
        Date date = null;
        df = new SimpleDateFormat(format);

        try {
            date = df.parse(strDate);
        } catch (ParseException e) {}

        return (date);
    }


    public static Date formatDate(Date date,String format) {
        SimpleDateFormat inDf = new SimpleDateFormat(format);
        SimpleDateFormat outDf = new SimpleDateFormat(format);
        String reDate = "";
        try {
            reDate = inDf.format(date);
            return outDf.parse(reDate);
        } catch (Exception e) {
            
        }
        return date;
    }


}

