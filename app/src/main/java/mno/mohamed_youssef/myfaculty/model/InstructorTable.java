package mno.mohamed_youssef.myfaculty.model;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.DoctorsFragment;
import mno.mohamed_youssef.myfaculty.fragment.ScheduleFragment;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class InstructorTable {
    public DatabaseReference myRef;
    public Instructor instructorR;
    public LinkedList<Instructor> instructor;
    private boolean rm;
    public InstructorTable(DatabaseReference myRef) {


        this.myRef = myRef;
        instructor = new LinkedList<>();
        setInstructor();


        //myRef.child("instructor").push().setValue(new Instructor(1,"د","على","ايراهيم"));

    }


    public void setInstructor()
    {
        myRef.child("instructor")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        instructor.clear();

                        for(DataSnapshot d : dataSnapshot.getChildren()) {

                            instructorR = d.getValue(Instructor.class);
                            instructorR.setId(d.getKey());
                            instructor.addFirst(instructorR);
                            // Log.d(">>>>>>>>>>>",id +" "+instructor.getId() + instructor.getInstLName());

                        }
                        if(ScheduleFragment.taskListAdapter != null)

                            ScheduleFragment.taskListAdapter.notifyDataSetChanged();

                        if(DoctorsFragment.doctorsAdapter != null)

                            DoctorsFragment.doctorsAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public String getInstructor(final String id) {


        String instructorName="";

        for (Instructor instr : instructor)
        if(instr.getId().equals(id)) {

            instructorName = instr.getInstNkname() + "/"
                    + instr.getInstFName() + " " + instr.getInstLName();


        }


        return instructorName;
    }

    public LinkedList<Instructor> getInstructor() {
        return instructor;
    }


    public  void addDoctor(Instructor instructor){


        myRef.child("instructor").push().setValue(instructor);


    }



    public boolean remove(final String key){
        rm=false;
        myRef.child("Info").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("Info").removeValue();

    }

}
