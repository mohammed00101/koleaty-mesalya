package mno.mohamed_youssef.myfaculty.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.adapter.MySpinnerAdapter;
import mno.mohamed_youssef.myfaculty.model.Database;

//import info.androidhive.materialdesign.mno.mohamed_youssef.myfaculty.adapter.MySpinnerAdapter;

public class StudentSignUp extends Fragment {

    Spinner year, setion , division;
    TextView textYear,textSection , textDivision;
    String UsectionId,sectionId;
    String Uyear, Usection, Udivision;
    List<String> arrayListYear;
    List<String> arrayListSection;
    List<String> arrayListDivison;
    private SharedPreferences sharedPreferenceUser;


    public static final String DEFAULT = "default";

    private Button cancel;
    private Button ButtonGo;


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_sign_up, container, false);
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        cancel = (Button)view.findViewById(R.id.cancelStud);
        ButtonGo = (Button)view.findViewById(R.id.creatStudent);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        sectionId  = sharedPreferences.getString("SectionId","");

       
            arrayListYear = Database.sectionTable.getYear();
            arrayListSection = Database.sectionTable.getSection();
            arrayListDivison = Database.sectionTable.getDivision();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFragmentManager().beginTransaction().replace(R.id.Home, new HomeFragment(),"Home").addToBackStack("Home").commit();
            }
        });



        ButtonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                go(v);
            }
        });
        confirm(view);

        return  view;

    }


public void confirm(View view){

    if (sectionId.equals("")) {
            year = (Spinner) view.findViewById(R.id.spinner_year);
            //database.sectionTable.getYear()
            MySpinnerAdapter adapter = new MySpinnerAdapter(getActivity(), arrayListYear);
            year.setAdapter(adapter);



            division = (Spinner) view.findViewById(R.id.spinner_group);
            MySpinnerAdapter adapter2 = new MySpinnerAdapter(getActivity(), arrayListDivison);
            division.setAdapter(adapter2);




            setion = (Spinner) view.findViewById(R.id.spinner_Section);
            MySpinnerAdapter adapter3 = new MySpinnerAdapter(getActivity(), arrayListSection);
            setion.setAdapter(adapter3);


        } else {


            getFragmentManager().beginTransaction().replace(R.id.Home, new ScheduleFragment(),"Schedule").addToBackStack("Schedule").commit();

       }


    }

    @Override
    public void onResume() {
        super.onResume();

        getActivity().setTitle("الجدوال");

    }

    public void go(View view) {

        if(division.getSelectedItem() == null || year.getSelectedItem() == null || setion.getSelectedItem() == null){
            Toast.makeText(getActivity(),"أكمل أدخال البيانات", Toast.LENGTH_SHORT).show();
            return;
        }

        Udivision = division.getSelectedItem().toString();
        Uyear = year.getSelectedItem().toString();
        Usection = setion.getSelectedItem().toString();
        UsectionId = Database.sectionTable.getId(Uyear,Usection,Udivision);



            Toast.makeText(getActivity(), Udivision +"/"+ Usection +"/"+ Uyear + "", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user",getActivity().MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("Divison", Udivision);
            editor.putString("Section", Usection);
            editor.putString("Year", Uyear);
            editor.putString("SectionId",UsectionId);


        Log.d(">>>>>>>>>>>>>>>>>>>>>>",""+UsectionId);

            editor.commit();

            /*Intent intent = new Intent(this, ScheduleActivity.class);
            startActivity(intent);
            finish();*/
        getFragmentManager().beginTransaction().replace(R.id.Home, new ScheduleFragment(),"Schedule").addToBackStack("Schedule").commit();

    }



    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.home, menu);
        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {
            inflater.inflate(R.menu.menu_add_section, menu);
        }

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

  if (id == R.id.me){
           startActivity(new Intent(getActivity(),AboutMeActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }
}
