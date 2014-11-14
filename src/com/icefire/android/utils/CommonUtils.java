package com.icefire.android.utils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

/**
 * @ClassName: CommonUtils
 * @Description: TODO(公共工具类)
 * @author yangchj
 * @date 2013-9-15 下午9:16:50
 * 1.对Map的操作
 * 2.对字符串与数值型数据转换
 * 3.获取UUID
 */
public class CommonUtils {

	/**
	 * @Title: mapHasValue
	 * @Description: TODO(判断Map中是否存在Key)
	 * @return boolean    返回类型
	 * @throws
	 */
	public static boolean mapHasValue(Map paramMap, String key){
		boolean flag=false;
		if(paramMap!=null&&paramMap.containsKey("key")&&!paramMap.get(key).toString().equals("")){
			flag=true;
		}
		return flag;
	}
	/**
	 * @Title: mapEquValue
	 * @Description: TODO(判断Map中Key值等于Value)
	 * @return boolean    返回类型
	 * @throws
	 */
	public boolean mapEquValue(Map paramMap, String key, String value){
		boolean flag=false;
		if(mapHasValue(paramMap, key)&&paramMap.get(key).toString().equalsIgnoreCase(value)){
			flag=true;
		}
		return flag;
	}
	/**
	 * @Title: mapUnequValue
	 * @Description: TODO(判断Map中Key值不等于Value)
	 * @return boolean    返回类型
	 * @throws
	 */
	public boolean mapUnequValue(Map paramMap, String key, String value){
		boolean flag=false;
		if(mapHasValue(paramMap, key)&&!mapEquValue(paramMap,key,value)){
			flag=true;
		}
		return flag;
	}
	/**
	 * @Title: getValByKey
	 * @Description: TODO(取Map中的值)
	 * @return String    返回类型
	 * @throws
	 */
	public String getValByKey(Map paramMap, String key) {
		return mapHasValue(paramMap, key)?paramMap.get(key).toString():"";
	}
	
	/**
	 * 字符串转换为整数
	 * 
	 * @param value
	 *            需要转换的字符串
	 * @param defaultValue
	 *            转达换失败时的替代值
	 * @return 转换后的整数
	 */
	public static int strToInt(String value, int defaultValue) {
		try {
			return Integer.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 字符串转换为长整数
	 * 
	 * @param value
	 *            需要转换的字符串
	 * @param defaultValue
	 *            转达换失败时的替代值
	 * @return 转换后的整数
	 */
	public static long strToInt(String value, long defaultValue) {
		try {
			return Long.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换16进制字符串为数字
	 * 
	 * @param value
	 *            需要转换的字符串
	 * @param defaultValue
	 *            转达换失败时的替代值
	 * @return 转换后的整数
	 */
	public static int hexToInt(String value, int defaultValue) {
		try {
			return Integer.parseInt(value, 16);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换16进制字符串为长数字
	 * 
	 * @param value
	 *            需要转换的字符串
	 * @param defaultValue
	 *            转达换失败时的替代值
	 * @return 转换后的整数
	 */
	public static long hexToInt(String value, long defaultValue) {
		try {
			return Long.parseLong(value, 16);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换为浮点数字
	 * 
	 * @param value
	 *            需要转换的字符串
	 * @param defaultValue
	 *            转达换失败时的替代值
	 * @return 转换后的整数
	 */
	public static float strToFloat(String value, float defaultValue) {
		try {
			return Float.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 转换为浮点数字
	 * 
	 * @param value
	 *            需要转换的字符串
	 * @param defaultValue
	 *            转达换失败时的替代值
	 * @return 转换后的整数
	 */
	public static double strToDouble(String value, double defaultValue) {
		try {
			return Double.valueOf(value);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 获得随机的GUID
	 * 
	 * @return 返回GUID
	 */
	public static String getGUID() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 * 产生n位伪随机数(n最大值为128)
	 * 
	 * @param strLength
	 * @return
	 */
	public String getFixLenthString(int n) {
		String res = "NaN";
		if (n < 129) {
			n++;
			Random rm = new Random();
			BigDecimal pross = new BigDecimal((1 + rm.nextDouble())
					* Math.pow(10, n));
			String fixLenthString = String.valueOf(pross);
			res = fixLenthString.substring(2, n + 1);
		}
		return res;
	}
	
	
	
}
