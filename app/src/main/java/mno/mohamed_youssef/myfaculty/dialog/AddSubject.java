package mno.mohamed_youssef.myfaculty.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Instructor;
import mno.mohamed_youssef.myfaculty.model.Subject;

/**
 * Created by Mohamed Yossif on 17/09/2016.
 */
public class AddSubject extends Dialog implements View.OnClickListener {


    private TextView subject;
    private Button addSubject;
    public AddSubject(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.add_subject);
        setTitle("أضافة ماده");


        subject = (EditText) findViewById(R.id.editTextaddSubject);

        addSubject = (Button) findViewById(R.id.add_subject);


        addSubject.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if (subject.equals("")) {
            Toast.makeText(getContext(), "أكمل أدخال البيانات", Toast.LENGTH_SHORT).show();

        } else {

            Database.subjectTable.addSubject(new Subject(null,subject.getText().toString()));
            Toast.makeText(getContext(), "تم الحفظ", Toast.LENGTH_SHORT).show();

        }

    }

}