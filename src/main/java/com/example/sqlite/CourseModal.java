package com.example.sqlite;

//Creating a model class for storing the data
public class CourseModal {

    private String courseName;
    private String courseDuration;
    private String courseTracks;
    private String courseDescription;
    private int id;

    //Getter: To retrieve the value of an object's attribute
    public String getCourseName() { return courseName; }

    public String getCourseDuration() { return courseDuration; }

    public String getCourseTracks() { return courseTracks; }

    public String getCourseDescription() { return courseDescription; }

    public int getId() { return id; }

    //Setter: To set the value of an object's attribute
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public void setCourseDuration(String courseDuration) { this.courseDuration = courseDuration; }

    public void setCourseTracks(String courseTracks) { this.courseTracks = courseTracks; }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public void setId(int id) { this.id = id; }

    //Constructor: To initialize the values at the time of object creation
    public CourseModal(String courseName, String courseDuration, String courseTracks, String courseDescription) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
        this.courseTracks = courseTracks;
        this.courseDescription = courseDescription;
    }

}//CourseModal
