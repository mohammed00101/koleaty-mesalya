package mno.mohamed_youssef.myfaculty.model;

import java.io.Serializable;

/**
 * Created by Mohamed Yossif on 21/09/2016.
 */
public class TeamInfo implements  Serializable {
    private String id;
    private String title;
    private String image;
    private String content;

    public  TeamInfo(){

    }

    public TeamInfo(String id,String title, String image, String content) {
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

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
