package mno.mohamed_youssef.myfaculty.model;

import java.io.Serializable;

/**
 * Created by Mohamed Yossif on 14/10/2016.
 */
public class Manage implements Serializable {
    private String id;
    private String password;
    private String name;
    private String type;

    public  Manage(){

    }

    public Manage(String id,String password, String name,String type) {
        this.id = id ;
        this.password = name ;
        this.id=id;
        this.type = type;

    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}