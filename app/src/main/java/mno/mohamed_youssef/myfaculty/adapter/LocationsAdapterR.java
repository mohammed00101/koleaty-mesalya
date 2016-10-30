package mno.mohamed_youssef.myfaculty.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Lectures;
import mno.mohamed_youssef.myfaculty.model.Location;

/**
 * Created by Mohamed Yossif on 13/10/2016.
 */
public class LocationsAdapterR extends RecyclerView.Adapter<LocationsAdapterR.ViewHolder> {

    private LinkedList<Location> locations;
    private Context context;
    private LayoutInflater inflater;


    public LocationsAdapterR(Context context , LinkedList<Location> locations){
        this.locations = locations;
        this.context    = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }



    @Override
    public LocationsAdapterR.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_locations, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( LocationsAdapterR.ViewHolder holder, int position) {
        Location lect =locations.get(position);
        holder.locationName.setText(lect.getLocation());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private TextView locationName;
        public  View view;
        public ViewHolder(View v ) {
            super(v);
            this.view=v;
            locationName = (TextView) v.findViewById(R.id.textLocationsName) ;

        }
    }
}
