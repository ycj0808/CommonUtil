package com.icefire.android.widget.verticaltextview;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

/**
 * 竖直显示文本
 * @author yangchj
 * @date 2014-8-13 下午8:51:15
 */
public class VerticalTextView extends TextView {

	public VerticalTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setSingleLine(false);
		//setBackgroundColor(Color.TRANSPARENT);
		setGravity(Gravity.CENTER_HORIZONTAL);
	}

	@Override
	public void setText(CharSequence text, BufferType type) {
		StringBuffer stringBuffer = new StringBuffer();
		if (text != null && text.length() > 0) {
			int length = text.length();
			for (int i = 0; i < length; i++)
				stringBuffer.append(text.charAt(i) + "\n");
		}
		super.setText(stringBuffer, type);
	}
}
