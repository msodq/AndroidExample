package com.sodiq.postsecure.utils.rest;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by Mohamad Sodiq on 24/08/2015.
 */
public class RestClient {
    private static final String BASE_URL = "http://myweb.com/api/v1/";
    private static AsyncHttpClient client = new AsyncHttpClient();

    private static String getApiUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

    public static void get(String url, RequestParams params,
                           AsyncHttpResponseHandler responseHandler) {
        client.get(getApiUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params,
                            AsyncHttpResponseHandler responseHandler) {
        client.post(getApiUrl(url), params, responseHandler);
    }

    public static void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put(getApiUrl(url), params,
                responseHandler);
    }

    public static void delete(String url,
                              AsyncHttpResponseHandler responseHandler) {
        client.delete(getApiUrl(url), responseHandler);
    }
}
