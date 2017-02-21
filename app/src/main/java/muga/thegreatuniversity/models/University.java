package muga.thegreatuniversity.models;

import java.util.ArrayList;

/**
 * Master object
 * Created by youri on 20/02/2017.
 */

public class University {
    // basic properties
    private String name;
    private int popularity;
    private int money;
    private int moral;
    private int maxPopulation;

    // main objects
    private ArrayList<Professor> professors;
    private ArrayList<Room> rooms;

    public University(String name, int popularity, int money, int moral, int maxPopulation) {
        this.name = name;
        this.popularity = popularity;
        this.money = money;
        this.moral = moral;
        this.maxPopulation = maxPopulation;
    }
}
