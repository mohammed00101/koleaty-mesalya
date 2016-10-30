package mno.mohamed_youssef.myfaculty.dialog;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.adapter.DoctorsAdapter;
import mno.mohamed_youssef.myfaculty.adapter.LocationsAdapter;
import mno.mohamed_youssef.myfaculty.adapter.MySpinnerAdapter;
import mno.mohamed_youssef.myfaculty.adapter.SubjectAdapter;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Instructor;
import mno.mohamed_youssef.myfaculty.model.Location;
import mno.mohamed_youssef.myfaculty.model.Schedule;
import mno.mohamed_youssef.myfaculty.model.Subject;

/**
 * Created by Mohamed Yossif on 18/09/2016.
 */
public class AddSchedule extends Dialog implements View.OnClickListener {


    private Spinner doctor,subject,places,type;
    private TextView textDoctor, textSubject,textType,textPlaces;
    private TextView from ,to;
    private Button addSchedule;
    private Database database;
    private LinkedList<Subject> arrayListSubject;
    private LinkedList<Instructor> arrayDoctor;
    private  LinkedList<Location> arrayListPlaces;
    private  List arrayType ;
    private SharedPreferences sharedPreferencedate;
    private SharedPreferences sharedPreferenceUser;
    private String instructorId="";
    private String subjectId="";
    private String typeS="";
    private String locationId="";
    private FragmentManager fragmentManager;
    private String timeFrom ,timeTo;





    public AddSchedule(Context context ,FragmentManager fragmentManager ) {
        super(context);
        this.fragmentManager = fragmentManager;

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.add_schedule);

        sharedPreferencedate = getContext().getSharedPreferences("date", Context.MODE_PRIVATE);
        sharedPreferenceUser = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);


        setTitle("أضافة الى الجدوال");

        doctor     =  (Spinner)findViewById(R.id.addScheduleDoctor);
        subject    = (Spinner) findViewById(R.id.addScheduleSubject);
        places     = (Spinner) findViewById(R.id.addScheduleplace);
        type       = (Spinner) findViewById(R.id.addScheduleType);
        addSchedule= (Button)  findViewById(R.id.add_schedule);
        from           = (TextView)  findViewById(R.id.time_from);
        to             = (TextView)  findViewById(R.id.time_to);


       from.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        from.setText(new StringBuilder().append(pad(hourOfDay))
                                .append(":").append(pad(minute)).toString());
                        from.setTextColor(Color.argb(255,0,0,255));

                        timeFrom = new StringBuilder().append(pad(hourOfDay))
                                .append(".").append(pad(minute)).toString();
                    }
                }, hour, minute, DateFormat.is24HourFormat(getContext())).show();
            }
        });

        to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);

                new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        to.setText(new StringBuilder().append(pad(hourOfDay))
                                .append(":").append(pad(minute)).toString());
                        to.setTextColor(Color.argb(255,0,0,255));

                        timeTo = new StringBuilder().append(pad(hourOfDay))
                                .append(".").append(pad(minute)).toString();
                    }
                }, hour, minute, DateFormat.is24HourFormat(getContext())).show();
            }
        });

        arrayListSubject = Database.subjectTable.getSubject();
        arrayDoctor      = Database.instructorTable.getInstructor();
        arrayListPlaces  = Database.locationTable.getLocation();
        arrayType        = Arrays.asList(getContext().getResources().getStringArray(R.array.type));



        data();
        addSchedule.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {


        if(subjectId.equals("") || locationId.equals("") ||instructorId.equals("")||
                typeS.equals("") || from.getText().equals("00:00") || to.getText().equals("00:00")){
            Toast.makeText(getContext(),"أكمل أدخال البيانات", Toast.LENGTH_SHORT).show();

        }
        else {

            try {
                Database.scheduleTable.addSchedule(
                        new Schedule(null,
                                sharedPreferencedate.getString("chosenDay", ""),
                                sharedPreferenceUser.getString("SectionId", null),
                                instructorId, subjectId, locationId,
                                Double.parseDouble(timeFrom), Double.parseDouble(timeTo),
                                typeS
                        ));
                Toast.makeText(getContext(), "تم الحفظ", Toast.LENGTH_SHORT).show();
            }catch (Exception e){

            }
        }

    }



    public void data(){


        subject.setAdapter(new SubjectAdapter(getContext(),arrayListSubject));
        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                subjectId=arrayListSubject.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        doctor.setAdapter(new DoctorsAdapter(getContext(),arrayDoctor));
        doctor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                instructorId = arrayDoctor.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



      places.setAdapter(new LocationsAdapter(getContext(),arrayListPlaces));
        places.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locationId = arrayListPlaces.get(position).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });


        type.setAdapter(new MySpinnerAdapter(getContext(),arrayType));

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeS =(String)arrayType.get(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



    }




    private static String pad(long c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }


}
