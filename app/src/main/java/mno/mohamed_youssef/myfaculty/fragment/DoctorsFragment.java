package mno.mohamed_youssef.myfaculty.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;

import mno.mohamed_youssef.myfaculty.adapter.DoctorsAdapterR;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.utils.SwipeableRecyclerViewTouchListener;


public class DoctorsFragment extends Fragment {


    private RecyclerView doctorsList;
    public static DoctorsAdapterR doctorsAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferences sharedPreferenceUser;
    private boolean auth=false;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_doctors, container, false);
        doctorsList =(RecyclerView) view.findViewById(R.id.listDoctors);


        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        doctorsList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        doctorsList.setLayoutManager(mLayoutManager);

        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(doctorsList,
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
                            Database.instructorTable.remove(Database.instructorTable.getInstructor().get(position).getId());
                            Database.instructorTable.getInstructor().remove(position);
                            doctorsAdapter.notifyItemRemoved(position);
                        }
                        doctorsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Database.instructorTable.remove(Database.instructorTable.getInstructor().get(position).getId());
                            Database.instructorTable.getInstructor().remove(position);
                            doctorsAdapter.notifyItemRemoved(position);
                        }
                        doctorsAdapter.notifyDataSetChanged();
                    }
                });

        doctorsList.addOnItemTouchListener(swipeTouchListener);


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("أعضاء هيئة التدريس");
        doctorsAdapter = new DoctorsAdapterR(getActivity(),Database.instructorTable.getInstructor());
        doctorsList.setAdapter(doctorsAdapter);


    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.home, menu);
        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {

            auth=true;
            inflater.inflate(R.menu.docotor, menu);
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
