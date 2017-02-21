package muga.thegreatuniversity.models;

import muga.thegreatuniversity.lists.CourseType;

/**
 * Created on 20/02/2017.
 * Auteurs : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */


public class Course {
    private String courseName;
    private CourseType courseType;
    private int coursePopularity;
    private int studentCount;
    private Boolean isLab;

    public Course(String courseName, CourseType courseType, int coursePopularity, int studentCount, Boolean isLab) {
        this.courseName = courseName;
        this.courseType = courseType;
        this.coursePopularity = coursePopularity;
        this.studentCount = studentCount;
        this.isLab = isLab;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public int getCoursePopularity() {
        return coursePopularity;
    }

    public void setCoursePopularity(int coursePopularity) {
        this.coursePopularity = coursePopularity;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public Boolean getLab() {
        return isLab;
    }

    public void setLab(Boolean lab) {
        isLab = lab;
    }
}
