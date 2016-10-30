package mno.mohamed_youssef.myfaculty.model;

import java.io.Serializable;

/**
 * Created by Mohamed Yossif on 28/04/2016.
 */
public class Schedule extends Object {

    private String id;
    private String day;
    private String sectionId;
    private String instructorId;
    private String subjectId;
    private String locationId;
    private double starttime;
    private double endTime;
    private String Type;

    public  Schedule(){

    }
    public Schedule(String id, String day, String sectionId, String instructorId, String subjectId,String locationId, double starttime, double endTime, String type) {
        this.id = id;
        this.day = day;
        this.sectionId = sectionId;
        this.instructorId = instructorId;
        this.subjectId = subjectId;
        this.locationId = locationId;
        this.starttime = starttime;
        this.endTime = endTime;
        Type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public void setStarttime(double starttime) {
        this.starttime = starttime;
    }

    public void setEndTime(double endTime) {
        this.endTime = endTime;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getSectionId() {
        return sectionId;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getLocationId() {
        return locationId;
    }

    public double getStarttime() {
        return starttime;
    }

    public double getEndTime() {
        return endTime;
    }

    public String getType() {
        return Type;
    }
}
