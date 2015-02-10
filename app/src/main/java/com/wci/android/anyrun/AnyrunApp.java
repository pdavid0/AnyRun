package com.wci.android.anyrun;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.gson.Gson;
import com.wci.android.anyrun.util.AnyrunPersistance;
import com.wci.android.anyrun.util.JsonPersistenceProvider;

/**
 * Created on 15-02-07.
 * @author phil
 */
public class AnyrunApp extends Application {
	private AnyrunPersistance mPersister;

	@Override public void onCreate() {
		super.onCreate();
//		Firebase myFirebaseRef = new Firebase(
//				String.format(
//						"%s%s",
//						getString(R.string.firebase_url),
//						"Users/-JhVsLxhhUf061lZsvil"
//				)
//		);
	}

	public void createPersistance(String name) {
		if (mPersister == null)
			mPersister = new AnyrunPersistance(name, new JsonPersistenceProvider(new Gson()));
	}

	public AnyrunPersistance getmPersister() {
		return mPersister;
	}


}
