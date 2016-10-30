package mno.mohamed_youssef.myfaculty.model;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class SectionTable {

    public LinkedList<Section> sectionT;
    public List<String> year;
    public List<String> section;
    public List<String> division;
    public DatabaseReference myRef;
    public Section sectionR;
    private boolean rm;

    public SectionTable(DatabaseReference myRef) {

                  this.myRef = myRef;
                  year     =     new ArrayList<>();year.add("");
                  section  =     new ArrayList<>();section.add("");
                  division =     new ArrayList<>();division.add("");
                  sectionT =     new LinkedList<>();

               setSectiont();


        }

    public void setSectiont() {
        myRef.child("Section")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                          sectionT.clear();

                        Set<String> yearSet = new HashSet<String>(),
                                  sectionSet  = new HashSet<String>(),
                                  divisionSet     = new HashSet<String>();
                        for(DataSnapshot d : dataSnapshot.getChildren()) {
                            sectionR =  d.getValue(Section.class);
                            sectionR.setId(d.getKey());
                            sectionT.addFirst(sectionR);
                            yearSet.add(sectionR.getYear());
                            divisionSet.add(sectionR.getDivision());
                            sectionSet.add(sectionR.getSection());

                        }
                        year.clear();
                        year.addAll(yearSet);
                        section.clear();
                        section.addAll(sectionSet);
                        division.clear();
                        division.addAll(divisionSet);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });

    }

    public List<String> getYear() {return   year;}

    public List<String> getSection() {return  section;}

    public List<String> getDivision() {return  division ;}

    public List<Section> getSectionT() {
        return sectionT;
    }

    public String getId(final String year , final String section , final String division){

        for(Section sectiondata : sectionT) {

            if (sectiondata.getYear().equals(year) && sectiondata.getDivision().equals(division)
                    && sectiondata.getSection().equals(section))
                return sectiondata.getId();
        }

                     return  null;


    }



    public  void addSection(Section section){


        myRef.child("Section").push().setValue(section);




    }


    public boolean remove(final String key){
        try {
            rm = false;
            myRef.child("Section").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    rm = true;
                }
            });
        }catch (Exception ex){}
        return rm;
    }

    public void removeAll(){
        myRef.child("Section").removeValue();

    }

}



