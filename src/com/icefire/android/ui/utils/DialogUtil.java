package com.icefire.android.ui.utils;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

public class DialogUtil {
	/**
	 * 自定义等待框
	 * @param mContext
	 * @return
	 */
//	public static Dialog showProgressBar(Context mContext,String msg,boolean isCancel){
//		Dialog  mDialog = new AlertDialog.Builder(mContext).create();
//		mDialog.setCanceledOnTouchOutside(isCancel);
//		mDialog.show();
//		View view=View.inflate(mContext,R.layout.layout_progress, null);
//		TextView tv_msg=(TextView) view.findViewById(R.id.message);
//		tv_msg.setText(msg);
//		mDialog.setContentView(view);
//		return mDialog;
//	}
	/**
	 * 自定义等待框   默认不可取消
	 * @param mContext
	 * @param msg
	 * @return
	 */
//	public static Dialog showProgressBar(Context mContext,String msg){
//		return showProgressBar(mContext,msg,false);
//	}
	
	/**
	 * 显示等待框
	 * @param mContext
	 * @param view
	 * @param isCancel
	 * @return
	 */
	public static Dialog showProgressBar(Context mContext,View view,boolean isCancel){
		Dialog  mDialog = new AlertDialog.Builder(mContext).create();
		mDialog.setCanceledOnTouchOutside(isCancel);
		mDialog.show();
		mDialog.setContentView(view);
		return mDialog;
	}
	
	/**
	 * 显示等待框
	 * @param mContext
	 * @param view
	 * @return
	 */
	public static Dialog showProgressBar(Context mContext,View view){
		return showProgressBar(mContext,view,false);
	}
	/**
	 * 
	 * @param mContext
	 * @param titleId     标题
	 * @param messageId   内容
	 * @param positiveButtontxt
	 * @param positiveListener   确定
	 * @param negativeButtontxt  取消
	 * @param negativeListener
	 */
	public static void showAlertDialog(Context mContext, int titleId, int messageId,
			CharSequence positiveButtontxt, DialogInterface.OnClickListener positiveListener,
			CharSequence negativeButtontxt, DialogInterface.OnClickListener negativeListener){
		 Dialog dlg = new AlertDialog.Builder(mContext)
		 .setTitle(titleId)
		 .setPositiveButton(positiveButtontxt, positiveListener)
		 .setNegativeButton(negativeButtontxt, negativeListener)
		 .setMessage(messageId)
		 .setCancelable(false)
		 .create();
		 dlg.show();
	}
	/**
	 * 设置默认的警示框
	 * @param mContext
	 * @param titleId
	 * @param messageId
	 * @param positiveListener
	 * @param negativeListener
	 */
	public static void showAlertDialog(Context mContext, int titleId, int messageId,
			DialogInterface.OnClickListener positiveListener,
			DialogInterface.OnClickListener negativeListener){
		showAlertDialog(mContext,titleId,messageId,"确定",positiveListener,"取消",negativeListener);
	}
	
	/**
	 * 显示警示框
	 * @param mContext
	 * @param title
	 * @param message
	 * @param positiveButtontxt
	 * @param positiveListener
	 * @param negativeButtontxt
	 * @param negativeListener
	 */
	public static void showAlertDialog(Context mContext,String title,String message,
			CharSequence positiveButtontxt,DialogInterface.OnClickListener positiveListener,
			CharSequence negativeButtontxt,DialogInterface.OnClickListener negativeListener){
		Dialog dlg = new AlertDialog.Builder(mContext)
		 .setTitle(title)
		 .setMessage(message)
		 .setPositiveButton(positiveButtontxt, positiveListener)
		 .setNegativeButton(negativeButtontxt, negativeListener)
		 .setMessage(message)
		 .setCancelable(false)
		 .create();
		 
	}
	/**
	 * 只有一个确认按钮的警示框
	 * @param mContext
	 * @param titleId
	 * @param messageId
	 * @param positiveButtontxt
	 * @param positiveListener
	 */
	public static void showAlertDialog(Context mContext, int titleId, int messageId,
			CharSequence positiveButtontxt, DialogInterface.OnClickListener positiveListener){
		 Dialog dlg = new AlertDialog.Builder(mContext)
		 .setTitle(titleId)
		 .setPositiveButton(positiveButtontxt, positiveListener)
		 .setMessage(messageId)
		 .setCancelable(false)
		 .create();
		 dlg.show();
	}
	/**
	 * 显示警示Dialog
	 * @param mContext
	 * @param title
	 * @param message
	 * @param positiveButtontxt
	 * @param positiveListener
	 */
	public static void showAlertDialog(Context mContext,String title,String message,
			CharSequence positiveButtontxt, DialogInterface.OnClickListener positiveListener){
		 Dialog dlg = new AlertDialog.Builder(mContext)
		 .setTitle(title)
		 .setMessage(message)
		 .setPositiveButton(positiveButtontxt, positiveListener)
		 .setCancelable(false)
		 .create();
		 dlg.show();
	}
}
