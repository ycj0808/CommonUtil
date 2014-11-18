package com.icefire.android.widget.customlistview;

import com.icefire.android.utils.LogUtil;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * 可以监听滑动到底部的{@link android.widget.ListView}
 * <p/>
 * lizheng -- lizheng@v1baobao.com
 * <p/>
 * V1宝宝
 */
public class ScrollEndListView extends ListView implements AbsListView.OnScrollListener{

    private static final boolean isShowLog = false;
    private static final String TAG = "** ScrollEndListView ** ";
    private OnScrollEndListener onScrollEndListener;
    
 // 滑动距离及坐标
    private float xDistance, yDistance, xLast, yLast;

    private boolean canScroll;
    
    private GestureDetector mGestureDetector;
    View.OnTouchListener mGestureListener;
    private int mLastItem;

    public ScrollEndListView(Context context) {
        super(context);
        setOnScrollListener(this);
    }


    public ScrollEndListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnScrollListener(this);
    }

    public ScrollEndListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        LogUtil.e(TAG + "onScrollStateChanged -- scrollState:" + scrollState);
        if (mLastItem == this.getAdapter().getCount() && scrollState == SCROLL_STATE_IDLE) {
        	 if (null!=onScrollEndListener){
               onScrollEndListener.onScrollEnd();
           }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    	mLastItem= firstVisibleItem + visibleItemCount;
        LogUtil.e(TAG + "onScroll -- firstVisibleItem:" + firstVisibleItem + " - visibleItemCount:" + visibleItemCount + " - totalItemCount:" + totalItemCount);
    }
    
    public void setOnScrollEndListener(OnScrollEndListener onScrollEndListener) {
        this.onScrollEndListener = onScrollEndListener;
    }

    public interface OnScrollEndListener {
        public void onScrollEnd();
    }
}
