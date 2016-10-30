package mno.mohamed_youssef.myfaculty.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import mno.mohamed_youssef.myfaculty.fragment.ScheduleFragment;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class ScheduleTable {

    public DatabaseReference myRef;
    public static  ArrayList<Schedule> scheduleT;
    public Schedule scheduleR;
    private boolean rm;
    private SharedPreferences sharedPreferenceUser;
    private SharedPreferences sharedPreferencedate;
    private Context context;



    public ScheduleTable(DatabaseReference myRef,Context context) {
        scheduleT = new ArrayList<>();
        this.myRef = myRef;
        this.context = context;
        setSchedule();
    }

    public void  setSchedule() {
        myRef.child("Schedule").orderByChild("starttime")
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                scheduleT.clear();
                for (DataSnapshot d : dataSnapshot.getChildren()) {

                    scheduleR = d.getValue(Schedule.class);
                    scheduleR.setId(d.getKey());
                    if(sharedPreferencedate == null && sharedPreferenceUser ==null){
                        scheduleT.add(scheduleR);

                    }
                    else if (scheduleR.getDay().equals(sharedPreferencedate.getString("chosenDay", ""))
                                &&scheduleR.getSectionId().equals(sharedPreferenceUser.getString("SectionId",""))){
                            scheduleT.add(scheduleR);

                        }



                }

                if (ScheduleFragment.taskListAdapter !=null)
                    ScheduleFragment.taskListAdapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public  ArrayList<Schedule> getScheduleT() {

        sharedPreferencedate = context.getSharedPreferences("date", Context.MODE_PRIVATE);
        sharedPreferenceUser = context.getSharedPreferences("user", Context.MODE_PRIVATE);

        setSchedule();
        return scheduleT;
    }

    public  void addSchedule(Schedule schedule){


        myRef.child("Schedule").push().setValue(schedule);


    }



    public boolean remove(final String key){
        rm=false;
        myRef.child("Schedule").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("Schedule").removeValue();

    }



}
