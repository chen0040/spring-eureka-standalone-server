package com.github.chen0040.eureka.server.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by xschen on 10/5/16.
 */
public class StringUtils {

   private static final Logger logger = LoggerFactory.getLogger(StringUtils.class);

   public static boolean isEmpty(String str) {
      return str == null || str.equals("");
   }


   public static String truncate(String s, int maxLength) {
      if(s.length() > maxLength){
         return s.substring(0, maxLength);
      }
      return s;
   }


   public static String formatDate(Date date) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
      if(date == null) {
         date = new Date(0L);
      }
      return dateFormat.format(date);
   }

   public static String formatDateTime(Date date) {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
      if(date == null) {
         date = new Date(0L);
      }
      return dateFormat.format(date);
   }


   public static String formatLong(long value) {
      return "" + value;
   }


   public static String formatBoolean(boolean value) {

      return value ? "true" : "false";
   }


   public static String formatInteger(int value) {
      return "" + value;
   }


   public static Date parseDate(String value) {

      List<SimpleDateFormat> knownPatterns = new ArrayList<SimpleDateFormat>();
      knownPatterns.add(new SimpleDateFormat("dd/MM/yyyy"));
      knownPatterns.add(new SimpleDateFormat("dd-MM-yyyy"));

      Date date = null;
      for (SimpleDateFormat dateFormat : knownPatterns) {
         try {
            date = dateFormat.parse(value);
         } catch (ParseException pe) {
            date = null;
         }
      }

      if(date == null) {
         try {
            long time = Long.parseLong(value);
            if(time < 0L) {
               time = 0L;
            }
            date = new Date(time);
            return date;

         }
         catch (NumberFormatException e) {
            date = null;
         }
      }

      if(date == null) {
         if(value.contains("GMT")) {
            value = value.substring(0, value.indexOf("GMT")-1);
            try {
               //Thu Jan 01 1970 08:00:00 GMT+0800
               date = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss").parse(value);
            }
            catch (ParseException ex) {
               date = null;
            }
         }
      }





      if(date == null) {
         logger.error("No known Date format found for: " + value + ". Setting date to 0..");
         date = new Date(0L);
      }


      return date;

   }


   public static long parseLong(String value) {
      long result;
      try{
         result = Long.parseLong(value);
      }catch(NumberFormatException ex) {
         result = -1L;
      }
      return result;
   }

   public static long parseLong(String value, long defaultValue) {
      long result;
      try{
         result = Long.parseLong(value);
      }catch(NumberFormatException ex) {
         result = defaultValue;
      }
      return result;
   }


   public static boolean parseBoolean(String value) {
      if(StringUtils.isEmpty(value)) return false;
      if(value.equalsIgnoreCase("true")) {
         return true;
      } else if(value.equalsIgnoreCase("yes")) {
         return true;
      } else if(value.equalsIgnoreCase("y")) {
         return true;
      } else if(value.equals("1")) {
         return true;
      }
      return false;
   }


   public static int parseInteger(String value) {
      int result;
      try{
         result = Integer.parseInt(value);
      } catch(NumberFormatException ex) {
         result = 0;
      }
      return result;
   }


   public static String toLowerCase(String email) {
      if(email == null) {
         return null;
      }
      return email.trim().toLowerCase();
   }


   public static String encodeUriComponent(String s) {
      try {
         return URLEncoder.encode(s, "UTF-8")
                 .replaceAll("\\+", "%20")
                 .replaceAll("\\%21", "!")
                 .replaceAll("\\%27", "'")
                 .replaceAll("\\%28", "(")
                 .replaceAll("\\%29", ")")
                 .replaceAll("\\%7E", "~");
      } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
      }
      return s;
   }

   public static String toCurrencyString(double val){
      return String.format("%.2f", val);
   }

   public static String toDateString(Date date){
      if(date == null) return "(null)";
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return format.format(date);
   }

   public static String toString(int val){
      return String.format("%d", val);
   }

   public static String toString(float val){
      return String.format("%.2f", val);
   }

   public static String toString(double val){
      return String.format("%.2lf", val);
   }
}
