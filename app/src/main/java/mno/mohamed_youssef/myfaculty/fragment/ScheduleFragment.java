package mno.mohamed_youssef.myfaculty.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Date;


import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.adapter.ScheduleAdapter;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Schedule;
import mno.mohamed_youssef.myfaculty.utils.SwipeableRecyclerViewTouchListener;

/**
 * A placeholder fragment containing a simple view.
 */
public class ScheduleFragment extends Fragment {

    public static ScheduleAdapter taskListAdapter;
    private RecyclerView listView;
    private Handler handler;
    private SharedPreferences sharedPreferencedate;
    private SharedPreferences sharedPreferenceUser;
    public static ArrayList<Schedule> todayschedule;
    private  String days [];
    private SharedPreferences sharedPreferencesSubjectId;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean auth;


    // ImageView listBackground;

    public ScheduleFragment() {
    }


    //initialization

    public void init(View view) {
        days= getActivity().getResources().getStringArray(R.array.days);

        listView = (RecyclerView) view.findViewById(R.id.listView);
        sharedPreferencedate = getActivity().getSharedPreferences("date", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencedate.edit();
        editor.putString("chosenDay",days[new Date().getDay()]);
       // Log.d(">>>>>>>>>>>>>>",days[new Date().getDay()]);
        editor.commit();
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        sharedPreferencesSubjectId = getActivity().getSharedPreferences("subjectId", Context.MODE_PRIVATE);



        listView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        listView.setLayoutManager(mLayoutManager);

        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(listView,
                new SwipeableRecyclerViewTouchListener.SwipeListener() {

                    @Override
                    public boolean canSwipeLeft(int position) {
                        return auth;
                    }

                    @Override
                    public boolean canSwipeRight(int position) {
                        return auth;
                    }

                    @Override
                    public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Database.scheduleTable.remove(Database.scheduleTable.getScheduleT().get(position).getId());
                            Database.scheduleTable.getScheduleT().remove(position);
                            taskListAdapter.notifyItemRemoved(position);
                        }
                        taskListAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Database.scheduleTable.remove(Database.scheduleTable.getScheduleT().get(position).getId());
                            Database.scheduleTable.getScheduleT().remove(position);
                            taskListAdapter.notifyItemRemoved(position);
                        }
                        taskListAdapter.notifyDataSetChanged();
                    }
                });

        listView.addOnItemTouchListener(swipeTouchListener);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_scedule, container, false);

        init(view);

        return view;
    }



    @Override
    public void onResume() {
        super.onResume();


        // refreh list

        updateList();


        // Refresh title
        if (!days[new Date().getDay()].equals(sharedPreferencedate.getString("chosenDay", ""))) {


            getActivity().setTitle(sharedPreferencedate.getString("chosenDay", ""));
        }
        else{
            getActivity().setTitle("جدول اليوم ");
            if (taskListAdapter != null) {
                handler = new Handler();
                handler.postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        taskListAdapter.notifyDataSetChanged();
                        handler.postDelayed(this, 60 * 1000);
                    }
                }, 60 * 1000);


            }

        }

    }





    public  void updateList() {



        todayschedule=Database.scheduleTable.getScheduleT();

                taskListAdapter = new ScheduleAdapter(getActivity(), todayschedule);
                listView.setAdapter(taskListAdapter);

    }




    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

         menu.clear();
        inflater.inflate(R.menu.home, menu);
        inflater.inflate(R.menu.logout, menu);

        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {
            auth=true;
            inflater.inflate(R.menu.schedule_menu, menu);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


         if (id == R.id.logout) {

                SharedPreferences sharePrefernces = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharePrefernces.edit();
                editor.clear();
                editor.commit();
            getFragmentManager().beginTransaction().replace(R.id.Home, new StudentSignUp(),"Home").addToBackStack("Home").commit();

        }

        else   if (id == R.id.me){
           startActivity(new Intent(getActivity(),AboutMeActivity.class));
        }



        return true;
    }


}
