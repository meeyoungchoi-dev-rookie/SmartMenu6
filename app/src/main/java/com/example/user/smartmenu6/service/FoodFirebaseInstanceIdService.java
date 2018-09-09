package com.example.user.smartmenu6.service;

import com.google.firebase.iid.FirebaseInstanceIdService;

public class FoodFirebaseInstanceIdService extends FirebaseInstanceIdService{


    public FoodFirebaseInstanceIdService() {
        super();
    }

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
    }
}
