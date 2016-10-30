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

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.adapter.SectionsAdapterR;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Sections;
import mno.mohamed_youssef.myfaculty.utils.SwipeableRecyclerViewTouchListener;

public class SectionsFragment  extends Fragment {


    private RecyclerView sectionsList;
    public static SectionsAdapterR sectionsAdapter;
    private LinkedList<Sections> Sections;
    private Handler handler;
    private SharedPreferences sharedPreferencesSubjectId;
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
        View view = inflater.inflate(R.layout.fragment_sections, container, false);
        sharedPreferencesSubjectId = getActivity().getSharedPreferences("subjectId", Context.MODE_PRIVATE);
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        sectionsList = (RecyclerView) view.findViewById(R.id.listViewSections);
        sectionsList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        sectionsList.setLayoutManager(mLayoutManager);

        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(sectionsList,
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
                            Database.sectionsTable.remove(Database.sectionsTable.getSections().get(position).getId());
                            Database.sectionsTable.getSections().remove(position);
                            sectionsAdapter.notifyItemRemoved(position);
                        }
                        sectionsAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Database.sectionsTable.remove(Database.sectionsTable.getSections().get(position).getId());
                            Database.sectionsTable.getSections().remove(position);
                            sectionsAdapter.notifyItemRemoved(position);
                        }
                        sectionsAdapter.notifyDataSetChanged();
                    }
                });

        sectionsList.addOnItemTouchListener(swipeTouchListener);
        return view;
    }



    @Override
    public void onResume() {
        super.onResume();


        getActivity().setTitle("السكشن");

         sharedPreferencesSubjectId.getString("subject","");
        sectionsAdapter = new SectionsAdapterR(getActivity(), Database.sectionsTable.getSections());
        sectionsList.setAdapter(sectionsAdapter);


    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.home, menu);
        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {
            auth=true;
            inflater.inflate(R.menu.sections, menu);
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
