package muga.thegreatuniversity.models;

import android.content.res.AssetManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import muga.thegreatuniversity.app.App;
import muga.thegreatuniversity.lists.CourseType;

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
        try {
            profs.add(generate_professor());
        } catch(Exception e) {}
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

    /**
     * Generate a random professor based on the following university properties:
     *  - popularity
     *  - money
     *  TODO: - Capitalize Name
     *        - Optimize (HM)?
     *        - Popularity -> legendary -> different icons/color?
     *        - normalize age
     *        - generate course
     * @return Professor
     */
    public static Professor generate_professor() throws IOException {
        AssetManager assets = App.getAppContext().getAssets();
        Scanner adjectivesFile = new Scanner(assets.open("Adjectives.txt"));

        ArrayList<String> adjectives = new ArrayList<>();
        while(adjectivesFile.hasNext()){
            adjectives.add(adjectivesFile.next());
        }

        Scanner animalsFile = new Scanner(assets.open("Animals.txt"));
        ArrayList<String> animals = new ArrayList<>();
        while(animalsFile.hasNext()){
            animals.add(animalsFile.next());
        }

        Random rng = new Random();
        int randomAdjective = rng.nextInt(adjectives.size());
        int randomAnimal = rng.nextInt(animals.size());

        String profFirstName = adjectives.get(randomAdjective);
        String profName = animals.get(randomAnimal);

        StringBuilder profFNSB = new StringBuilder(profFirstName.toLowerCase());
        profFNSB.setCharAt(0, Character.toUpperCase(profFNSB.charAt(0)));
        profFirstName = profFNSB.toString();

        StringBuilder profNSB = new StringBuilder(profName.toLowerCase());
        profNSB.setCharAt(0, Character.toUpperCase(profNSB.charAt(0)));
        profName = profNSB.toString();

        Professor p = new Professor(profFirstName + "  " + profName, rng.nextInt(10),
                new Course("Empty Course", CourseType.MAG,0,0,false), rng.nextInt(100));

        return p;
    }
}