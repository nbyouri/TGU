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

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseType=" + courseType +
                '}';
    }
}
