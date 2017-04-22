package muga.thegreatuniversity.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.lists.enums.ProfType;
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
    private ArrayList<Course> courses;      // from 1 to 5 courses he gives
    private int age;            // will he die soon
    private int price;          // Cost of hiring

    public static final int NB_HIRES = 5; // amount of professors available to hire each turn

    public Professor(String name, ProfType type, int popularity, ArrayList<Course> courses, int age, int price) {
        this.name = name;
        this.type = type;
        this.popularity = popularity;
        this.courses = courses;
        this.age = age;
        this.price = price;
    }

    public Professor() {
        courses = new ArrayList<>();
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

    public ArrayList<Course> getCourses() {
        return courses;
    }

    public void setCourses(ArrayList<Course> courses) {
        this.courses = courses;
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

    public static ArrayList<Professor> genProfList() {
        ArrayList<Professor> profs = new ArrayList<>();
        for (int i = 0; i < NB_HIRES; i++)
            profs.add(generate_professor());
        return profs;
    }

    @Override
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

    @Override
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

    public static Professor generate_professor() {
        Professor p = new Professor();
        p.setName(Tools.Capitalize(Assets.getRandomAdjective()) + "  "
                + Tools.Capitalize(Assets.getRandomAnimal()));
        p.setType(ProfType.getType());
        p.setPopularity(p.getType().getPopularity());
        p.setCourses(Course.genCourseList(p.getType().ranking()));
        p.setAge(ProfType.getAge());
        p.setPrice(p.getType().getPrice());
        return p;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", popularity=" + popularity +
                ", age=" + age +
                ", price=" + price +
                '}';
    }

    /* Sort based on rarity */
    public static void sort(ArrayList<Professor> list) {
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

        if (popularity != professor.popularity) return false;
        if (age != professor.age) return false;
        if (price != professor.price) return false;
        if (name != null ? !name.equals(professor.name) : professor.name != null) return false;
        return type == professor.type && (courses != null ? courses.equals(professor.courses) :
                professor.courses == null);

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