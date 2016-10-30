package mno.mohamed_youssef.myfaculty.model;

import android.app.IntentService;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;


/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class Database {


    public Context context;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Faculty";
    public static  FirebaseStorage storage ;
    public static  FirebaseDatabase firebaseDatabase;
    public static  DatabaseReference myRef;
    public static  ScheduleTable scheduleTable;
    public static  InstructorTable instructorTable ;
    public static  LocationTable locationTable ;
    public static  SubjectTable subjectTable ;
    public static  SectionTable sectionTable ;
    public static  SectionsTable sectionsTable ;
    public static  LecturesTable lecturesTable ;
    public static  NewsTable  newsTable ;
    public static  InfoTable  infoTable ;
    public static  TeamInfoTable teamInfoTable;
    public static String adminPass="-1";
    public static String editorPass="-1";
    public static String rsite="";
    public static String tsite="";
    public static String fsite="";
    public static String fGsite="";



    public Database(Context context){
        this.context=context;
        storage = FirebaseStorage.getInstance();
        firebaseDatabase =  FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        myRef =firebaseDatabase.getReference();
        scheduleTable = new ScheduleTable(myRef,context);
        instructorTable = new InstructorTable(myRef);
        locationTable = new LocationTable(myRef);
        subjectTable = new SubjectTable(myRef);
        sectionTable = new SectionTable(myRef);
        newsTable = new NewsTable(myRef,storage,context);
        infoTable = new InfoTable(myRef);
        teamInfoTable = new TeamInfoTable(myRef,context);
        sectionsTable=new SectionsTable(myRef,context);
        lecturesTable=new LecturesTable(myRef,context);

        myRef.child("AdminPassword").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    adminPass = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        myRef.child("EditorPassword").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    editorPass = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        myRef.child("Rsite").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    rsite= dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




        myRef.child("Tsite").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    tsite = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        myRef.child("Fsite").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    fsite = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        myRef.child("FGsite").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    fGsite = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



}
