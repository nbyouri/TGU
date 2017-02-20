package muga.thegreatuniversity.models;

/**
 * Created by youri on 20/02/2017.
 */

public class Professor {
    private String name;
    private int popularity;     // combination of experience and friendliness
    private Course[] courses;   // from 1 to 5 courses he gives
    private int age;            // will he die soon (?)

    public Professor(String name, int popularity, Course[] courses, int age) {
        this.name = name;
        this.popularity = popularity;
        this.courses = courses;
        this.age = age;
    }
}