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
import muga.thegreatuniversity.models.events.Events;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class University implements SavableLoadableJSON {

    // Help Object
    private FormulaUniversity formula;

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
        currentEvents = new ArrayList<>();
        formula = new FormulaUniversity(this);
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

        this.sortProfessors();
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
        if(money < 0) {
            this.money = 0;
        }
        else {
            this.money = money;
        }
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

    public FormulaUniversity getFormula() {
        return formula;
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
        int income= this.studentNb * 10;

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
        this.updateCurrentEvents();
        this.addWeek(); // Increment the value of week
        this.newStudentNB(); // Popularity is the chance of increasing the student by 1 each week
        this.setMoney(this.money + this.getIncome());
        this.reloadHires(); // Reload list of professors available for hire
        this.currentEvents.addAll(EventManager.getEvents());
        this.sortProfessors();
    }

    public void sortProfessors() {
        Professor.sort(this.professors);
        Professor.sort(this.availableHires);
    }

    public void updateCurrentEvents(){
        ArrayList<Event> toRemove = new ArrayList<Event>();
        for (Event ev: this.currentEvents) {
            if(ev.getCount()<ev.getDuration()){
                ev.incrementCount();
            }
            else{
                ev.setCount0();
                toRemove.add(ev);
            }
        }
        for (Event ev: toRemove)
        {
            ev.setDisplayable(true);
            this.currentEvents.remove(ev);
        }
    }

    private void newStudentNB(){

        int maxPop = this.getMaxPopulation();

        long newStudentNb = formula.newStudent();

        if(this.studentNb + newStudentNb > maxPop) {
            studentNb = maxPop;
        } else if (this.studentNb + newStudentNb < 1) {
            studentNb = 0;
        } else {
            studentNb += newStudentNb;
        }

    }

    public void createNewUniversity(String name){
        University.get().setName(name);
        University.get().setStudentNb(DefaultValues.START_STUDENT_NB);
        University.get().setMoney(DefaultValues.START_MONEY);
        University.get().setWeek(DefaultValues.START_WEEK);
        University.get().setBasicPopularity(DefaultValues.START_POPULARITY);
        University.get().reloadHires();
        this.rooms = new ArrayList<>();
        this.professors = new ArrayList<>();
        University.get().addRoom(new Room("Classroom",20, RoomType.CLASS,500));
        University.get().setMoral(DefaultValues.START_MORAL);
    }

    public void eventAction(Event event){
        EventResult result = event.getResult();

        for (EventAction act : result.getActions()) {
            switch (act.getValueType()) {
                case MONEY:
                    this.setMoney(computation(act.getActionType(), this.money, act.getValue()));
                    break;
                case POPULARITY:
                    this.setBasicPopularity(computation(act.getActionType(), this.basicPopularity, act.getValue()));
                    break;
                case MORAL:
                    this.setMoral(computation(act.getActionType(), this.moral, act.getValue()));
                    break;
                case STUDENT:
                    this.setStudentNb(computation(act.getActionType(), this.studentNb, act.getValue()));
                    break;
                case PROF:
                    computation(act.getActionType(), 0, act.getValue());
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
            case FIRE:
                for(int i = 0; i < value; i++) {
                    professors.remove(i);
                }
                return 0;
        }
        Logger.error("Error Computation Event : type = " + type + " , prevValue = "
                + prevValue + " , value = " + value);
        return 0;
    }
}