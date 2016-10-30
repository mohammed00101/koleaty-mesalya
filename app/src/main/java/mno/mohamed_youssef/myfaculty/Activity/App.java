package mno.mohamed_youssef.myfaculty.Activity;

import android.app.Application;
import android.content.Intent;

import mno.mohamed_youssef.myfaculty.notifications.NewsNotifications;
import mno.mohamed_youssef.myfaculty.utils.MyAppServices;

/**
 * Created by Mohamed Yossif on 29/09/2016.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        startService(new Intent(this, MyAppServices.class));
        //startService(new Intent(this, NewsNotifications.class));

    }
}
