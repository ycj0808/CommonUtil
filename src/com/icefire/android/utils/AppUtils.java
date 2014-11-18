package com.icefire.android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;

/**
 * 应用信息相关的工具类
 * 
 * @author yangchj
 * @date 2014-11-18 下午2:37:50
 */
public class AppUtils {

	/**
	 * 获取meta 中的数据
	 * 
	 * @param ctx
	 * @param key
	 * @return
	 */
	public static String getMetaData(Context ctx, String key) {
		String value = "";
		if (TextUtils.isEmpty(key)) {
			return value;
		}
		try {
			ApplicationInfo ai;
			ai = ctx.getPackageManager().getApplicationInfo(
					ctx.getPackageName(), PackageManager.GET_META_DATA);
			Object objVal = ai.metaData.get(key);
			if (objVal != null) {
				value = objVal.toString();
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 获取应用版本号
	 * 
	 * @param context
	 * @return
	 */
	public static int getAppVersionCode(Context context) {
		int versonCode = 1;
		try {
			 PackageManager pm = context.getPackageManager();
			 PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
			 versonCode = pi.versionCode;
		} catch (Exception e) {
			 e.printStackTrace();
		}
		return versonCode;
	}
	/**
	 * 获取版本名称
	 * @param context
	 * @return
	 */
	public static String getAppVersionName(Context context){
		String versionName = "";
		try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
		} catch (Exception e) {
			e.printStackTrace();
		}
		return versionName;
	}
	
    /**
     * 获取包名
     * @param cxt
     * @return
     */
    public static String getPkgName(Context cxt) {
        PackageManager pManager = cxt.getPackageManager();
        PackageInfo pkgInfo = null;
        try {
            pkgInfo = pManager.getPackageInfo(cxt.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return pkgInfo.packageName;
    }	
}
