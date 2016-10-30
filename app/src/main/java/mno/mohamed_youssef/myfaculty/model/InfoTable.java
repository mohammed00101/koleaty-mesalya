package mno.mohamed_youssef.myfaculty.model;

import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.InfoFragment;
import mno.mohamed_youssef.myfaculty.fragment.News_Fragment;

/**
 * Created by Mohamed Yossif on 21/09/2016.
 */
public class InfoTable {

    public DatabaseReference myRef;
    public  Info infoR;
    public LinkedList<Info> info;
    public Context context;
    private Integer index = new Integer(0) ;
    private boolean rm;
    public InfoTable(DatabaseReference myRef) {


        this.myRef = myRef;
        info = new LinkedList<>();
        setInfo();


    }


    public void setInfo()
    {
        myRef.child("Info").orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        info.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                           infoR= d.getValue(Info.class);
                            infoR.setId(d.getKey());
                            info.addFirst(infoR);
                        }

                        if(InfoFragment.infoAdapter!= null)

                            InfoFragment.infoAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public LinkedList<Info> getInfo() {

        return info;
    }




    public  void addInfo(Info info){


        myRef.child("Info").push().setValue(info);


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


