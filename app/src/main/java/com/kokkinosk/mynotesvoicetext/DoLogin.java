package com.kokkinosk.mynotesvoicetext;

import android.app.Activity;

import java.lang.ref.WeakReference;

public class DoLogin {

    private WeakReference<Activity> activityReference;


    public DoLogin(Activity loginActivity) {
        activityReference = new WeakReference<>(loginActivity);
    }


}