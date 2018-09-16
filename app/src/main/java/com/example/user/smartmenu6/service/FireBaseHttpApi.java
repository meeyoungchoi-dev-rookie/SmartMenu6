package com.example.user.smartmenu6.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FireBaseHttpApi {
@Headers({"Authorization: key=AAAA6WS6qEY:APA91bGTuYEflyIY7P_qGd9PEggqkKKC9snKh6s8Eos8sA7KNAkIa3GBUVVtgjNjgRErjQdaskoPgquLRmUAHQ9bCUbv9YuvkhU4Ez6lDnaowWZ5xfZCyLPF-FSw0vVIHP-TrE-Fbc2s"
        ,"Content-Type:application/json"})
@POST("fcm/send")
    Call<ResponseBody> sendChatNotification(@Body Map<String, Object> requestNotification);


}
