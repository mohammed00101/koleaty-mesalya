package mno.mohamed_youssef.myfaculty.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Database;

/**
 * Created by Mohamed Yossif on 15/10/2016.
 */
public class GetUrl extends DialogFragment {

    private Context context;
    private String child;

    public GetUrl(Context context , String child){
        this.context =context;
        this.child = child;
    }

    private String Url;
    private AutoCompleteTextView urlView;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // Get the layout inflater
        final LayoutInflater inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        builder.setView(inflater.inflate(R.layout.get_url, null))
                // Add action buttons
                .setPositiveButton("موفق",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {

                        urlView =(AutoCompleteTextView)((Dialog) dialog).findViewById(R.id.inputUrl);
                        Url =urlView.getText().toString();
                        try{
                            Uri.parse(Url);
                            Database.myRef.child(child).setValue(Url);

                        }catch (Exception e){
                            Toast.makeText(context,"لا يصلح",Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("ألغاء", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GetUrl.this.getDialog().cancel();
                    }
                });
        return builder.create();
    }

}
