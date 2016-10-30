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
import mno.mohamed_youssef.myfaculty.model.Location;
import mno.mohamed_youssef.myfaculty.model.Subject;

/**
 * Created by Mohamed Yossif on 23/09/2016.
 */
public class AddPlaces extends Dialog implements View.OnClickListener {


    private TextView place;
    private Button addPlaces;

    public AddPlaces(Context context) {
        super(context);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.addplaces);
        setTitle("أضافة مكان");


        place = (EditText) findViewById(R.id.editTextaddPlaces);

        addPlaces = (Button) findViewById(R.id.add_Places);


        addPlaces.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {


        if (place.equals("")) {
            Toast.makeText(getContext(), "أكمل أدخال البيانات", Toast.LENGTH_SHORT).show();

        } else {

            Database.locationTable.addLocation(new Location(null, place.getText().toString()));
            Toast.makeText(getContext(), "تم الحفظ", Toast.LENGTH_SHORT).show();

        }

    }
}
