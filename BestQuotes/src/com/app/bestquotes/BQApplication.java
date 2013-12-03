package com.app.bestquotes;


import android.app.Application;
import android.content.Context;

public class BQApplication extends Application
{
    private static BQApplication instance = null;

    /*private BQApplication()
    {
        instance = this;
    }*/
    
    @Override
    public void onCreate() {
            super.onCreate();
            
    }

    public static Context getInstance()
    {
        if (null == instance)
        {
            instance = new BQApplication();
        }

        return instance;
    }
}
