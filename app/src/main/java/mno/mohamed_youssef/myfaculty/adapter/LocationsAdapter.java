package mno.mohamed_youssef.myfaculty.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Instructor;
import mno.mohamed_youssef.myfaculty.model.Lectures;
import mno.mohamed_youssef.myfaculty.model.Location;


/**
 * Created by Mohamed Yossif on 19/09/2016.
 */
public class LocationsAdapter extends BaseAdapter  implements SpinnerAdapter {
    private LinkedList<Location> locations;
    private Context context;
    private LayoutInflater inflater;


    public LocationsAdapter(Context context , LinkedList<Location> locations){
        this.locations = locations;
        this.context    = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public int getCount()
    {
        return locations.size();
    }

    public Object getItem(int i)
    {
        return locations.get(i);
    }

    public long getItemId(int i)
    {
        return (long)i;
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView txt = new TextView(context);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(18);
        txt.setGravity(Gravity.CENTER_VERTICAL);
        Location loc = locations.get(position);
        txt.setText(loc.getLocation());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
        TextView txt = new TextView(context);
        txt.setGravity(Gravity.CENTER);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(16);
        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
        Location loc = locations.get(i);
        txt.setText(loc.getLocation());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

}
