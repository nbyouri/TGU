package muga.thegreatuniversity.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.DefaultValues;
import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.RoomType;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.models.events.EventAction;
import muga.thegreatuniversity.models.events.EventManager;
import muga.thegreatuniversity.models.events.EventResult;
import muga.thegreatuniversity.utils.Logger;

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
    private int studentNb;
    private int week;
    private Event currentEvent;

    // main objects
    private ArrayList<Professor> professors;
    private ArrayList<Room> rooms;
    private ArrayList<Professor> availableHires;

    private static class UniversityHolder {
        private final static University instance = new University();
    }

    public static University get() {
        return UniversityHolder.instance;
    }

    private University() {
        professors = new ArrayList<>();
        rooms = new ArrayList<>();
        availableHires = new ArrayList<>();
    }

    @Override
    public JSONObject getAsJSON() throws JSONException  {
        JSONObject uni = new JSONObject();

        uni.put("money", money);
        uni.put("moral", moral);
        uni.put("studentNb", studentNb);
        uni.put("week", week);
        uni.put("name", name);

        JSONArray profs = new JSONArray();
        for (Professor p : professors) {
            profs.put(p.getAsJSON());
        }
        uni.put("profs", profs);

        JSONArray ra = new JSONArray();
        for (Room r : rooms) {
            ra.put(r.getAsJSON());
        }
        uni.put("rooms", ra);

        JSONArray ah = new JSONArray();
        for (Professor p : availableHires) {
            ah.put(p.getAsJSON());
        }
        uni.put("availableHires", ah);

        return uni;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.money = jsonO.getInt("money");
        this.moral = jsonO.getInt("moral");
        this.studentNb = jsonO.getInt("studentNb");
        this.week = jsonO.getInt("week");
        this.name = jsonO.getString("name");

        JSONArray pArr = jsonO.getJSONArray("profs");
        for (int i = 0; i < pArr.length(); i++) {
            Professor p = new Professor();
            p.loadJSON(pArr.getJSONObject(i));
            this.professors.add(p);
        }

        JSONArray rArr = jsonO.getJSONArray("rooms");
        for (int i = 0; i < rArr.length(); i++) {
            Room r = new Room();
            r.loadJSON(rArr.getJSONObject(i));
            this.rooms.add(r);
        }

        JSONArray hArr = jsonO.getJSONArray("availableHires");
        for (int i = 0; i < hArr.length(); i++) {
            Professor h = new Professor();
            h.loadJSON(hArr.getJSONObject(i));
            this.availableHires.add(h);
        }
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
        int maxPopulation =0;
        ArrayList<Room> l = University.get().getRooms();
        for(int i = 0; i<l.size(); i++) {
            maxPopulation = maxPopulation + l.get(i).getCapacity();
        }
        return maxPopulation;
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

    public ArrayList<Professor> getAvailableHires() {
        return availableHires;
    }

    public void reloadHires() {
        this.availableHires = Professor.genProfList();
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

    public Event getCurrentEvent() {
        return currentEvent;
    }

    public void newTurn(){
        this.addWeek(); //Increment the value of week
        this.newMoney(); //Gain 10$ per student each week
        this.newStudentNB(); //Popularity is the chance of increasing the student by 1 each week
        this.reloadHires(); // Reload list of professors available for hire
        this.currentEvent = EventManager.get().newEvent();
    }

    public void newMoney(){
        this.money = this.money + this.studentNb*10;
    }

    public void newStudentNB(){
        if(this.studentNb < this.getMaxPopulation()) {

            int random = (int) Math.floor(Math.random() * 101);
            if (random <= this.popularity) {
                setStudentNb(this.studentNb + 1);
            }
        }
    }

    public void createNewUniversity(String name){
        University.get().setName(name);
        University.get().setStudentNb(DefaultValues.START_STUDENT_NB);
        University.get().setMoney(DefaultValues.START_MONEY);
        University.get().setWeek(DefaultValues.START_WEEK);
        University.get().setPopularity(DefaultValues.START_POPULARITY);
        University.get().reloadHires();
        University.get().addRoom(new Room("Classroom",20, RoomType.CLASS,500));
    }

    public void eventAction(Event event){
        EventResult result = event.getResult();

        for (EventAction act : result.getActions()) {
            switch (act.getValueType()) {
                case MONEY:
                    this.money = computation(act.getActionType(), this.money, act.getValue());
                    break;
                case POPULARITY:
                    this.popularity = computation(act.getActionType(), this.popularity, act.getValue());
                    break;
                case MORAL:
                    this.moral = computation(act.getActionType(), this.moral, act.getValue());
                    break;
                case STUDENT:
                    this.studentNb = computation(act.getActionType(), this.studentNb, act.getValue());
                    break;
                case PROF:
                    break;
            }
        }

    }

    private int computation(EventActionType type, int prevValue, double value){
        switch (type) {
            case ADD:
                return prevValue + (int)value;
            case MULTIPLICATION:
                return (int)(prevValue * value);
            case REMOVE:
                return 0;
        }
        Logger.error("Error Computation Event : type = " + type + " , prevValue = "
                + prevValue + " , value = " + value);
        return 0;
    }
}