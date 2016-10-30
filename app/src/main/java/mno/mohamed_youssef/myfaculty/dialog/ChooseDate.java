package mno.mohamed_youssef.myfaculty.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.fragment.ScheduleFragment;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Schedule;


/**
 * A placeholder fragment containing a simple view.
 */
public class ChooseDate extends  Dialog{

    private SharedPreferences sharedPreferencedate;
    private ListView chooseday;
    private SharedPreferences sharedPreferenceUser;
    private ArrayList<Schedule> todayschedule;
    private  String days [];
    private  Context context;



    public ChooseDate(Context context) {
        super(context);

        this.context =context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chooseday);
        setTitle("اختار اليوم                                              ");
        days= getContext().getResources().getStringArray(R.array.days);
        chooseday = (ListView) findViewById(R.id.listViewday);

        chooseday.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                String days [] = getContext().getResources().getStringArray(R.array.days);
                sharedPreferenceUser = getContext().getSharedPreferences("user", Context.MODE_PRIVATE);
                sharedPreferencedate = getContext().getSharedPreferences("date", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferencedate.edit();
                editor.putString("chosenDay",days[position]);
                editor.commit();

                ScheduleFragment.todayschedule= Database.scheduleTable.getScheduleT();
                ScheduleFragment.taskListAdapter.notifyDataSetChanged();

                if (!days[new Date().getDay()].equals(sharedPreferencedate.getString("chosenDay", ""))) {

                    ((Activity)context).setTitle(sharedPreferencedate.getString("chosenDay", ""));
                }
                else {
                    ((Activity)context).setTitle("جدول اليوم");
                }
                dismiss();


            }
        });

    }


}
