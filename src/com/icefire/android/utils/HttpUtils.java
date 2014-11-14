package com.icefire.android.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExecutor;
import com.litesuits.http.data.HttpStatus;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.parser.BitmapParser;
import com.litesuits.http.parser.FileParser;
import com.litesuits.http.request.Request;
import com.litesuits.http.request.content.MultipartBody;
import com.litesuits.http.request.content.UrlEncodedFormBody;
import com.litesuits.http.request.content.multi.FilePart;
import com.litesuits.http.request.content.multi.InputStreamPart;
import com.litesuits.http.request.content.multi.StringPart;
import com.litesuits.http.request.param.HttpMethod;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpResponseHandler;

import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;


/**
 * 网络连接工具类(Apache的HttpClient 和Android自带的URLConnection)
 * @author yangchj
 * @date 2014-11-12 下午5:15:34
 */
public class HttpUtils {

	private static int TIME_OUT_SOCKET_DELAY = 60 * 1000;// 响应时间
	private static int TIME_OUT_CONN_DELAY = 60 * 1000;// 连接时间
	
	private static final int TIME_OUT=9001;//超时
	private static final int RESPONSE_EXCEPTION=9002;//应答异常
	
	/**
	 * Apache的HttpClient使用的post请求
	 * @param url
	 * @param params
	 * @param handler
	 * @return
	 */
	public static String sendPostRequest(String url, Map<String, String> params, Handler handler){
		String result = "";
		List<NameValuePair> pairs = new ArrayList<NameValuePair>();// 存放请求参数
		if (params != null && !params.isEmpty()) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				pairs.add(new BasicNameValuePair(entry.getKey(), entry
						.getValue()));
			}
		}
		HttpPost httpPost = new HttpPost(url);
		HttpClient httpClient = initHttp();
		HttpResponse httpResponse = null;
		try {
			httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
			httpPost.setHeader("Charset", "UTF-8");
			// 设置httpPost请求参数
			httpPost.setEntity(new UrlEncodedFormEntity(pairs, HTTP.UTF_8));
			httpResponse = httpClient.execute(httpPost);
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (ConnectTimeoutException e) {// 超时异常
			if(handler!=null){
				handler.sendEmptyMessage(TIME_OUT);
			}
			e.printStackTrace();
		} catch (IOException e) {// 响应异常
			if(handler!=null){
				handler.sendEmptyMessage(RESPONSE_EXCEPTION);
			}
			e.printStackTrace();
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
		return result;
	}
	/**
	 * 初始化HttpClient
	 * @return
	 */
	public static HttpClient initHttp() {
		HttpClient client = new DefaultHttpClient();
		client.getParams().setIntParameter(
				HttpConnectionParams.CONNECTION_TIMEOUT, TIME_OUT_CONN_DELAY);// 连接超时
		client.getParams().setIntParameter(HttpConnectionParams.SO_TIMEOUT,
				TIME_OUT_SOCKET_DELAY);// 响应超时
		return client;
	}
	
	/**
	 * 使用三方的LiteHttp发送post请求
	 * @param url
	 * @return
	 */
	public static Request initPostRequest(String url,Map<String,String> paramMap){
		Request req=new Request(url);
		req.setMethod(HttpMethod.Post);
		if(paramMap!=null&&!paramMap.isEmpty()){
			List<com.litesuits.http.data.NameValuePair> pairs = new ArrayList<com.litesuits.http.data.NameValuePair>();// 存放请求参数
			for (Map.Entry<String, String> entry : paramMap.entrySet()){
				pairs.add(new com.litesuits.http.data.NameValuePair(entry.getKey(), entry.getValue()));
			}
			req.setHttpBody(new UrlEncodedFormBody(pairs));
		}
		return req;
	}
	/**
	 * 发送带参数的请求,及文件上传
	 * @param url
	 * @param paramMap
	 * @param listFilePath
	 * @param client
	 * @return
	 */
	public static Response upLoadMultiBody(String url,Map<String,String> paramMap,List<String> listFilePath,LiteHttpClient client){
		Request req = new Request(url);
		MultipartBody body = new MultipartBody();
		if(paramMap!=null&&!paramMap.isEmpty()){
			for (Map.Entry<String, String> entry : paramMap.entrySet()){
				body.addPart(new StringPart(entry.getKey(), entry.getValue()));
			}
		}
		//文件上传
		if(listFilePath!=null&&!listFilePath.isEmpty()){
			for(int i=0;i<listFilePath.size();i++){
				body.addPart(new FilePart("pic", new File(listFilePath.get(i)), "image/jpeg"));
//				body.addPart(new FilePart("pic", new File(listFilePath.get(i))));
//				body.addPart(new FilePart("pic", new File("sdcard/apic.png"), "image/jpeg"));
//		        body.addPart(new FilePart("song", new File("sdcard/asong.mp3"), "audio/x-mpeg"));
//		        body.addPart(new InputStreamPart("alog", fis, "alog.xml", "text/xml"));
			}
		}
        req.setMethod(HttpMethod.Post).setHttpBody(body);
        Response res = client.execute(req);
        return res;
	}
	
	/************************************************
	 * 使用initPostRequest
	 * LiteHttpClient client = LiteHttpClient.newApacheHttpClient(context);
	 * HttpAsyncExecutor asyncExcutor=HttpAsyncExecutor.newInstance(client);
	 * asyncExcutor.execute(req, new HttpResponseHandler() {
	 * @Override
	 * protected void onSuccess(Response res,HttpStatus status, NameValuePair[] headers) {
	 * 		Log.i("TAG",res.getString());
	 * }
	 * 
	 * @Override
	 * protected void onFailure(Response res, HttpException e) {
	 * 		Log.i("TAG", res.getString());
	 * }
	 * });
	 * 
	 * 带HttpException : ClientException + NetworkException + ServerException
	 * 
	 * Request req = new Request(url).setMethod(HttpMethod.Head);
	 * HttpAsyncExcutor asyncExcutor = new HttpAsyncExcutor();
	 * asyncExcutor.execute(client, req, new HttpResponseHandler(){
	 *  @Override
     *	public void onSuccess(Response response, HttpStatus status, NameValuePair[] headers) {
     *   	response.getBitmap();
     *   	// do some thing on ui thread
     *	}
	 * 
	 *	@Override
     *	public void onFailure(Response response, HttpException e) {
     *
        new HttpExceptionHandler() {
            @Override
            protected void onClientException(HttpClientException e, ClientException type) {
                // Client Exception
            }

            @Override
            protected void onNetException(HttpNetException e, NetException type) {
                if (type == NetException.NetworkError) {
                    // NetWork Unconnected
                } else if (type == NetException.UnReachable) {
                    // NetWork UnReachable
                } else if (type == NetException.NetworkDisabled) {
                    // Network Disabled
                }
            }

            @Override
            protected void onServerException(HttpServerException e, ServerException type, HttpStatus status, NameValuePair[] headers) {
                // Server Exception
            }

        	}.handleException(e);
    	  }
		});
	 ************************************************/
	
	/**
	 * 文件下载
	 * @param imageUrl 网络地址
	 * @param fileUrl  文件存储地址
	 * @param client
	 * @return
	 */
	public File getFile(String imageUrl,String fileUrl,LiteHttpClient client){
		return client.execute(imageUrl, new FileParser(fileUrl), HttpMethod.Get);
	}
	
	
	/**
	 * 文件下载二
	 * @param imageUrl
	 * @param client
	 * @return 
	 */
	public Bitmap getFile(String imageUrl,LiteHttpClient client){
		Response res = client.execute(new Request(imageUrl).setDataParser(new BitmapParser()));
		return res.getBitmap();
	}
	
	//使用文件下载二
    /*runOnUiThread(new Runnable() {
        @Override
        public void run() {
            new HttpResponseHandler() {
                @Override
                public void onSuccess(Response response, HttpStatus status, NameValuePair[] headers) {
                    addImageViewToBottom(response.getBitmap());
                }
                @Override
                public void onFailure(Response response, HttpException exception) {
                    toast("加载图片失败");
                }
            }.handleResponse(res);
        }
    });*/
}
