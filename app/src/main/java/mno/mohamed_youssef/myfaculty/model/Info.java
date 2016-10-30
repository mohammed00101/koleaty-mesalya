package mno.mohamed_youssef.myfaculty.model;

import java.io.Serializable;

/**
 * Created by Mohamed Yossif on 21/09/2016.
 */
public class Info implements Serializable {
    private String id;
    private String title;
    private String content;

    public  Info(){

    }

    public Info(String id,String title, String content) {
        this.title = title ;
        this.content = content ;
        this.id=id;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setId(String id) {
        this.id = id;
    }
}