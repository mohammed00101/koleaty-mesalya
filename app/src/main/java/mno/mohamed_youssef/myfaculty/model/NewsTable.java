package mno.mohamed_youssef.myfaculty.model;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.fragment.News_Fragment;

/**
 * Created by Mohamed Yossif on 11/07/2016.
 */
public class NewsTable {

    public DatabaseReference myRef;
    public LinkedList<News> news;
    public Context context;
    public News newsR;
    private File outputDir;
    private FirebaseStorage storage;
    boolean  rm;
    public NewsTable(DatabaseReference myRef, FirebaseStorage storage, Context context) {


        this.context=context;
        this.myRef = myRef;
        this.storage=storage;
        news = new LinkedList<>();
        outputDir = new File(this.context.getCacheDir().getAbsolutePath()+"/imagesNews/");
        outputDir.mkdir();
        setNews();


        }


        public void setNews()
        {
            myRef.child("News").orderByKey()
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            news.clear();
                            for (DataSnapshot d : dataSnapshot.getChildren()) {


                                newsR = d.getValue(News.class);
                                newsR.setId(d.getKey());
                                news.addFirst(newsR);
                                if (newsR.getImage() !=null)
                                    Glide.with(context).load(Uri.parse(newsR.getImage())).diskCacheStrategy(DiskCacheStrategy.ALL);
                            }


                            if (News_Fragment.newsAdapter != null)
                                News_Fragment.newsAdapter.notifyDataSetChanged();

                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
        }

        public LinkedList<News> getNews() {

            return news;
        }




    public  String addNews(News news){
        String key =myRef.child("News").push().getKey();
        myRef.child("News").child(key).setValue(news);
       return key;

    }


    public void setImageUri(String uri , String key){
        myRef.child("News/"+key+"/image").setValue(uri);


    }




    public boolean remove(final String key){
        rm=false;
        myRef.child("News").child(key).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                rm=true;
                Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesNews/" +key+ ".png").delete();
            }
        });
        return rm;
    }

    public void removeAll(){
        myRef.child("News").removeValue();
        Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/imagesNews/").delete();
    }
}


