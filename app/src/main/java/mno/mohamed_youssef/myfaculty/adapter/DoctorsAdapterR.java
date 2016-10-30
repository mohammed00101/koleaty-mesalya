package mno.mohamed_youssef.myfaculty.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Instructor;

/**
 * Created by Mohamed Yossif on 13/10/2016.
 */
public class DoctorsAdapterR extends RecyclerView.Adapter<DoctorsAdapterR.ViewHolder> {

    private LinkedList<Instructor> instructor;
    private static Context context;
    private LayoutInflater inflater;
 ;


    public DoctorsAdapterR(Context context, LinkedList<Instructor> instructor) {
        this.instructor = instructor;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public DoctorsAdapterR.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.doctors_item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DoctorsAdapterR.ViewHolder holder, int position) {
        Instructor inst =(Instructor) instructor.get(position);
       holder.InstructorName.setText(inst.getInstNkname()+" / " +inst.getInstFName()+" "+inst.getInstLName());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return instructor.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        private TextView InstructorName;
        public  View view;
        public ViewHolder(View v ) {
            super(v);
            this.view=v;
            InstructorName = (TextView) v.findViewById(R.id.textViewInstructorName);


        }
    }
}
