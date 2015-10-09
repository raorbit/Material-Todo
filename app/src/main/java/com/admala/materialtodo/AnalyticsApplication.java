package com.admala.materialtodo;

import android.app.Application;
import android.content.pm.PackageManager;

import com.example.admala.materialtodo.R;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

import java.util.Map;

public class AnalyticsApplication extends Application {

    private Tracker mTracker;
    private static final boolean IS_ENABLED = true;
    synchronized private Tracker getDefaultTracker(){
        if(mTracker==null){
            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);

            /*R.xml.app_tracker contains my Analytics code
            To use this, go to Google Analytics, and get
            your code, create a file under res/xml , and save
            your code as <string name="ga_trackingId">UX-XXXXXXXX-Y</string>
            Due to security reasons i have not provided my own analytics id.
            */

            mTracker = analytics.newTracker(R.xml.app_tracker);
            mTracker.setAppName("To Do List");
            mTracker.enableExceptionReporting(true);
            try{
                mTracker.setAppId(getPackageManager().getPackageInfo(getPackageName(),0).versionName);
            }
            catch (PackageManager.NameNotFoundException e){
                e.printStackTrace();
            }

        }
        return mTracker;
    }

    public void send(Object screenName) {
        send(screenName, new HitBuilders.ScreenViewBuilder().build());
    }

    public void send(Object screenName, Map<String,String> params) {
        if(IS_ENABLED) {
            Tracker tracker = getDefaultTracker();
            tracker.setScreenName(getClassName(screenName));
            tracker.send(params);
        }
    }

    private String getClassName(Object o) {
        Class c = o.getClass();
        while(c.isAnonymousClass()) {
            c = c.getEnclosingClass();
        }
        return c.getSimpleName();

    }
}
