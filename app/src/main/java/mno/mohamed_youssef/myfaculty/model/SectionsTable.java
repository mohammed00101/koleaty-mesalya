package mno.mohamed_youssef.myfaculty.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.LecturesFragment;

/**
 * Created by Mohamed Yossif on 08/10/2016.
 */
public class SectionsTable  {

    public DatabaseReference myRef;
    public LinkedList<Sections> sections;
    public Context context;
    private Sections sectionR;
    private boolean rm;
    private SharedPreferences sharedPreferenceUser;
    private SharedPreferences sharedPreferencesSubjectId;
    private String subjectid="";
    private String sectionId="";


    public SectionsTable(DatabaseReference myRef, Context context) {


        this.myRef = myRef;
        this.context = context;
        sections = new LinkedList<>();
        setSections();


    }


    public void setSections()
    {
        myRef.child("Sections").orderByKey()
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        sections.clear();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                            sectionR = d.getValue(Sections.class);
                            sectionR.setId(d.getKey());
                            if(!sectionId.equals("") && !subjectid.equals("")&&sectionR.getSectionId().equals(sectionId)&&sectionR.getSubjectId().equals(subjectid)){

                                sections.addFirst(sectionR);
                            }
                            else if(!sectionId.equals("")&&sectionR.getSectionId().equals(sectionId)){
                                sections.addFirst(sectionR);
                            }
                            else{
                                sections.addFirst(sectionR);
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

    public LinkedList<Sections> getSections() {
        sharedPreferenceUser = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        sharedPreferencesSubjectId = context.getSharedPreferences("subjectId", Context.MODE_PRIVATE);
        sectionId=sharedPreferenceUser.getString("SectionId","");
        subjectid=  sharedPreferencesSubjectId.getString("subject","");
        setSections();

        return sections;
    }




    public  String addSections(Sections sections){

        String key = myRef.push().getKey();

        myRef.child("Sections").child(key).setValue(sections);
        return key;


    }

    public void setImageUri(String uri , String key){
        myRef.child("Sections/"+key+"/imageUrl").setValue(uri);


    }


    public boolean remove(final String key){
        rm=false;
        myRef.child("Sections").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
                Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesSections/" +key+ ".png").delete();
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("Sections").removeValue();
        Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesSections/").delete();

    }
}
