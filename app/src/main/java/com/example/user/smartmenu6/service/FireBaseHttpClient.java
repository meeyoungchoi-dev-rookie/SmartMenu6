package com.example.user.smartmenu6.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class FireBaseHttpClient {

    public static OkHttpClient client=new OkHttpClient.Builder()
            .addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {

                    Request newRequest=chain.request().newBuilder()
                            .build();
                    return chain.proceed(newRequest);

                }
            })
            //.addNetworkInterceptor(new St)
            .connectTimeout(1, TimeUnit.MINUTES)
            .build();






}
