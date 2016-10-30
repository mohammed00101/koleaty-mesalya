package mno.mohamed_youssef.myfaculty.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.adapter.MySpinnerAdapter;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Instructor;
import mno.mohamed_youssef.myfaculty.model.Section;

/**
 * Created by Mohamed Yossif on 16/09/2016.
 */
public class AddDoctor extends Dialog implements View.OnClickListener {


    private TextView degree,firstName,lastName;
    private Button addDoctor;
    public AddDoctor(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.add_doctor);
        setTitle("أضافة عضو هيئة تدريس");


        degree = (EditText) findViewById(R.id.editTextadddegree);
        firstName = (EditText) findViewById(R.id.editTextaddFirstName);
        lastName = (EditText) findViewById(R.id.editTextaddlastName);
        addDoctor = (Button) findViewById(R.id.add_Doctor);


        addDoctor.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if (degree.equals("")|| firstName.equals("") || lastName.equals("")) {
            Toast.makeText(getContext(), "أكمل أدخال البيانات", Toast.LENGTH_SHORT).show();

        } else {

            Database.instructorTable.addDoctor(new Instructor(null,degree.getText().toString(),firstName.getText().toString()
                                                   ,lastName.getText().toString()));
            Toast.makeText(getContext(), "تم الحفظ", Toast.LENGTH_SHORT).show();

        }

    }

}