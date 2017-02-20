package muga.thegreatuniversity.models;

/**
 * Created by youri on 20/02/2017.
 */

enum courseType {
    MAG("@string/courseType_magistral"),
    LAB_SC("@string/scienceLab"),
    LAB_AG("@string/agronomyLab"),
    LAB_IT("@string/ITLab");

    private String name = "";

    courseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class Course {
    private String courseName;
    private String courseType;
    private int coursePopularity;
    private int studentCount;
    private Boolean isLab;

    public Course(String courseName, String courseType, int coursePopularity, int studentCount, Boolean isLab) {
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

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
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
