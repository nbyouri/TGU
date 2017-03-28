package muga.thegreatuniversity.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;

import muga.thegreatuniversity.lists.DefaultValues;
import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.RoomType;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.models.events.EventAction;
import muga.thegreatuniversity.models.events.EventManager;
import muga.thegreatuniversity.models.events.EventResult;
import muga.thegreatuniversity.models.events.Events;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.SaveManager;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class University implements SavableLoadableJSON {

    // Basic properties
    private String name;
    private int basicPopularity;
    private int money;
    private int moral;
    private int studentNb;
    private int week;

    private ArrayList<Event> currentEvents;

    // main objects
    private ArrayList<Professor> professors;
    private ArrayList<Room> rooms;
    private ArrayList<Professor> availableHires;
    private Events events;

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
        uni.put("basicPopularity", basicPopularity);

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
        this.basicPopularity = jsonO.getInt("basicPopularity");

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

    public int getBasicPopularity() {
        return basicPopularity;
    }

    public void setBasicPopularity(int basicPopularity) {
        this.basicPopularity = basicPopularity;
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

    public ArrayList<Professor> getProfessors() {
        return professors;
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

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public ArrayList<Event> getCurrentEvents() {
        return currentEvents;
    }

    public int getIncome(){
        int income= this.studentNb * 5;

        for (Professor p : professors){
            income -= p.getPrice();
        }

        return income;
    }

    public int getPopularity(){
        int pop = this.basicPopularity;

        // basicPopulation + ForeachProf(basicPopu * prof.Popularity)
        for (Professor p : professors){

            pop += (p.getPopularity()*this.basicPopularity/100);

        }

        pop += getMoral() * this.basicPopularity/100;

        return pop;
    }

    public int getMaxPopulation() {
        int maxPopulation = 0;
        ArrayList<Room> l = University.get().getRooms();
        for(int i = 0; i<l.size(); i++) {
            maxPopulation = maxPopulation + l.get(i).getCapacity();
        }
        return maxPopulation;
    }

    public void newTurn(){
        this.addWeek(); // Increment the value of week
        this.newStudentNB(); // Popularity is the chance of increasing the student by 1 each week
        this.money += this.getIncome(); // Gain 10$ per student each week
        this.reloadHires(); // Reload list of professors available for hire

    }

    private void newStudentNB(){

        int maxPop = this.getMaxPopulation();

        if(this.studentNb < maxPop) {

            // TODO : Change the formula
           /* int addStudents = (int) Math.floor(Math.random() * this.getPopularity());

            if (this.studentNb + addStudents > maxPop){
                setStudentNb(maxPop);
            } else {
                setStudentNb(this.studentNb + addStudents);
            }*/
            Random randomGenerator = new Random();
            int addnumber = 0;

            //Positive
            if(getMoral() >=50 && getMoral() <60)
                addnumber = randomGenerator.nextInt(2);
            if(getMoral() >=60 && getMoral() <70)
                addnumber = randomGenerator.nextInt(4);
            if(getMoral() >=70 && getMoral() <80)
                addnumber = randomGenerator.nextInt(6);
            if(getMoral() >=80 && getMoral() <90)
                addnumber = randomGenerator.nextInt(8);
            if(getMoral() >=90 && getMoral() <= 100)
                addnumber = randomGenerator.nextInt(10);

            //Negative
            if(getMoral() >=40 && getMoral() <50)
                addnumber = - randomGenerator.nextInt(2);
            if(getMoral() >=30 && getMoral() <40)
                addnumber = - randomGenerator.nextInt(4);
            if(getMoral() >=20 && getMoral() <30)
                addnumber = - randomGenerator.nextInt(6);
            if(getMoral() >=10 && getMoral() <20)
                addnumber = - randomGenerator.nextInt(8);
            if(getMoral() >=0 && getMoral() <= 10)
                addnumber = - randomGenerator.nextInt(10);

            if (this.studentNb + addnumber > maxPop){
                setStudentNb(maxPop);
            } else if(this.studentNb + addnumber < 5) {
                setStudentNb(5);
            } else {
                setStudentNb(this.studentNb + addnumber);
            }
        }
    }

    public void createNewUniversity(String name){
        University.get().setName(name);
        University.get().setStudentNb(DefaultValues.START_STUDENT_NB);
        University.get().setMoney(DefaultValues.START_MONEY);
        University.get().setWeek(DefaultValues.START_WEEK);
        University.get().setBasicPopularity(DefaultValues.START_POPULARITY);
        University.get().reloadHires();
        this.rooms = new ArrayList<Room>();
        this.professors = new ArrayList<Professor>();
        University.get().addRoom(new Room("Classroom",20, RoomType.CLASS,500));
        University.get().setMoral(50);
    }

    public void eventAction(Event event){
        EventResult result = event.getResult();

        for (EventAction act : result.getActions()) {
            switch (act.getValueType()) {
                case MONEY:
                    this.money = computation(act.getActionType(), this.money, act.getValue());
                    break;
                case POPULARITY:
                    this.basicPopularity = computation(act.getActionType(), this.basicPopularity, act.getValue());
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