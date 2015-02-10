package com.wci.android.anyrun.http;

import android.util.Log;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;
import com.wci.android.anyrun.http.requests.AbstractRequest;

import roboguice.util.temp.Ln;

/**
 * @author philippe
 */
public class RequestManager {

    private static final String KEY_LAST_REQUEST_CACHE_KEY = "lastRequestCacheKey";
    private static final String TAG = "RequestManager";
    private static RequestManager instance;

    public SpiceManager getSpiceManager() {
        return new SpiceManager(JsonSpiceService.class);
    }

    public static RequestManager getInstance() {
        if (instance == null)
            instance = new RequestManager();

        return instance;
    }

    public RequestManager() {
        Ln.getConfig().setLoggingLevel(Log.ERROR);
    }

    /**
     * Perform a HTTP request. The cache will be queried first.
     *
     * @param request       The request to execute
     * @param listener      The listener to notify
     * @param cacheDuration see {@link com.octo.android.robospice.persistence.DurationInMillis}
     * @param spiceManager  The service to execute the request.
     */
    public void performRequest(AbstractRequest request, RequestListener<?> listener, long cacheDuration, SpiceManager spiceManager) {
        String lastRequestCacheKey = request.createCacheKey();

        //print request url
        Log.w(TAG, request.toString());

        spiceManager.getFromCacheAndLoadFromNetworkIfExpired(request, lastRequestCacheKey,
                cacheDuration, listener);
    }

    public void cancelAllRequests() {
        getSpiceManager().cancelAllRequests();
    }
}
