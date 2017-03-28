package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.lists.enums.CourseType;
import muga.thegreatuniversity.utils.Tools;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */


public class Course implements SavableLoadableJSON {
    private String courseName;
    private CourseType courseType;

    public static final int NB_COURSES = 5; // maximum courses a professor can teach;

    public Course() {}

    public Course(String courseName, CourseType courseType) {
        this.courseName = courseName;
        this.courseType = courseType;
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


    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject uni = new JSONObject();
        uni.put("courseName", courseName);
        uni.put("courseType", courseType.getName());
        return uni;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.courseName = jsonO.getString("courseName");
        this.courseType = CourseType.getEnum(jsonO.getString("courseType"));
    }

    public static Course genCourse() {
        /* TODO handle labs */
        return new Course(Assets.getRandomCourse(), CourseType.MAG);
    }

    public static ArrayList<Course> genCourseList() {
        ArrayList<Course> courses = new ArrayList<>();
        for (int i = 0; i < Tools.randInt(1, NB_COURSES); i++) {
            courses.add(genCourse());
        }
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseName != null ? !courseName.equals(course.courseName) : course.courseName != null)
            return false;
        return courseType == course.courseType;

    }

    @Override
    public int hashCode() {
        int result = courseName != null ? courseName.hashCode() : 0;
        result = 31 * result + (courseType != null ? courseType.hashCode() : 0);
        return result;
    }
}
