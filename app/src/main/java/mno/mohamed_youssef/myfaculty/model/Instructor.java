package mno.mohamed_youssef.myfaculty.model;

import java.util.Objects;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class Instructor extends Object{
    private String id;
    private String instNkname;
    private String instFname;
    private String instLname;

    public  Instructor(){

    }
    public Instructor(String id, String instNkname, String instFName, String instLName) {
        this.id = id;
        this.instNkname = instNkname;
        this.instFname = instFName;
        this.instLname = instLName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setInstNkname(String instNkname) {
        this.instNkname = instNkname;
    }

    public void setInstFName(String instFName) {
        this.instFname = instFName;
    }

    public void setInstLName(String instLName) {
        this.instLname = instLName;
    }

    public String getId() {
        return id;
    }

    public String getInstNkname() {
        return instNkname;
    }

    public String getInstFName() {
        return instFname;
    }

    public String getInstLName() {
        return instLname;
    }
}
