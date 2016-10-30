package mno.mohamed_youssef.myfaculty.model;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class Subject {

    private String id;
    private String subjectname ;

    public  Subject(){

    }
    public Subject(String id, String subjectName) {
        this.id = id;
        this.subjectname = subjectName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectname = subjectName;
    }

    public String getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectname;
    }
}
