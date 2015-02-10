package com.wci.android.anyrun.util;


import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.wci.android.anyrun.util.Persistence.PersistenceProvider;

public class JsonPersistenceProvider implements PersistenceProvider {

	private final Gson mGson;
	
	public JsonPersistenceProvider(Gson gson){
		mGson = gson;
	}
	
	@Override
	public String toString(Object object, String key) {
		if(object != null){
			return mGson.toJson(object);
		}else{
			return null;
		}
		
	}
	
	@Override
	public Object fromString(String string, Type targetType, String key){
		if(string != null){
			return mGson.fromJson(string, targetType);
		}else{
			return null;	
		}
	}
}
