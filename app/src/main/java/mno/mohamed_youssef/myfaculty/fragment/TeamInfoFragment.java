package mno.mohamed_youssef.myfaculty.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.adapter.InfoTeamAdapterR;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.TeamInfo;
import mno.mohamed_youssef.myfaculty.utils.SwipeableRecyclerViewTouchListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamInfoFragment extends android.app.Fragment {


    private RecyclerView infoTeamList;
    public static InfoTeamAdapterR infoTeamAdapter;
    private ImageView imageViewTeam;
    private int index=0;
    private LinkedList<TeamInfo> teamInfo;
    private Handler handler;
    private RecyclerView.LayoutManager mLayoutManager;
    private SharedPreferences sharedPreferenceUser;
    private  boolean auth=false;



    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_team_info, container, false);
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        infoTeamList = (RecyclerView) view.findViewById(R.id.listViewInfoTeam);
        imageViewTeam =(ImageView) view.findViewById(R.id.ImageViewTeam);

        infoTeamList.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        infoTeamList.setLayoutManager(mLayoutManager);

        SwipeableRecyclerViewTouchListener swipeTouchListener = new SwipeableRecyclerViewTouchListener(infoTeamList,
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
                            Database.teamInfoTable.remove(Database.teamInfoTable.getTeamInfo().get(position).getId());
                            Database.teamInfoTable.getTeamInfo().remove(position);
                            infoTeamAdapter.notifyItemRemoved(position);
                        }
                        infoTeamAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                        for (int position : reverseSortedPositions) {
                            Database.teamInfoTable.remove(Database.teamInfoTable.getTeamInfo().get(position).getId());
                            Database.teamInfoTable.getTeamInfo().remove(position);
                            infoTeamAdapter.notifyItemRemoved(position);
                        }
                        infoTeamAdapter.notifyDataSetChanged();
                    }
                });

        infoTeamList.addOnItemTouchListener(swipeTouchListener);


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        imageViewTeam.setImageResource(R.drawable.logo);



    }

    @Override
    public void onResume() {
        super.onResume();


        getActivity().setTitle("عن الفريق");
        teamInfo = Database.teamInfoTable.getTeamInfo();

        infoTeamAdapter = new InfoTeamAdapterR(getActivity(),teamInfo);
        infoTeamList.setAdapter(infoTeamAdapter);



        handler = new Handler();
        handler.postDelayed(new Runnable() {

            @Override
            public void run() {



                    try {


                        if (index >= teamInfo.size() || teamInfo.size() == 0) {
                            index = 0;
                        imageViewTeam.setImageResource(R.drawable.ic_team);


                        } else if (teamInfo.get(index).getImage() !=null) {
                            Glide.with(getActivity()).load(Uri.parse(teamInfo.get(index).getImage())).fitCenter().diskCacheStrategy(DiskCacheStrategy.ALL).into(imageViewTeam);
                            index++;
                        }
                        else {

                            imageViewTeam.setImageResource(R.drawable.logo);

                            index++;

                        }

                    }catch (Exception e){
                        Log.d(">>>>>>>",""+e.getMessage());
                    }
                    handler.postDelayed(this, 3000);

                }

        },3000);




    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        menu.clear();
        inflater.inflate(R.menu.home, menu);
        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {
            auth=true;
            inflater.inflate(R.menu.teaminfo, menu);
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
