package mno.mohamed_youssef.myfaculty.model;

import android.content.Context;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.InfoFragment;

/**
 * Created by Mohamed Yossif on 14/10/2016.
 */
public class ManageTable {


    public DatabaseReference myRef;
    public  Manage manage;
    public LinkedList<Manage> manageT;
    public Context context;
    private boolean rm;
    public ManageTable(DatabaseReference myRef) {


        this.myRef = myRef;
        manageT = new LinkedList<>();
        setManage();


    }


    public void setManage()
    {
        myRef.child("Manage").orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        manageT.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                            manage= d.getValue(Manage.class);
                            manage.setId(d.getKey());
                            manageT.addFirst(manage);
                        }

                       /* if(InfoFragment.infoAdapter!= null)

                            InfoFragment.infoAdapter.notifyDataSetChanged();
*/

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public LinkedList<Manage> getInfo() {

        return manageT;
    }




    public  void addInfo(Manage manage){


        myRef.child("Manage").push().setValue(manage);


    }


    public boolean remove(final String key){
        rm=false;
        myRef.child("Manage").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("Manage").removeValue();

    }

}
