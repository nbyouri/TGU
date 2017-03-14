package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.CourseType;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */


public class Course implements SavableLoadableJSON {
    private String courseName;
    private CourseType courseType;
    private int coursePopularity;
    private int studentCount;
    private Boolean isLab;

    public Course() {}

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

    public static ArrayList<Course> genCourses(/*professor*/) {
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Programming Paradigms", CourseType.MAG, 10, 10, false));
        courses.add(new Course("MRH", CourseType.MAG, 2, 150, false));
        courses.add(new Course("Algorithms", CourseType.MAG, 7, 100, false));
        courses.add(new Course("Proba", CourseType.MAG, 1, 200, false));
        courses.add(new Course("English", CourseType.MAG, 0, 80, false));
        return courses;
    }


    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject uni = new JSONObject();
        uni.put("courseName", courseName);
        uni.put("courseType", courseType.getName());
        uni.put("coursePopularity", coursePopularity);
        uni.put("studentCount", studentCount);
        uni.put("isLab", isLab);

        return uni;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.courseName = jsonO.getString("courseName");
        this.courseType = CourseType.getEnum(jsonO.getString("courseType"));
        this.coursePopularity = jsonO.getInt("coursePopularity");
        this.studentCount = jsonO.getInt("studentCount");
        this.isLab = jsonO.getBoolean("isLab");
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseType=" + courseType +
                ", coursePopularity=" + coursePopularity +
                ", studentCount=" + studentCount +
                ", isLab=" + isLab +
                '}';
    }
}
