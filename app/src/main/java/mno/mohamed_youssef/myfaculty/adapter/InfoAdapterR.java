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
import mno.mohamed_youssef.myfaculty.model.Info;
import mno.mohamed_youssef.myfaculty.model.Instructor;

/**
 * Created by Mohamed Yossif on 13/10/2016.
 */
public class InfoAdapterR  extends RecyclerView.Adapter<InfoAdapterR.ViewHolder> {

    private Context context;
    private LinkedList<Info> info;
    private LayoutInflater inflater;
    private StorageReference storageRef;




    public InfoAdapterR(Context context , LinkedList<Info> info){

        this.context = context;
        this.info =info ;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //storageRef = Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/images");

    }

    @Override
    public InfoAdapterR.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_info, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InfoAdapterR.ViewHolder holder, int position) {
        Info inf =info.get(position);
        holder.infoTitle.setText(inf.getTitle()+" # ");
        holder.infoContent.setText(inf.getContent());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return info.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private  TextView infoTitle;
        private TextView infoContent;
        public  View view;
        public ViewHolder(View v ) {
            super(v);
            this.view=v;
            infoTitle = (TextView) v.findViewById(R.id.textInfo);
            infoContent =(TextView) v.findViewById(R.id.contentInfo) ;


        }
    }
}
