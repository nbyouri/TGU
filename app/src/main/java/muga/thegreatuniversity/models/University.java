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

    public University() {
        professors = new ArrayList<Professor>();
        rooms = new ArrayList<Room>();
    }

    private static class UniversityHolder {
        private final static University instance = new University();
    }

    public static University getInstance() {
        return UniversityHolder.instance;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public ArrayList<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}