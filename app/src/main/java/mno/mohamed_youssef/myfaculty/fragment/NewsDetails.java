package mno.mohamed_youssef.myfaculty.fragment;


import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.firebase.storage.StorageReference;

import mno.mohamed_youssef.myfaculty.Activity.AboutMeActivity;
import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.News;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetails extends Fragment {


    private StorageReference storageRef;
    private News news;
    private ImageView imageNews;
    private TextView  title;
    private TextView  content;
    private SharedPreferences sharedPreferenceUser;


    public NewsDetails(){
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_news_details, container, false);
        sharedPreferenceUser = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);

        setHasOptionsMenu(true);
        this.news=(News) getArguments().get("news");

        imageNews =(ImageView) view.findViewById(R.id.imageDNews);
        title = (TextView)view.findViewById(R.id.titleDNews);
        content = (TextView)view.findViewById(R.id.contentDNews);
        storageRef = Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/images");


        title.setText(news.getTitle()+ "   #  ");
        content.setText(news.getContent());
        if (news.getImage()!=null)
            Glide.with(getActivity()).load(Uri.parse(news.getImage())).diskCacheStrategy(DiskCacheStrategy.ALL).into(imageNews);


        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        getActivity().setTitle("تفاصيل الخبر");
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        menu.clear();
        inflater.inflate(R.menu.home, menu);
        if (sharedPreferenceUser.getString("admin","").equals(Database.adminPass)||
                sharedPreferenceUser.getString("admin","").equals(Database.editorPass)) {
            inflater.inflate(R.menu.newsd, menu);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
       if (id == R.id.me){
           startActivity(new Intent(getActivity(),AboutMeActivity.class));
        }
        return super.onOptionsItemSelected(item);

    }




}
