package com.wci.android.anyrun.http;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.exception.CacheCreationException;
import com.octo.android.robospice.persistence.springandroid.json.gson.GsonObjectPersisterFactory;

import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by philippe on 14-07-03.
 */
public class JsonSpiceService extends SpringAndroidSpiceService {

    @Override
    public CacheManager createCacheManager(Application application) throws CacheCreationException {
        CacheManager cacheManager = new CacheManager();
        GsonObjectPersisterFactory gsonObjectPersisterFactory = new GsonObjectPersisterFactory(application);
        cacheManager.addPersister(gsonObjectPersisterFactory);
        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();


        // web services support json responses
        GsonHttpMessageConverter jsonConverter = new GsonHttpMessageConverter();
        final List<HttpMessageConverter<?>> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add(jsonConverter);
        listHttpMessageConverters.add(new StringHttpMessageConverter());
        restTemplate.setMessageConverters(listHttpMessageConverters);
        return restTemplate;
    }
}