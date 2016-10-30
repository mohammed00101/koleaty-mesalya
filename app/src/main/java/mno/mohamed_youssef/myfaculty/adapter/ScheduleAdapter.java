package mno.mohamed_youssef.myfaculty.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.Date;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.fragment.LecturesFragment;
import mno.mohamed_youssef.myfaculty.fragment.NewsDetails;
import mno.mohamed_youssef.myfaculty.fragment.SectionsFragment;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Schedule;


/**
 * Created by Mohamed Yossif on 30/04/2016.
 */

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ViewHolder>  {

    private Context context;
    private ArrayList<Schedule> schedual;
    private static LayoutInflater inflater = null;
    private long currenttime;
    private SharedPreferences sharedPreference;
    private Date date;
    private Database database;
    private SharedPreferences sharedPreferencesSubjectId;



    public ScheduleAdapter(Context context, ArrayList<Schedule> schedule) {
        this.context = context;
        this.schedual = schedule;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sharedPreferencesSubjectId = context.getSharedPreferences("subjectId", Context.MODE_PRIVATE);


    }


    public Object getItem(int position) {return  schedual.get(position);
    }

    @Override
    public ScheduleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScheduleAdapter.ViewHolder holder, int position) {
        double startTime = schedual.get(position).getStarttime(),
                endTime = schedual.get(position).getEndTime();

        date = new Date();
        currenttime = (date.getHours() * 60) + (date.getMinutes());
        sharedPreference = context.getSharedPreferences("date", Context.MODE_PRIVATE);
        final Schedule sch =schedual.get(position);
        holder.textViewSubject.setText(Database.subjectTable.getSubject(sch.getSubjectId()));
        holder.textViewPlace.setText(Database.locationTable.getLocation(sch.getLocationId()));
        holder.textViewDoctor.setText(Database.instructorTable.getInstructor(sch.getInstructorId()));
        holder.textViewType.setText(sch.getType());
        holder.textViewBegin.setText(setTimeViewInFormet(startTime));
        holder.textViewEnd.setText(setTimeViewInFormet(endTime));
        setList_imgView(startTime, endTime ,holder);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferencesSubjectId.edit().putString("subject",sch.getSubjectId()).commit();
                if(sch.getType().equals("سكشن")){

                    ((Activity) context).getFragmentManager().beginTransaction().replace(R.id.fschedule,new SectionsFragment(),"Sections").addToBackStack("Sections").commit();

                }else {
                    ((Activity) context).getFragmentManager().beginTransaction().replace(R.id.fschedule,new LecturesFragment(),"Lectures").addToBackStack("Lectures").commit();

                }

        }
        });
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return schedual.size();
    }


    public long getTimeMin(double schedual_assistanttime) {


        int h = ((int) schedual_assistanttime);
        int m = (int) ((schedual_assistanttime - h) * 100);
        return (h * 60 + m);


    }

    public String timeFormate(long M) {

        int tH = (int) M / 60;
        long tM = M - (tH * 60);
        /*String timeInView = null;
        if (tH < 10 && tM < 10) timeInView = "0" + tH + ":0" + tM;
        else if (tH < 10 && tM > 10) timeInView = "0" + tH + ":" + tM;
        else if (tH > 10 && tM < 10) timeInView = tH + ":0" + tM;
        else timeInView = tH + ":" + tM;
*/

        return   new StringBuilder().append(pad(tH))
                .append(":").append(pad(tM)).toString();


    }


    public String setTimeViewInFormet(double time) {


        String timeForm;
        if (getTimeMin(time) > 12 * 60) {

            timeForm = timeFormate(getTimeMin(time)) + "PM";
        } else {
            timeForm = timeFormate(getTimeMin(time)) + "AM";
        }

        return timeForm;

    }


    public void setList_imgView(double startTime, double endTime,ScheduleAdapter.ViewHolder holder) {

        String days[] = context.getResources().getStringArray(R.array.days);
        boolean chosenDay = days[new Date().getDay()].equals(sharedPreference.getString("chosenDay", ""));

        //past Subjects


        if (getTimeMin(endTime) <= currenttime && chosenDay) {
            holder.list_img.setImageResource(R.drawable.radiobutto_next);

            holder.textViewTimer.setText("");

        }

        //now Subject

        else if (getTimeMin(startTime) <= currenttime && chosenDay) {

            holder.list_img.setImageResource(R.drawable.radiobutton_now);
            holder.textViewTimer.setText(timeFormate(getTimeMin(endTime) - currenttime));


        }

        //next  Subjects
        else {

            holder.list_img.setImageResource(R.drawable.radiobutton_past);
            holder.textViewTimer.setText("");

        }


    }


    private static String pad(long c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);
    }





    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView textViewSubject;
        private TextView textViewBegin;
        private TextView textViewPlace;
        private TextView textViewEnd;
        private ImageView list_img;
        private TextView textViewTimer;
        private TextView textViewType;
        private TextView textViewDoctor;
        public  View view;
        public ViewHolder(View vi ) {
            super(vi);
            this.view=vi;
            textViewSubject = (TextView) vi.findViewById(R.id.subject);
            textViewBegin = (TextView) vi.findViewById(R.id.begin);
            textViewEnd = (TextView) vi.findViewById(R.id.end);
            textViewPlace = (TextView) vi.findViewById(R.id.place);
            list_img = (ImageView) vi.findViewById(R.id.list_image);
            textViewTimer = (TextView) vi.findViewById(R.id.textViewtimer);
            textViewType = (TextView) vi.findViewById(R.id.textViewType);
            textViewDoctor = (TextView) vi.findViewById(R.id.textViewDoctor);

        }
    }
}