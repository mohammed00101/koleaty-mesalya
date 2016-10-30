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
import mno.mohamed_youssef.myfaculty.model.Section;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddSection extends Dialog implements View.OnClickListener {


    private Spinner year, division;

    private TextView textYear, textDivision;
    private EditText textSection ;

    private Button addSection;
    private DatabaseReference myRef;
    private Database database;
    List<String> arrayListYear;
    List<String> arrayDivision;

    public AddSection(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.add_section);
        setTitle("أضافة فرقة او شعبه او قسم");


        textSection     = (EditText)  findViewById(R.id.editTextaddSection);
        addSection       = (Button)  findViewById(R.id.addSection);

        arrayListYear = Arrays.asList(getContext().getResources().getStringArray(R.array.year));
        arrayDivision = Arrays.asList(getContext().getResources().getStringArray(R.array.division));


        myRef= FirebaseDatabase.getInstance().getReference();

        addSection.setOnClickListener(this);
        data();

    }

    @Override
    public void onClick(View v) {


        if(division.getSelectedItem() == null || year.getSelectedItem() == null || textSection.equals("")){
            Toast.makeText(getContext(),"أكمل أدخال البيانات", Toast.LENGTH_SHORT).show();

        }
        else {

            Database.sectionTable.addSection(new Section(null,year.getSelectedItem().toString()
                    ,textSection.getText().toString(),division.getSelectedItem().toString()));
            Toast.makeText(getContext(),"تم الحفظ", Toast.LENGTH_SHORT).show();

        }

    }



    public void data(){

            year = (Spinner) findViewById(R.id.addspinner_year);
            //database.sectionTable.getYear()
            MySpinnerAdapter adapter = new MySpinnerAdapter(getContext(), arrayListYear);
            year.setAdapter(adapter);

            division = (Spinner)findViewById(R.id.addspinner_group);
            MySpinnerAdapter adapter2 = new MySpinnerAdapter(getContext(), arrayDivision);
            division.setAdapter(adapter2);

    }
}
