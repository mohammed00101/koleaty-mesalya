package mno.mohamed_youssef.myfaculty.model;

import java.io.Serializable;

/**
 * Created by Mohamed Yossif on 08/10/2016.
 */
public class Lectures implements Serializable {
    private String id;
    private String title;
    private String imageUrl;
    private String sectionId;
    private String subjectId;

    public  Lectures(){

    }

    public Lectures(String id,String title, String image,String sectionId,String subjectId) {
        this.title = title ;
        this.imageUrl = image;
        this.id=id;
        this.sectionId = sectionId;
        this.subjectId=subjectId;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return imageUrl;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.imageUrl = image;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getSubjectId() {
        return subjectId;
    }
}
