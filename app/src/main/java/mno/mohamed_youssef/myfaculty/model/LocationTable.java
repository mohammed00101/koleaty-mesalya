package mno.mohamed_youssef.myfaculty.model;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.LocationsFragment;
import mno.mohamed_youssef.myfaculty.fragment.ScheduleFragment;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class LocationTable {
    public DatabaseReference myRef;
    public LinkedList<Location> location;
    public Location locationR;
    private boolean rm;
    public LocationTable(DatabaseReference myRef) {

        this.myRef = myRef;
        location =new LinkedList<>();
        setlocation();

       // myRef.child("Location").push().setValue(new Subject(1,"ق100"));

    }

    public void setlocation(){


        myRef.child("Location")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        location.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                            locationR = d.getValue(Location.class);
                            locationR.setId(d.getKey());
                            location.addFirst(locationR);
                            // Log.d(">>>>>>>>>>>",id +" "+location.getId() + location.getLocation());

                        }


                        if(ScheduleFragment.taskListAdapter != null)
                        ScheduleFragment.taskListAdapter.notifyDataSetChanged();


                        if(LocationsFragment.locationsAdapter != null)
                            LocationsFragment.locationsAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    public String getLocation(final String id) {

        String locationName="";

        for (Location locat:location)
        if(locat.getId().equals(id)) {
            locationName = locat.getLocation();

        }
        return locationName;
    }

    public LinkedList<Location> getLocation() {
        return location;
    }


    public  void addLocation(Location location){


        myRef.child("Location").push().setValue(location);


    }




    public boolean remove(final String key){
        rm=false;
        myRef.child("Location").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("Location").removeValue();

    }



}
