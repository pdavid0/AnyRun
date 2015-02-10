package com.wci.android.anyrun.views;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

import com.wci.android.anyrun.LatoRegularFont;

/**
 *
 * Created by phil on 15-02-07.
 *
 */
public class CFontBtn extends Button{
	public CFontBtn(Context context) {
		super(context);
		setTypeface(LatoRegularFont.getInstance(context).getTypeFace());
	}

	public CFontBtn(Context context, AttributeSet attrs) {
		super(context, attrs);
		setTypeface(LatoRegularFont.getInstance(context).getTypeFace());
	}

	public CFontBtn(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		setTypeface(LatoRegularFont.getInstance(context).getTypeFace());
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	public CFontBtn(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		setTypeface(LatoRegularFont.getInstance(context).getTypeFace());

	}
}
