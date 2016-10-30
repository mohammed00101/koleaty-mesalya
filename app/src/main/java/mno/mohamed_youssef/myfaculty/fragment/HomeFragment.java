package mno.mohamed_youssef.myfaculty.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.dialog.GetName;


public class HomeFragment extends Fragment {


    private SharedPreferences sharedPreferenceUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        if(!sharedPreferenceUser.getString("userName","").equals("")){

            GetName getName =  new GetName(getActivity());
            getName.show(getFragmentManager(),"userName");
        }

        getActivity().setTitle("كليتى مثالية");
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.home, menu);
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
