package mno.mohamed_youssef.myfaculty.utils;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import mno.mohamed_youssef.myfaculty.Activity.Home;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.News;

/**
 * Created by Mohamed Yossif on 29/09/2016.
 */
public class MyAppServices extends Service {

    private Database database;
    @Override
    public void onCreate() {
        if(!FirebaseApp.getApps(this).isEmpty())
            database = new Database(this);

    }



    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }






}
