package com.example.user.smartmenu6.service;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface FireBaseHttpApi {
@Headers({"Authorization: key=AAAAHoF5t-s:APA91bH68ptgQGxlh1OJDwoSiUFQikXg-jM_Rp2L4gkq0ubka8MDVVFikYmL49HBO9Vs4oYl4JgIMPfczL8b0sdc__v3dps_-ZQBOgYitqEMmXlf7sTtlvCzYfLltRRlDOlq-o87TMzB"
        ,"Content-Type:application/json"})
@POST("fcm/send")
    Call<ResponseBody> sendChatNotification(@Body Map<String, Object> requestNotification);


}
