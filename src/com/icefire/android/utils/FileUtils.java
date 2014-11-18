package com.icefire.android.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Locale;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;

/**
 * 文件工具类
 * @author yangchj
 * @date 2014-11-18 下午2:15:49
 */
public class FileUtils {
	/**
	 * Check the SD card
	 * @return
	 */
	public static boolean checkSDCardAvailable() {
		return android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);
	}
	
	/**
	 * 删除文件
	 * @param path  文件目录
	 * @param fileName  文件名称
	 */
	public static void deleteFileAtPathAndName(String path, String fileName){
        if (checkSDCardAvailable()) {
            File folder = new File(path);
            File[] files = folder.listFiles();
            for (File file : files) {
                if (file.getName().equals(fileName)) {
                    file.delete();
                }
            }
        }
	}
	
    /**
     * 文件是否存在
     * @param context ctx
     * @param fileName 文件名
     * @return true，存在、false，不存在
     */
    public static boolean isHave(Context context,String fileName){
        File file = new File(context.getFilesDir(),fileName);
        return file.exists();
    }
    
	/**
	 * 保存图片到SD卡
	 * 
	 * @param imagePath
	 * @param format
     * @param bitmap
	 * @throws IOException
	 */
	public static boolean saveImage(String imagePath, String format,
			Bitmap bitmap) throws IOException {
		File f = new File(imagePath);
		if (f.exists()) {
			f.delete();
		}

		File parentFile = f.getParentFile();
		if(parentFile==null){
			return false;
		}
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		f.createNewFile();
		FileOutputStream fos = new FileOutputStream(imagePath);

		CompressFormat cf = null;

		if (format.toLowerCase(Locale.getDefault()).contains("jpg")
				|| format.toLowerCase().contains("jpeg")) {
			cf = CompressFormat.JPEG;
		} else {
			cf = CompressFormat.PNG;
		}

		boolean result = bitmap.compress(cf, 80, fos);
		if (result) {
			fos.flush();
			fos.close();
		} else {
			if (f.exists()) {
				f.delete();
			}
		}
		return true;
	} 
	/**
	 * 删除缓存文件
	 * @param context
	 * @param fileName
	 * @return
	 */
    public static boolean deleteCacheFile(Context context,String fileName){
        File file = new File(context.getFilesDir(), fileName);
        return file.delete();
    }	
}
