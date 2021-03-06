package muga.thegreatuniversity.models;

import android.support.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.lists.DefaultValues;
import muga.thegreatuniversity.lists.enums.ProfType;
import muga.thegreatuniversity.utils.Tools;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Professor {
    private String name = "";
    private ProfType type;      // Type of professor
    private int popularity;     // combination of experience and friendliness
    private ArrayList<Course> courses;      // from 1 to 5 courses he gives
    private int age;            // will he die soon
    private int price;          // Cost of hiring

    private static final int NB_HIRES = 5; // amount of professors available to hire each turn

    static Professor genSnoop(ArrayList<Course> courses) {
        Professor snoop = new Professor();
        snoop.name = DefaultValues.NAME_FIRST_PROF;
        snoop.type = ProfType.LEGENDARY;
        snoop.popularity = 25;
        snoop.courses = courses;
        snoop.age = 20;
        snoop.price = 0;
        return snoop;
    }

    public Professor() {
        courses = new ArrayList<>();
    }

    @NonNull
    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getPopularity() {
        return popularity;
    }

    private void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public ArrayList<Course> getCourses() {
        return courses;
    }

    private void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
    }

    public int getAge() {
        return age;
    }

    private void setAge(int age) {
        this.age = age;
    }

    public ProfType getType() {
        return type;
    }

    private void setType(ProfType type) {
        this.type = type;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    static ArrayList<Professor> genProfList() {
        ArrayList<Professor> profs = new ArrayList<>();
        for (int i = 0; i < NB_HIRES; i++)
            profs.add(generateProfessor());
        return profs;
    }

    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("type", type.getName());
        obj.put("popularity", popularity);
        JSONArray jsonCourses = new JSONArray();
        for (Course c : courses) {
            jsonCourses.put(c.getAsJSON());
        }
        obj.put("courses", jsonCourses);
        obj.put("age", age);
        obj.put("price", price);
        return obj;
    }

    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.type = ProfType.getEnum(jsonO.getString("type"));
        this.popularity = jsonO.getInt("popularity");
        JSONArray courseObj = jsonO.getJSONArray("courses");
        for (int i = 0; i < courseObj.length(); i++) {
            Course c = new Course();
            c.loadJSON(courseObj.getJSONObject(i));
            this.courses.add(c);
        }
        this.age = jsonO.getInt("age");
        this.price = jsonO.getInt("price");
    }

    private static Professor generateProfessor() {
        Professor p = new Professor();
        String name = "";
        while (name.isEmpty()) {
            name = Tools.Capitalize(Assets.getRandomAdjective());
        }
        String surname = "";
        while (surname.isEmpty()) {
            surname = Tools.Capitalize(Assets.getRandomAnimal());
        }
        p.setName(Tools.Capitalize(name + "  " + Tools.Capitalize(surname)));
        p.setType(ProfType.getType());
        p.setPopularity(p.getType().getPopularity());
        p.setCourses(Course.genCourseList(p.getType().ranking()));
        p.setAge(ProfType.getAge());
        p.setPrice(p.getType().getPrice());
        return p;
    }

    /* Sort based on rarity */
    static void sort(ArrayList<Professor> list) {
        Collections.sort(list, new Comparator<Professor>() {
            @Override
            public int compare(Professor o1, Professor o2) {
                return o2.getType().ranking() - o1.getType().ranking();
            }
        });
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Professor professor = (Professor) o;

        return popularity == professor.popularity &&
                age == professor.age && price == professor.price &&
                (name != null ? name.equals(professor.name) :
                        professor.name == null && type == professor.type &&
                                (courses != null ? courses.equals(professor.courses) :
                                        professor.courses == null));

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + popularity;
        result = 31 * result + (courses != null ? courses.hashCode() : 0);
        result = 31 * result + age;
        result = 31 * result + price;
        return result;
    }
}