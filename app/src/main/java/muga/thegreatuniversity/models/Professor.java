package muga.thegreatuniversity.models;

import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import muga.thegreatuniversity.app.App;
import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.lists.enums.CourseType;
import muga.thegreatuniversity.lists.enums.ProfType;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.Tools;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Professor implements SavableLoadableJSON {
    private String name;
    private ProfType type;      // Type of professor
    private int popularity;     // combination of experience and friendliness
    private Course course;      // from 1 to 5 courses he gives
    private int age;            // will he die soon
    private int price;          // Cost of hiring

    public static final int NB_HIRES = 5; // amount of professors available to hire each turn

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

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ProfType getType() {
        return type;
    }

    public void setType(ProfType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static ArrayList<Professor> genProfList(/* popularity of university, level of player */) {
        ArrayList<Professor> profs = new ArrayList<>();
        try {
            for (int i = 0; i < NB_HIRES; i++)
                profs.add(generate_professor());
        } catch(Exception e) {
            Logger.error(e.getMessage());
        }
        return profs;
    }

    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("type", type.getName());
        obj.put("popularity", popularity);
        obj.put("course", course.getAsJSON());
        obj.put("age", age);
        obj.put("price", price);
        return obj;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.type = ProfType.getEnum(jsonO.getString("type"));
        this.popularity = jsonO.getInt("popularity");
        // TODO : course array
        this.course = new Course();
        JSONObject courseObj = jsonO.getJSONObject("course");
        this.course.loadJSON(courseObj);
        this.age = jsonO.getInt("age");
        this.price = jsonO.getInt("price");
    }

    /**
     * Generate a random professor based on the following university properties:
     *  - popularity
     *  - money
     *  TODO:
     *        - generate course
     * @return Professor
     */
    public static Professor generate_professor() throws IOException {
        Professor p = new Professor();
        p.setName(Tools.Capitalize(Assets.getRandomAdjective()) + "  "
                + Tools.Capitalize(Assets.getRandomAnimal()));
        p.setType(ProfType.getType());
        p.setPopularity(p.getType().getPopularity());
        p.setCourse(new Course("Empty Course", CourseType.MAG));
        p.setAge(ProfType.getAge());
        p.setPrice(p.getType().getPrice());
        return p;
    }
}