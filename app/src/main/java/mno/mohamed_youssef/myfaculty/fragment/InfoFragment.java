package mno.mohamed_youssef.myfaculty.fragment;

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
import mno.mohamed_youssef.myfaculty.adapter.InfoAdapterR;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.utils.SwipeableRecyclerViewTouchListener;

public class InfoFragment extends android.app.Fragment {


    private RecyclerView infolist;
    public static InfoAdapterR infoAdapter;
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
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        infolist = (RecyclerView) view.findViewById(R.id.listViewInfo);
        infolist.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        infolist.setLayoutManager(mLayoutManager);

        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(infolist,
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
                            Database.infoTable.remove(Database.infoTable.getInfo().get(position).getId());
                            Database.infoTable.getInfo().remove(position);
                            infoAdapter.notifyItemRemoved(position);
                        }
                        infoAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Database.infoTable.remove(Database.infoTable.getInfo().get(position).getId());
                            Database.infoTable.getInfo().remove(position);
                            infoAdapter.notifyItemRemoved(position);
                        }
                        infoAdapter.notifyDataSetChanged();
                    }
                });

        infolist.addOnItemTouchListener(swipeTouchListener);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();




    }

    @Override
    public void onResume() {
        super.onResume();



        getActivity().setTitle("معلومات يجب ان تعرفها");
        infoAdapter = new InfoAdapterR(getActivity(), Database.infoTable.getInfo());
        infolist.setAdapter(infoAdapter);

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.home, menu);
        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {
            auth=true;
            inflater.inflate(R.menu.info, menu);

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
