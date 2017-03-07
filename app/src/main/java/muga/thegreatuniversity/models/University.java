package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.AnsType;
import muga.thegreatuniversity.lists.DefaultValues;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class University implements SavableLoadableJSON {
    // basic properties
    private String name;
    private int popularity;
    private int money;
    private int moral;
    private int maxPopulation;
    private int studentNb;
    private int week;

    //private boolean created;

    // main objects
    private ArrayList<Professor> professors;
    private ArrayList<Room> rooms;

    private University() {
        professors = new ArrayList<Professor>();
        rooms = new ArrayList<Room>();
    }

    @Override
    public JSONObject getAsJSON() throws JSONException  {
        JSONObject uni = new JSONObject();
        // TODO : CONTINUE
        uni.put("money", money);
        uni.put("moral", moral);
        uni.put("studentNb", studentNb);
        uni.put("week", week);
        uni.put("name", name);
        uni.put("maxPopulation", maxPopulation);

        // TODO : ROOMS and Profs

        return uni;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.money = jsonO.getInt("money");
        this.moral = jsonO.getInt("moral");
        this.studentNb = jsonO.getInt("studentNb");
        this.week = jsonO.getInt("week");
        this.name = jsonO.getString("name");
        this.maxPopulation = jsonO.getInt("maxPopulation");

        // TODO : ROOMS and Profs

    }

    private static class UniversityHolder {
        private final static University instance = new University();
    }



    public static University get() {
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

    public void addProfessor(Professor professor) {
        professors.add(professor);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public int getStudentNb() {
        return studentNb;
    }

    public void setStudentNb(int studentNb) {
        this.studentNb = studentNb;
    }

    public int getWeek() {
        return week;
    }

    public void addWeek() {
        this.week++;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void newTurn(){
        this.addWeek(); //Increment the value of week
        this.newMoney(); //Gain 10$ per student each week
        this.newStudentNB(); //Popularity is the chance of increasing the student by 1 each week
    }

    public void newMoney(){
        this.money = this.money + this.studentNb*10;
    }

    public void newStudentNB(){
        if(this.studentNb < this.maxPopulation) {

            int random = (int) Math.floor(Math.random() * 101);
            if (random <= this.popularity) {
                setStudentNb(this.studentNb + 1);
            }
        }
    }

    public void createNewUniversity(String name){
        University.get().setName(name);
        University.get().setStudentNb(DefaultValues.START_STUDENT_NB);
        University.get().setMaxPopulation(DefaultValues.START_MAX_POPULATION);
        University.get().setMoney(DefaultValues.START_MONEY);
        University.get().setWeek(DefaultValues.START_WEEK);
        University.get().setPopularity(DefaultValues.START_POPULARITY);
    }

    // TODO : CHANGE IN GENERICS WAYS
    public void eventAction(int id, AnsType type){
        System.out.println(id);
        switch (id){
            case 1: // You lost half your money
                this.money=this.money/2;
                break;
            case 2: //24h velo event
                switch (type){
                    case YES:
                        this.money-=100;// Cost 100 to organize
                        this.popularity+=2;
                        break;
                    case NO:
                        this.popularity--;
                        break;
                    case NOANS:
                        break;
                }
                break;
        }
    }
}