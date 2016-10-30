package mno.mohamed_youssef.myfaculty.dialog;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.Info;
import mno.mohamed_youssef.myfaculty.model.News;

/**
 * Created by Mohamed Yossif on 21/09/2016.
 */
public class AddInfo extends Dialog {


    private Activity activity;
    private Dialog dialog;
    private EditText title , content ;
    private Button posInfo;
    private StorageReference storageRef;



    public AddInfo(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_info);
        setTitle("أضافة معلومة جديده");


        title = (EditText) findViewById(R.id.addInfoTitle);
        content = (EditText) findViewById(R.id.addContentInfo) ;
        posInfo = (Button) findViewById(R.id.postInfo);



        posInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Info info= new Info(null,title.getText().toString()
                        ,content.getText().toString());
                Database.infoTable.addInfo(info);

                dismiss();
            }
        });

    }
}
