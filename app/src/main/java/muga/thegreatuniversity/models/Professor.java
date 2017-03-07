package muga.thegreatuniversity.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Professor implements SavableLoadableJSON {
    private String name;
    private int popularity;     // combination of experience and friendliness
    private Course course;      // from 1 to 5 courses he gives
    private int age;            // will he die soon (?)

    public Professor() {}

    public Professor(String name, int popularity, Course course, int age) {
        this.name = name;
        this.popularity = popularity;
        this.course = course;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course courses) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static ArrayList<Professor> genProfList(/* popularity of university, level of player */) {
        ArrayList<Professor> profs = new ArrayList<>();
        ArrayList<Course> courses = Course.genCourses();
        profs.add(new Professor("Schauss", 1, courses.get(0), 42));
        profs.add(new Professor("Deville", 8, courses.get(1), 42));
        profs.add(new Professor("Mens", 9, courses.get(2), 42));
        profs.add(new Professor("Bonaventure", 7, courses.get(3), 42));
        profs.add(new Professor("Pecheur", 8, courses.get(4), 42));
        return profs;
    }

    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("popularity", popularity);
        obj.put("course", course.getAsJSON());
        obj.put("age", age);

        return obj;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.popularity = jsonO.getInt("popularity");
        // TODO : course array
        this.course = new Course();
        JSONObject courseObj = jsonO.getJSONObject("course");
        this.course.loadJSON(courseObj);
        this.age = jsonO.getInt("age");
    }
}