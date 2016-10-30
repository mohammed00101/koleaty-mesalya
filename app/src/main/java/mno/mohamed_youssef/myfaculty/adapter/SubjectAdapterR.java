package mno.mohamed_youssef.myfaculty.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Location;
import mno.mohamed_youssef.myfaculty.model.Subject;

/**
 * Created by Mohamed Yossif on 13/10/2016.
 */
public class SubjectAdapterR extends RecyclerView.Adapter<SubjectAdapterR.ViewHolder> {

    private LinkedList<Subject> subject;
    private Context context;
    private LayoutInflater inflater;



    public SubjectAdapterR(Context context , LinkedList<Subject> subject){
        this.subject = subject;
        this.context    = context;
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public SubjectAdapterR.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_subject, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( SubjectAdapterR.ViewHolder holder, int position) {
        Subject sub =subject.get(position);
        holder.subjectName.setText(sub.getSubjectName());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return subject.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case

        private TextView subjectName;
        public  View view;
        public ViewHolder(View v ) {
            super(v);
            this.view=v;
            subjectName = (TextView) v.findViewById(R.id.textSubjectName) ;

        }
    }
}
