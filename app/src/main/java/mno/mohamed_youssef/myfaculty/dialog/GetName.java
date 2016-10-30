package mno.mohamed_youssef.myfaculty.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.AutoCompleteTextView;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Database;

/**
 * Created by Mohamed Yossif on 15/10/2016.
 */
public class GetName extends DialogFragment {

    private Context context;
    private SharedPreferences sharedPreferenceUser;


    public GetName(Context context){
        this.context =context;
    }

    private String name;
    private AutoCompleteTextView nameView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.getname, null))
                // Add action buttons
                .setPositiveButton("موفق",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        nameView =(AutoCompleteTextView)((Dialog) dialog).findViewById(R.id.inputName);
                        name =nameView.getText().toString();
                            Database.myRef.child("user").push().setValue(name);
                        sharedPreferenceUser.edit().putString("userName",name).commit();


                    }
                })
                .setNegativeButton("ألغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GetName.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }

}
