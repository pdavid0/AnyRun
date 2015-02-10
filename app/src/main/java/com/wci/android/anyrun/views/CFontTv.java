package com.wci.android.anyrun.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.wci.android.anyrun.LatoRegularFont;

/**
 * Created by phil on 15-02-07.
 */
public class CFontTv extends TextView {

	public CFontTv(Context context) {
		super(context);
		setTypeface(LatoRegularFont.getInstance(context).getTypeFace());
	}

	public CFontTv(Context context, AttributeSet attrs) {
		super(context, attrs);
		setTypeface(LatoRegularFont.getInstance(context).getTypeFace());
	}

	public CFontTv(Context context, AttributeSet attrs,
	               int defStyle) {
		super(context, attrs, defStyle);
		setTypeface(LatoRegularFont.getInstance(context).getTypeFace());
	}

}