package mno.mohamed_youssef.myfaculty.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
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
 * Created by Mohamed Yossif on 14/10/2016.
 */
public class DeleteSection extends Dialog implements View.OnClickListener {


    private Spinner year, division,section;

    private TextView textYear, textDivision;


    private Button deleteSection;
    private DatabaseReference myRef;
    private Database database;
    List<String> arrayListYear;
    List<String> arrayListSection;
    List<String> arrayListDivison;
    private Context context;

    public DeleteSection(Context context) {
        super(context);
        this.context = context;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.delete_section);
        setTitle("حذف قسم شعبة فرقة");


        deleteSection = (Button)  findViewById(R.id.deleteSection);



        myRef= FirebaseDatabase.getInstance().getReference();

        deleteSection.setOnClickListener(this);
        data();

    }

    @Override
    public void onClick(View v) {


        if(division.getSelectedItem() == null || year.getSelectedItem() == null || section.getSelectedItem() ==null){
            Toast.makeText(getContext(),"أكمل أدخال البيانات", Toast.LENGTH_SHORT).show();

        }
        else {

            Database.sectionTable.remove(Database.sectionTable.getId(year.getSelectedItem().toString(),
                    section.getSelectedItem().toString(),division.getSelectedItem().toString()));
            Toast.makeText(getContext(),"تم الحذف", Toast.LENGTH_SHORT).show();

        }

    }



    public void data(){

        arrayListYear = Database.sectionTable.getYear();
        arrayListSection = Database.sectionTable.getSection();
        arrayListDivison = Database.sectionTable.getDivision();

        year = (Spinner) findViewById(R.id.deletespinner_year);
        division = (Spinner)findViewById(R.id.deletespinner_group);
        section = (Spinner)findViewById(R.id.deleteSection_spinner);

        //database.sectionTable.getYear()
        MySpinnerAdapter adapter = new MySpinnerAdapter(context, arrayListYear);
        year.setAdapter(adapter);


        MySpinnerAdapter adapter2 = new MySpinnerAdapter(context, arrayListDivison);
        division.setAdapter(adapter2);


        MySpinnerAdapter adapter3 = new MySpinnerAdapter(context, arrayListSection);
        section.setAdapter(adapter3);



    }
}
