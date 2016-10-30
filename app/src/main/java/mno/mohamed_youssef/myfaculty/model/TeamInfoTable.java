package mno.mohamed_youssef.myfaculty.model;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.News_Fragment;
import mno.mohamed_youssef.myfaculty.fragment.TeamInfoFragment;

/**
 * Created by Mohamed Yossif on 21/09/2016.
 */
public class TeamInfoTable {

    public DatabaseReference myRef;
    public LinkedList<TeamInfo> teamInfo;
    public Context context;
    private TeamInfo teamInfoR;
    private boolean rm;
    public TeamInfoTable(DatabaseReference myRef, Context context) {


        this.myRef = myRef;
        this.context = context;
        teamInfo = new LinkedList<>();
        setTeamInfo();


    }


    public void setTeamInfo()
    {
        myRef.child("TeamInfo").orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        teamInfo.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                            teamInfoR = d.getValue(TeamInfo.class);
                            teamInfoR.setId(d.getKey());
                            //Log.d(">>>>>",teamInfoR.getImage()) ;
                            teamInfo.addFirst(teamInfoR);
                           if (teamInfoR.getImage() !=null)
                                Glide.with(context).load(Uri.parse(teamInfoR.getImage())).diskCacheStrategy(DiskCacheStrategy.ALL);
                            //Log.d(">>>>>>>>>>",d.getValue(News.class).getTitle());

                        }

                        if(TeamInfoFragment.infoTeamAdapter!= null)

                            TeamInfoFragment.infoTeamAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public LinkedList<TeamInfo> getTeamInfo() {

        return teamInfo;
    }




    public  String addTeamInfo(TeamInfo teamInfo){

        String key = myRef.push().getKey();

        myRef.child("TeamInfo").child(key).setValue(teamInfo);
       return key;


    }

    public void setImageUri(String uri , String key){
        myRef.child("TeamInfo/"+key+"/image").setValue(uri);


    }

    public boolean remove(final String key){
        rm=false;
        myRef.child("TeamInfo").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
                Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesTeam/" +key+ ".png").delete();
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("TeamInfo").removeValue();
        Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesTeam/").delete();
    }
}


