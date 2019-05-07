package com.example.checkshop;

import android.app.Application;

import com.firebase.client.Firebase;

public class CheckShop extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);

    }
}
