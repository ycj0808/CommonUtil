package com.icefire.android.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import android.annotation.SuppressLint;
import android.util.Base64;

/**
 * 安全类方法的处理方法
 * 
 * @author yangchj
 */
public class SecurityUtils {
	
	private static final int BUFFER_SIZE = 1024;
	/**
	 * 对字符串进行Base64编码
	 * 
	 * @param content
	 *            需要编码的的字符
	 * @return 返回编码成功后的字符串
	 */
	@SuppressLint("NewApi")
	public static String base64(String content) {
		try {
			content = Base64.encodeToString(content.getBytes("utf-8"),
					Base64.DEFAULT); // 对字符串进行Base64编码
			content = StrEncoder(content, "utf-8"); // 对字符串进行URL编码
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace(); // 输出异常信息
		}
		return content;
	}

	/**
	 * 对URL的字符串进行编码
	 * 
	 * @param content
	 *            需要编码的字符串
	 * @param charset
	 *            需要编码的类型 如:utf-8,gb2312
	 * @return
	 */
	public static String StrEncoder(String str, String charset) {
		String Result = str;
		try {
			Result = URLEncoder.encode(str, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Result;
	}

	/**
	 * 对URL的字符串进行解码
	 * 
	 * @param content
	 *            需要解码的字符串
	 * @param charset
	 *            需要编码的类型 如:utf-8,gb2312
	 * @return
	 */
	public static String StrDecoder(String str, String charset) {
		String Result = str;
		try {
			Result = URLDecoder.decode(str, charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Result;
	}

	/**
	 * BASE64 加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encryptBASE64(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			byte[] encode = str.getBytes("UTF-8");
			// base64 加密
			return new String(Base64.encode(encode, 0, encode.length,
					Base64.DEFAULT), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * BASE64 解密
	 * 
	 * @param str
	 * @return
	 */
	public static String decryptBASE64(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}
		try {
			byte[] encode = str.getBytes("UTF-8");
			// base64 解密
			return new String(Base64.decode(encode, 0, encode.length,
					Base64.DEFAULT), "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * GZIP 加密
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] encryptGZIP(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		try {
			// gzip压缩
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gzip = new GZIPOutputStream(baos);
			gzip.write(str.getBytes("UTF-8"));
			gzip.close();
			byte[] encode = baos.toByteArray();
			baos.flush();
			baos.close();

			// base64 加密
			return encode;
			// return new String(encode, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * GZIP 解密
	 * 
	 * @param str
	 * @return
	 */
	public static String decryptGZIP(String str) {
		if (str == null || str.length() == 0) {
			return null;
		}

		try {

			byte[] decode = str.getBytes("UTF-8");

			// gzip 解压缩
			ByteArrayInputStream bais = new ByteArrayInputStream(decode);
			GZIPInputStream gzip = new GZIPInputStream(bais);

			byte[] buf = new byte[BUFFER_SIZE];
			int len = 0;

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			while ((len = gzip.read(buf, 0, BUFFER_SIZE)) != -1) {
				baos.write(buf, 0, len);
			}
			gzip.close();
			baos.flush();

			decode = baos.toByteArray();

			baos.close();

			return new String(decode, "UTF-8");

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 十六进制字符串 转换为 byte[]
	 * 
	 * @param hexString
	 *            the hex string
	 * @return byte[]
	 */
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	/**
	 * Convert char to byte
	 * 
	 * @param c
	 *            char
	 * @return byte
	 */
	private static byte charToByte(char c) {
		return (byte) "0123456789abcdef".indexOf(c);
		// return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/**
	 * byte[] 转换为 十六进制字符串
	 * 
	 * @param src
	 * @return
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");

		if (src == null || src.length <= 0) {
			return null;
		}

		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}
	
}
