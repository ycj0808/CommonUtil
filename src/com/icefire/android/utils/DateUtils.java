package com.icefire.android.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

	public static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
	public static String DEFAULT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
	
	/**
	 * @Title: stringDate
	 * @Description: TODO(把日期转换为yyyy-MM-dd格式字符串)
	 * @return String    返回类型
	 * @throws
	 */
	public static String stringDate(Date date){
		if(date==null){
			return "";
		}else{
			return dateFormat.format(date);
		}
	}
	/**
	 * @Title: stringDate
	 * @Description: TODO(把日期转换为指定格式字符串)
	 * @return String    返回类型
	 * @throws
	 */
	public static String stringDate(Date date,String format){
		if(date==null){
			return "";
		}else{
			SimpleDateFormat df=new SimpleDateFormat(format);
			return df.format(date);
		}
	}
	/**
	 * @Title: dateString
	 * @Description: TODO(将固定格式字符串转化为yyyy-MM-dd)
	 * @return Date    返回类型
	 * @throws
	 */
	public static Date dateString(String strDate){
		try {
			return dateFormat.parse(strDate);
		} catch (ParseException e) {
			return null;
		}
	}
	/**
	 * 获取系统时间
	 * @param format
	 * @return
	 */
	public static String getNowTime(String format) {
		String result = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date curDate = new Date(System.currentTimeMillis()); // 获取当前时间
		result = sdf.format(curDate);
		return result;
	}
	/**
	 * 将long类型的时间转化为日期类型的字符串
	 * @param time
	 * @return
	 */
	public static String getDateFromLongTime(long time){
		String result="";
		SimpleDateFormat sdf= new SimpleDateFormat(DEFAULT_DATETIME_FORMAT);
		Date date=new Date(time);
		result=sdf.format(date);
		return result;
	}
	/**
	 * 将long类型的时间转化为指定日期字符串类型
	 * @author yangchj
	 * @date   2014-7-22 上午11:02:05
	 */
	public static String getDateFromLongTime(long time,String format){
		String result="";
		SimpleDateFormat sdf= new SimpleDateFormat(format);
		Date date=new Date(time);
		result=sdf.format(date);
		return result;
	}
}
