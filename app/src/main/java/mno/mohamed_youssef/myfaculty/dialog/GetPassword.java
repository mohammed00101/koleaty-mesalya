package mno.mohamed_youssef.myfaculty.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.AutoCompleteTextView;

import mno.mohamed_youssef.myfaculty.R;

/**
 * Created by Mohamed Yossif on 15/10/2016.
 */
public class GetPassword extends DialogFragment {


    private String password;
    private AutoCompleteTextView passView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.get_password, null))
                // Add action buttons
                .setPositiveButton("دخول",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        passView =(AutoCompleteTextView)((Dialog) dialog).findViewById(R.id.inputpassword);
                        password =passView.getText().toString();
                    }
                })
                .setNegativeButton("الغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GetPassword.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

    public String getPassword() {
        return password;
    }
}
