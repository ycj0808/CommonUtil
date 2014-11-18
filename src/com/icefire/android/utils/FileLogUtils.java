package com.icefire.android.utils;

import java.io.File;
import android.os.Environment;
import android.text.TextUtils;

/**
 * 文件日志类
 * @author yangchj
 * @date 2014-11-18 下午2:24:16
 */
public class FileLogUtils {
	
	private static final boolean isShowLog = false;
	private static final String TAG = "** FileLogUtil ** ";
	
    /**
     * 是否可以记录日志
     * @return true，可以、false，不可以
     */
    private static boolean isCanRecord() {
        return FileUtils.checkSDCardAvailable();
    }
    
    
}
