package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.lists.enums.CourseType;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */


public class Course {
    private String courseName;
    private CourseType courseType;

    Course() {}

    Course(String courseName) {
        this.courseName = courseName;
        this.courseType = CourseType.MAG;
    }

    public JSONObject getAsJSON() throws JSONException {
        JSONObject uni = new JSONObject();
        uni.put("courseName", courseName);
        uni.put("courseType", courseType.getName());
        return uni;
    }

    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.courseName = jsonO.getString("courseName");
        this.courseType = CourseType.getEnum(jsonO.getString("courseType"));
    }

    private static Course genCourse() {
        /* TODO handle labs */
        return new Course(Assets.getRandomCourse());
    }

    static ArrayList<Course> genCourseList(int nb) {
        ArrayList<Course> courses = new ArrayList<>();
        for (int i = 0; i < nb; i++) {
            courses.add(genCourse());
        }
        return courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        return courseName != null ? courseName.equals(course.courseName) :
                course.courseName == null && courseType == course.courseType;

    }

    @Override
    public int hashCode() {
        int result = courseName != null ? courseName.hashCode() : 0;
        result = 31 * result + (courseType != null ? courseType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return courseName + " (" + courseType.getName() + ")";
    }
}
