package com.example.newsmvvm;

import android.app.Application;
import android.util.Log;

import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate ();
        // OneSignal Initialization
        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();

        String playerId =OneSignal.getPermissionSubscriptionState ()
                .getSubscriptionStatus ()
                .getUserId ();


        Log.e ( "one signal" ,"PlayerId");
    }
}
