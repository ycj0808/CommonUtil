package com.icefire.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtils {
	/**
	  * @Title: 判断是否有网络连接
	  * @Description: TODO
	  * @param     context
	  * @param     设定文件
	  * @return boolean    返回类型
	  * @throws
	  */
	public static boolean isNetWorkConnected(Context context){
		if(context!=null){
			ConnectivityManager mConnectivityManager=(ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo=mConnectivityManager.getActiveNetworkInfo();
			if(mNetworkInfo!=null){
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	/**
	  * @Title: 判断WIFI网络是否可用
	  * @Description: TODO
	  * @param     context
	  * @param     设定文件
	  * @return boolean    返回类型
	  * @throws
	  */
	public static boolean isWifiConnected(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mWiFiNetworkInfo = mConnectivityManager  
	                .getNetworkInfo(ConnectivityManager.TYPE_WIFI);  
	        if (mWiFiNetworkInfo != null) {  
	            return mWiFiNetworkInfo.isAvailable();  
	        }  
	    }  
	    return false;  
	}
	/**
	  * @Title: 判断MOBILE网络是否可用
	  * @Description: TODO
	  * @param @param context
	  * @param @return    设定文件
	  * @return boolean    返回类型
	  * @throws
	 */
	public static boolean isMobileConnected(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mMobileNetworkInfo = mConnectivityManager  
	                .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);  
	        if (mMobileNetworkInfo != null) {  
	            return mMobileNetworkInfo.isAvailable();  
	        }  
	    }  
	    return false;  
	}
	/**
	  * @Title: 获取当前网络连接的类型信息
	  * 获取当前的网络状态  -1：没有网络  1：WIFI网络2：wap网络3：net网络
	  * @Description: TODO
	  * @param @param context
	  * @param @return    设定文件
	  * @return int    返回类型
	  * @throws
	  */
	public static int getConnectedType(Context context) {  
	    if (context != null) {  
	        ConnectivityManager mConnectivityManager = (ConnectivityManager) context  
	                .getSystemService(Context.CONNECTIVITY_SERVICE);  
	        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();  
	        if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {  
	            return mNetworkInfo.getType();  
	        }  
	    }  
	    return -1;  
	}
	
}



















