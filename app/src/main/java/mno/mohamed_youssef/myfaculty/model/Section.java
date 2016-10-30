package mno.mohamed_youssef.myfaculty.model;

/**
 * Created by Mohamed Yossif on 26/06/2016.
 */
public class Section extends Object{

    private String division;
    private String id ;
    private String section;
    private String year;

    public  Section(){

    }
    public Section(String id, String year, String section, String division) {
        this.id = id;
        this.year = year;
        this.section = section;
        this.division = division;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getId() {
        return id;
    }

    public String getYear() {
        return year;
    }

    public String getSection() {
        return section;
    }

    public String getDivision() {
        return division;
    }
}
