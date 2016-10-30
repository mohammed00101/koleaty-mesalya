package mno.mohamed_youssef.myfaculty.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import mno.mohamed_youssef.myfaculty.R;

public class AboutMeActivity extends AppCompatActivity {

    private ImageButton facebook;
    private ImageButton linkedin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_me);
        facebook =(ImageButton)findViewById(R.id.imageButtonFace);
        linkedin =(ImageButton)findViewById(R.id.imageButtonLinked);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/profile.php?id=100007489060985")));

            }
        });


        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.linkedin.com/in/mohamed-el-arand-33a083112?trk=nav_responsive_tab_profile_pic")));

            }
        });

    }
}
