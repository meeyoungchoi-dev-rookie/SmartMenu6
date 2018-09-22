package com.example.user.smartmenu6.service;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FoodFirebaseInstanceIdService extends FirebaseInstanceIdService{


    public FoodFirebaseInstanceIdService() {
        super();
    }

    @Override
    public void onTokenRefresh() {

        super.onTokenRefresh();
        Log.d("refresh", FirebaseInstanceId.getInstance().getToken());

    }
}
