package mno.mohamed_youssef.myfaculty.model;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class Location {
    private String id;
    private String location;


    public  Location(){

    }

    public Location(String id, String location) {
        this.id = id;
        this.location = location;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }
}
