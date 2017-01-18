package com.tz.bms.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ʱ�乤����
 */
public class DateUtil {
	/**
	 * ��ݸ�Ĳ����������ڵ�������
	 */
	public static Date setDate(int year,int month,int day){
		Calendar cal=Calendar.getInstance();
		cal.set(year, month-1, day);
		return cal.getTime();
	}
	
	/**
	 * ��ݸ�ĸ�ʽ��ʽ��Date
	 */
	public static String formatDate(Date date,String pattern){
		//��date�����ж�
		if(date!=null){
			SimpleDateFormat sdf =new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
		return null;
	}
	
	/**
	 * ��ݸ�ĸ�ʽ��Stringת����Date
	 */
	public static Date parseString(String pattern,String time){
		SimpleDateFormat sdf =new SimpleDateFormat(pattern);
		Date date=null;
		try {
			date=sdf.parse(time);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
}
