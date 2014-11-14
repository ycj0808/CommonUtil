package com.icefire.android.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 工具类
 * @author Administrator
 */
public class JsonUtils {

	/**
	 * 获取json字符串
	 * @param list
	 * @return
	 */
	public static String getJsonStr(List<Map<String,Object>> list){
		Gson gson = new Gson();
		return gson.toJson(list);
	}
	
	/**
	 * 返回List
	 * @param jsonStr
	 * @return
	 */
	public static List<Map<String,Object>> getList(String jsonStr){
		Gson gson = new Gson();
		return gson.fromJson(jsonStr,  new TypeToken<List<Map<String,Object>>>(){}.getType());
	}
	
	/*public static List<Map<String,Object>> getBannerList(String jsonStr){
		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		try {
			JSONObject jsonObject=new JSONObject(jsonStr);  
			jsonObject.get("banner");
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return dataList;
	}*/
	
	/**
	 * 返回Map
	 * @author yangchj
	 * @date 2014年5月18日 上午10:54:08
	 * @param jsonStr
	 * @return
	 */
	public static Map<String,Object> getMap(String jsonStr){
		Gson gson = new Gson();
		return gson.fromJson(jsonStr, new TypeToken<Map<String,Object>>(){}.getType());
	}
}
