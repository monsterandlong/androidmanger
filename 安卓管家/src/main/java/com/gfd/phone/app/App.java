package com.gfd.phone.app;

import android.app.Application;


public class App extends Application{

    public static App appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
