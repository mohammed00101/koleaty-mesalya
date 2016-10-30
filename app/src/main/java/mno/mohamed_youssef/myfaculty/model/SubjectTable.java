package mno.mohamed_youssef.myfaculty.model;

import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.ScheduleFragment;
import mno.mohamed_youssef.myfaculty.fragment.SubjectFragment;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class SubjectTable {

    public DatabaseReference myRef;
    public LinkedList<Subject> subject;
    public Subject subjectR;
    private boolean rm;

    public SubjectTable(DatabaseReference myRef) {

        this.myRef = myRef;
        setSubject();
        subject = new LinkedList<>();


        //myRef.child("Subject").push().setValue(new Subject(1,"عربى"));

    }
    public void setSubject(){


        myRef.child("Subject")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        subject.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                            subjectR =d.getValue(Subject.class);
                            subjectR.setId(d.getKey());
                            subject.addFirst(subjectR);
                            // Log.d(">>>>>>>>>>>",id +" "+subject.getId() + subject.getSubjectName());

                        }

                        if(ScheduleFragment.taskListAdapter != null)

                            ScheduleFragment.taskListAdapter.notifyDataSetChanged();

                        if(SubjectFragment.subjectAdapter != null)
                            SubjectFragment.subjectAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    public String getSubject(final String id) {

         String subjectName="";

        for (Subject sub:subject)
        if(sub.getId().equals(id)) {
            subjectName = sub.getSubjectName();
        }
        return subjectName;
    }


    public LinkedList<Subject> getSubject() {
        return subject;
    }

    public  void addSubject(Subject subject){


        myRef.child("Subject").push().setValue(subject);


    }


    public boolean remove(final String key){
        rm=false;
        myRef.child("Subject").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("Subject").removeValue();

    }

}
