package mno.mohamed_youssef.myfaculty.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Instructor;

/**
 * Created by Mohamed Yossif on 16/09/2016.
 */
public class DoctorsAdapter extends BaseAdapter  implements SpinnerAdapter {

    private LinkedList<Instructor> instructor;
    private Context context;
    private LayoutInflater inflater;


    public DoctorsAdapter(Context context, LinkedList<Instructor> instructor) {
        this.instructor = instructor;
        this.context = context;
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }




    public int getCount()
    {
        return instructor.size();
    }

    public Object getItem(int i)
    {
        return instructor.get(i);
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
        Instructor inst = instructor.get(position);
        txt.setText(inst.getInstNkname()+" / " + inst.getInstFName() +" " + inst.getInstLName());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

    public View getView(int i, View view, ViewGroup viewgroup) {
        TextView txt = new TextView(context);
        txt.setGravity(Gravity.CENTER);
        txt.setPadding(16, 16, 16, 16);
        txt.setTextSize(16);
        txt.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
        Instructor inst = instructor.get(i);
        txt.setText(inst.getInstNkname()+" / " + inst.getInstFName() +" " + inst.getInstLName());
        txt.setTextColor(Color.parseColor("#000000"));
        return  txt;
    }

}
