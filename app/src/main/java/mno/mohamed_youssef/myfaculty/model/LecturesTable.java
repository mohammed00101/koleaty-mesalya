package mno.mohamed_youssef.myfaculty.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.LecturesFragment;
import mno.mohamed_youssef.myfaculty.fragment.TeamInfoFragment;

/**
 * Created by Mohamed Yossif on 08/10/2016.
 */
public class LecturesTable {

    public DatabaseReference myRef;
    public LinkedList<Lectures> lectures;
    public Context context;
    private Lectures lecturesR;
    private SharedPreferences sharedPreferenceUser;
    private SharedPreferences sharedPreferencesSubjectId;
    private String subjectid="";
    private String sectionId="";
    private boolean rm;



    public LecturesTable(DatabaseReference myRef, Context context) {


        this.myRef = myRef;
        this.context = context;
        lectures = new LinkedList<>();

        setLectures();


    }


    public void setLectures()
    {
        myRef.child("Lectures").orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        lectures.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                            lecturesR = d.getValue(Lectures.class);
                            lecturesR.setId(d.getKey());
                            if(!sectionId.equals("") && !subjectid.equals("")&&lecturesR.getSectionId().equals(sectionId)&&lecturesR.getSubjectId().equals(subjectid)){

                                lectures.addFirst(lecturesR);
                            }
                            else if(!sectionId.equals("")&&lecturesR.getSectionId().equals(sectionId)){
                                lectures.addFirst(lecturesR);
                            }
                            else{
                                lectures.addFirst(lecturesR);
                            }
                        }

                       if(LecturesFragment.lecturesAdapter!= null)

                           LecturesFragment.lecturesAdapter.notifyDataSetChanged();


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    public LinkedList<Lectures> getLectures() {
        sharedPreferenceUser = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        sharedPreferencesSubjectId = context.getSharedPreferences("subjectId", Context.MODE_PRIVATE);
        sectionId=sharedPreferenceUser.getString("SectionId","");
        subjectid=  sharedPreferencesSubjectId.getString("subject","");
        setLectures();
        return lectures;
    }




    public  String addLectures(Lectures lectures){

        String key = myRef.push().getKey();

        myRef.child("Lectures").child(key).setValue(lectures);
        return key;


    }

    public void setImageUri(String uri , String key){
        myRef.child("Lectures/"+key+"/imageUrl").setValue(uri);


    }




    public boolean remove(final String key){
        rm=false;
        myRef.child("Lectures").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
                Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesLectures/" +key+ ".png").delete();
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("Lectures").removeValue();
        Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesLectures/").delete();

    }
}


