package mno.mohamed_youssef.myfaculty.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.storage.StorageReference;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Lectures;
import mno.mohamed_youssef.myfaculty.model.Sections;

/**
 * Created by Mohamed Yossif on 13/10/2016.
 */
public class SectionsAdapterR  extends RecyclerView.Adapter<SectionsAdapterR.ViewHolder> {


    private Context context;
    private LinkedList<Sections> sections;
    private LayoutInflater inflater;
    private StorageReference storageRef;
    private ViewHolder viewHolder;




    public SectionsAdapterR(Context context , LinkedList<Sections> sections){

        this.context = context;
        this.sections =sections ;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //storageRef = Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/images");

    }

    @Override
    public SectionsAdapterR.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_sections, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SectionsAdapterR.ViewHolder holder, int position) {
        final Sections sec =sections.get(position);
        holder.title.setText(sec.getTitle());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) context).startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(sec.getImageUrl())));
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return sections.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private TextView title;
        public  View view;
        public ViewHolder(View v ) {
            super(v);
            this.view=v;
            title = (TextView) v.findViewById(R.id.textSections);

        }
    }
}
