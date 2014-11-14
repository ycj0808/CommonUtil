package com.icefire.android.widget.webview;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
/**
 * 自定义WebView适应ScrollView
 * @author yangchj
 * @date 2014-8-19 下午3:23:29
 */
public class WebViewForScrollView extends WebView{

	public WebViewForScrollView(Context context) {
		super(context);
	}

	public WebViewForScrollView(Context context,AttributeSet attrs) {
		super(context,attrs);
	}
	
	public WebViewForScrollView(Context context,AttributeSet attrs,int defStyle) {
		super(context,attrs,defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
