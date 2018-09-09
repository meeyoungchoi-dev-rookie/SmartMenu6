package com.example.user.smartmenu6.service;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class FoodFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {


        Log.d("pushMessage", remoteMessage.getNotification().getBody());

        super.onMessageReceived(remoteMessage);
    }
}
