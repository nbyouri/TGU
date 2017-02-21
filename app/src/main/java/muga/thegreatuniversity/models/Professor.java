package muga.thegreatuniversity.models;

import java.util.ArrayList;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Professor {
    private String name;
    private int popularity;     // combination of experience and friendliness
    private ArrayList<Course> courses;   // from 1 to 5 courses he gives
    private int age;            // will he die soon (?)

    public Professor(String name, int popularity, Course[] courses, int age) {
        this.name = name;
        this.popularity = popularity;
        this.courses = new ArrayList<Course>();
        this.age = age;
    }
}