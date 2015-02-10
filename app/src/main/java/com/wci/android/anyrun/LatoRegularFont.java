package com.wci.android.anyrun;

import android.content.Context;
import android.graphics.Typeface;

/**
 *
 * Created by phil on 15-02-07.
 *
 */
public class LatoRegularFont {

	private static LatoRegularFont instance;
	private static Typeface        typeface;

	public static LatoRegularFont getInstance(Context context) {
		synchronized (LatoRegularFont.class) {
			if (instance == null) {
				instance = new LatoRegularFont();
				typeface = Typeface.createFromAsset(context.getResources().getAssets(), "Lato-Regular.ttf");
			}
			return instance;
		}
	}

	public Typeface getTypeFace() {
		return typeface;
	}
}
