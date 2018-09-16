package com.example.user.smartmenu6.service;

import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FireBaseHttpRequestConnector extends AsyncTask<Map<String, Object> ,Void, String> {
    private static final String baseURL="https://fcm.googleapis.com/";
    public final Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(baseURL)
            .client(FireBaseHttpClient.client)
            .addConverterFactory(GsonConverterFactory.create())

            .build();

    public FireBaseHttpRequestConnector() {
        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(String s) {
        super.onCancelled(s);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected String doInBackground(Map<String, Object>... maps) {

        FireBaseHttpApi api=retrofit.create(FireBaseHttpApi.class);

        Call<ResponseBody> call = api.sendChatNotification(maps[0]);
        String retVal="";
        try {
            Response<ResponseBody> responseBody=call.execute();
            ResponseBody rtnBody=responseBody.body();

            retVal=responseBody.body().toString();
            Log.d("firbase http retVal", rtnBody.string());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return  retVal;

        }
    @MainThread
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

}
