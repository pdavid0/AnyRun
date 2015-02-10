package com.wci.android.anyrun.http.requests;

/**
 * Created by philippe on 14-07-02.
 */

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Random;

/**
 * Abstract Typed Request, extends this class to have a base for restfull HTTP calls
 *
 * @param <T> The type of the result
 */
public abstract class AbstractRequest<T> extends SpringAndroidSpiceRequest<T> {

    protected final String mUrl;
    protected final Context mContext;
    protected final Gson gson;
    private String cacheKey;

    public AbstractRequest(String url, Class<T> clazz, Context c) {
        super(clazz);
        this.mUrl = url;
        this.mContext = c;

        this.gson = new GsonBuilder().disableHtmlEscaping().create();
    }

    /**
     * Create a body for the request with header Authorisation.
     * This is completely optional, the body can be null.
     *
     * @param payload can be null
     * @param api_key
     * @return an {@link org.springframework.http.HttpEntity} to be used with a request
     */
    protected HttpEntity<?> createEntity(Object payload, String api_key) {
        // Set the Content-Type header
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", "api_key=" + api_key);
        requestHeaders.setContentType(new MediaType("application", "json"));

        HttpEntity<?> requestEntity = new HttpEntity<Object>(payload, requestHeaders);

        return requestEntity;
    }

    /**
     * This method generates a unique cache key for this request. In this case
     * our cache key depends just on the keyword.
     *
     * @return
     */
    public String createCacheKey() {
        if (cacheKey == null)
            cacheKey = mContext.getPackageName() + new Random().nextInt(Math.abs((int) System.nanoTime()));
        return cacheKey;
    }

    @Override
    public String toString() {
        return mUrl;
    }

    public String getCacheKey() {
        if (cacheKey == null) return createCacheKey();
        else return cacheKey;
    }
}