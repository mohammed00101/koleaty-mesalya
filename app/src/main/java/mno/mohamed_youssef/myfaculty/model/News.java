package mno.mohamed_youssef.myfaculty.model;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Mohamed Yossif on 11/07/2016.
 */
public class News implements Serializable {
    private String id;
    private String title;
    private String image;
    private String content;
     public  News(){

     }

    public News(String id,String title, String image, String content) {
        this.title = title ;
        this.image = image;
        this.content = content ;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
