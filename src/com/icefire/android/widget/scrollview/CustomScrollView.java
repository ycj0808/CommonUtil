package com.icefire.android.widget.scrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 自定义ScrollView,仿美团滑动固定某一控件
 * @author yangchj
 * @date 2014-9-25 上午9:22:32
 */
public class CustomScrollView extends ScrollView {
	private OnScrollListener onScrollListener;
	public CustomScrollView(Context context) {
		this(context,null);
	}

	public CustomScrollView(Context context, AttributeSet attrs){
		this(context, attrs, 0);
	}
	
	public CustomScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	/**
	 * 设置滚动接口
	 * @param onScrollListener
	 */
	public void setOnScrollListener(OnScrollListener onScrollListener) {
		this.onScrollListener = onScrollListener;
	}
	/**
	 * 滚动
	 */
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt) {
		super.onScrollChanged(l, t, oldl, oldt);
		if(onScrollListener != null){
			onScrollListener.onScroll(t);
		}
	}
	
	/**
	 * 滚动的回调接口
	 */
	public interface OnScrollListener{
		/**
		 * 回调方法， 返回MyScrollView滑动的Y方向距离
		 * @param scrollY
		 */
		public void onScroll(int scrollY);
	}
}
