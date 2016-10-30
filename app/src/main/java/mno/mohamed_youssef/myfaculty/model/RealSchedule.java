package mno.mohamed_youssef.myfaculty.model;

/**
 * Created by Mohamed Yossif on 04/07/2016.
 */
public class RealSchedule {

    private String day;
    private String instructorName;
    private String subjectName;
    private String locationName;
    private double starttime;
    private double endTime;
    private String Type;

    public RealSchedule(String day, String instructorName, String subjectName, String locationName, double starttime, double endTime, String type) {
        this.day = day;
        this.instructorName = instructorName;
        this.subjectName = subjectName;
        this.locationName = locationName;
        this.starttime = starttime;
        this.endTime = endTime;
        Type = type;
    }

    public String getDay() {
        return day;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public String getLocationName() {
        return locationName;
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
