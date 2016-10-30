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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.News;
import mno.mohamed_youssef.myfaculty.model.TeamInfo;

/**
 * Created by Mohamed Yossif on 21/09/2016.
 */
public class AddTeamInfo extends Dialog {


    private Activity activity;
    private Dialog dialog;
    private EditText title , content ;
    private ImageButton imageButton;
    private ImageView image;
    private Button postinfoTeam;
    private String imageName=null;
    private Bitmap imageO;
    private Integer imageIndex =0;
    private StorageReference storageRef;




    public AddTeamInfo(Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.add_infoteam);
        setTitle("اضافة معلومة عن الفريق");

        storageRef = Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesTeam");


        title = (EditText) findViewById(R.id.addinfoTeamTitle);
        content = (EditText) findViewById(R.id.addinfoTeamContent) ;
        image = (ImageView) findViewById(R.id.addinfoTeamimage);
        imageButton = (ImageButton) findViewById(R.id.addinfoTeamimageButton);
        postinfoTeam = (Button) findViewById(R.id.postinfoTeam);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();

            }
        });


        postinfoTeam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                TeamInfo teamInfo= new TeamInfo(null,title.getText().toString()
                        ,null,content.getText().toString());

                imageName=Database.teamInfoTable.addTeamInfo(teamInfo);
                uploadImage();
                dismiss();
            }
        });

    }


    private void selectImage() {
        final CharSequence[] items = { "الكاميرا", "أختار من مكتبة الصور", "ألغاء" };

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("الكاميرا")) {
                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                    activity.startActivityForResult(intent,2);
                } else if (items[item].equals("أختار من مكتبة الصور")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.setType("image/*");
                    activity.startActivityForResult(Intent.createChooser(intent, ""),2);
                } else if (items[item].equals("ألغاء")){
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void setImage(Bitmap image) {
        imageO =image;
        this.image.setImageBitmap(image);
    }


    @Override
    public void onPanelClosed(int featureId, Menu menu) {
        super.onPanelClosed(featureId, menu);
    }

    public void uploadImage() {


try {
    byte[] data = null;

    storageRef = Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesTeam/" + imageName + ".png");

    if (imageO != null) {

        int MAX_IMAGE_SIZE = 200 * 1024; // max final file size
        int compressQuality = 104; // quality decreasing by 5 every loop. (start from 99)
        int streamLength = MAX_IMAGE_SIZE;
        while (streamLength >= MAX_IMAGE_SIZE) {
            ByteArrayOutputStream bmpStream = new ByteArrayOutputStream();
            compressQuality -= 5;
            imageO.compress(Bitmap.CompressFormat.JPEG, compressQuality, bmpStream);
            data = bmpStream.toByteArray();
            streamLength = data.length;
        }

    }

    UploadTask uploadTask = storageRef.putBytes(data);
    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


            Database.teamInfoTable.setImageUri(taskSnapshot.getDownloadUrl().toString(),imageName);

        }
    });

}catch (Exception ex){};

    }
}
