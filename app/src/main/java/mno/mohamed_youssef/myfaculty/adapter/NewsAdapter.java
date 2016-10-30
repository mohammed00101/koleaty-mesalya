package mno.mohamed_youssef.myfaculty.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import mno.mohamed_youssef.myfaculty.R;
import mno.mohamed_youssef.myfaculty.fragment.NewsDetails;
import mno.mohamed_youssef.myfaculty.model.Database;
import mno.mohamed_youssef.myfaculty.model.News;

/**
 * Created by Mohamed Yossif on 11/07/2016.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    private  static Context context;
    private  LinkedList<News> news;
    private  LayoutInflater inflater;
    //private StorageReference storageRef;

   // private File outputDir;




    public NewsAdapter(Context context , LinkedList<News> news){

        this.context = context;
        this.news =news ;

        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

         //storageRef = Database.storage.getReferenceFromUrl("gs://firebase-myfaculty.appspot.com/images");
        //Collections.reverse(this.news);


        // outputDir = new File(this.context.getCacheDir().getAbsolutePath()+"/imagesNews/");


    }




    public Object getItem(int position) {return news.get(position);}

    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_news, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        if (news.get(position).getImage() !=null)
            Glide.with(context).load(Uri.parse(news.get(position).getImage())).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.newsImage);
        // newsImage.setImageBitmap(mBitmap);
       holder.newsTitle.setText(news.get(position).getTitle()+" # ");
       holder.newContent.setText(news.get(position).getContent());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putSerializable("news",news.get(position));
                NewsDetails newsDetails =new NewsDetails();
                newsDetails.setArguments(bundle);
                ((Activity) context).getFragmentManager().beginTransaction().replace(R.id.news,newsDetails,"NewsD").addToBackStack("NewsD").commit();
            }
        });
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return news.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public  TextView newsTitle;
        public  TextView newContent;
        public  ImageView newsImage;
        public  View view;
        public ViewHolder(View v ) {
            super(v);
            this.view=v;
            this.newsTitle = (TextView)  v.findViewById(R.id.textNews);
            this.newsImage = (ImageView) v.findViewById(R.id.imageNews);
            this.newContent =(TextView)  v.findViewById(R.id.contentNews) ;

        }
    }


}
