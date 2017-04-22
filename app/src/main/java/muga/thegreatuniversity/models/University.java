package muga.thegreatuniversity.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.DefaultValues;
import muga.thegreatuniversity.lists.enums.CourseType;
import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.ProfType;
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
    private UniversityBasicData basicData;

    private String name;
    private int week;

    // main objects
    private ArrayList<Event> currentEvents;
    private ArrayList<Professor> professors;
    private ArrayList<Room> rooms;
    private ArrayList<Professor> availableHires;
    private ArrayList<Kot> kots;
    private ArrayList<Entertainment> entertainments;
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
        entertainments = new ArrayList<>();
        currentEvents = new ArrayList<>();
        formula = new FormulaUniversity(this);
        basicData = new UniversityBasicData();
        kots = new ArrayList<>();
    }

    @Override
    public JSONObject getAsJSON() throws JSONException  {
        JSONObject uni = new JSONObject();

        uni.put("money", basicData.getMoney());
        uni.put("moral", basicData.getMoral());
        uni.put("studentNb", basicData.getStudentNb());
        uni.put("week", week);
        uni.put("name", name);
        uni.put("basicPopularity", basicData.getBasicPopularity());

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

        JSONArray ko = new JSONArray();
        for (Kot k : kots) {
            ko.put(k.getAsJSON());
        }
        uni.put("kots", ko);

        JSONArray en = new JSONArray();
        for (Entertainment e : entertainments) {
            en.put(e.getAsJSON());
        }
        uni.put("entertainments", ko);

        return uni;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
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

        JSONArray kArr = jsonO.getJSONArray("kots");
        for (int i = 0; i < kArr.length(); i++) {
            Kot k = new Kot();
            k.loadJSON(kArr.getJSONObject(i));
            this.kots.add(k);
        }

        JSONArray eArr = jsonO.getJSONArray("entertainments");
        for (int i = 0; i < eArr.length(); i++) {
            Entertainment e = new Entertainment();
            e.loadJSON(eArr.getJSONObject(i));
            this.entertainments.add(e);
        }

        this.sortProfessors();

        this.basicData.setMoney(jsonO.getInt("money"));
        this.basicData.setMoral(jsonO.getInt("moral"));
        this.basicData.setStudentNb(jsonO.getInt("studentNb"),getMaxPopulation());
        this.week = jsonO.getInt("week");
        this.name = jsonO.getString("name");
        this.basicData.setBasicPopularity( jsonO.getInt("basicPopularity"));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasicPopularity() {
        return basicData.getBasicPopularity();
    }

    public int getMoney() {
        return basicData.getMoney();
    }

    public void setMoney(int money){
        basicData.setMoney(money);
    }

    public int getMoral() {
        return basicData.getMoral();
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

    public ArrayList<Kot> getKots() {
        return kots;
    }

    public void addKot(Kot kot) {
        kots.add(kot);
    }

    public ArrayList<Entertainment> getEntertainments() {
        return entertainments;
    }

    public void addEntertainments(Entertainment entertainment) {
        entertainments.add(entertainment);
    }

    public ArrayList<Professor> getAvailableHires() {
        return availableHires;
    }

    public void reloadHires() {
        this.availableHires = Professor.genProfList();
    }

    public int getStudentNb() {
        return basicData.getStudentNb();
    }

    public FormulaUniversity getFormula() {
        return formula;
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
        int income = basicData.getStudentNb() * DefaultValues.MONEY_BY_STUDENT;

        for (Professor p : professors){
            income -= p.getPrice();
        }

        return income;
    }

    public int getPopularity(){
        int pop = basicData.getBasicPopularity();
        // basicPopulation + ForeachProf(basicPopu * prof.Popularity)
        for (Professor p : professors){

            pop += (p.getPopularity()* basicData.getBasicPopularity()/100);

        }
        pop += getMoral() *  basicData.getBasicPopularity()/100;
        return pop;
    }

    public int getMaxPopulation() {
        int maxPopulation = 0;
        ArrayList<Room> l = University.get().getRooms();
        for(int i = 0; i<l.size(); i++) {
            maxPopulation = maxPopulation + l.get(i).getCapacity();
        }
        ArrayList<Kot> k = University.get().getKots();
        for(int j = 0; j<k.size(); j++) {
            maxPopulation = maxPopulation + k.get(j).getCapacity();
        }
        return maxPopulation;
    }

    public int newTurn() {
        this.addWeek(); // Increment the value of week
        this.newStudentNB(); // Popularity is the chance of increasing the student by 1 each week
        basicData.setMoney(basicData.getMoney() + this.getIncome());
        this.updateCurrentEvents();
        this.currentEvents.addAll(EventManager.getEvents());
        this.reloadHires(); // Reload list of professors available for hire
        this.sortProfessors();

        if (basicData.getMoney() < 0){
            return removeBestProfessor();
        }
        return 0;
    }

    private int removeBestProfessor() { // YOU LOSS IF YOU DON'T HAVE MONEY AND PROFESSOR
        if (professors.size() == 1){
            return 2; // MEAN LOSS
        } else {
            if (professors.get(0).getName().equals(DefaultValues.NAME_FIRST_PROF)){
                professors.remove(1);
            } else {
                professors.remove(0);
            }
            return 1;
        }
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
        int newStudentNb =  formula.newStudent();
        basicData.setStudentNb(basicData.getStudentNb()+newStudentNb, maxPop);
    }

    public void createNewUniversity(String name){
        this.name = name;
        this.week = (DefaultValues.START_WEEK);
        reloadHires();
        this.rooms = new ArrayList<>();
        this.kots = new ArrayList<>();
        this.professors = new ArrayList<>();
        this.entertainments = new ArrayList<>();
        this.addRoom(new Room("Classroom",20, RoomType.CLASS,500));
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course(DefaultValues.NAME_FIRST_COURSE, CourseType.MAG));
        this.professors.add(new Professor (DefaultValues.NAME_FIRST_PROF, ProfType.LEGENDARY, 25, courses, 20, 0));
        basicData.setMoney(DefaultValues.START_MONEY);
        basicData.setStudentNb(DefaultValues.START_STUDENT_NB, getMaxPopulation());
        basicData.setMoral(DefaultValues.START_MORAL);
        basicData.setBasicPopularity(DefaultValues.START_POPULARITY);
    }

    public void eventAction(Event event){
        EventResult result = event.getResult();

        for (EventAction act : result.getActions()) {
            switch (act.getValueType()) {
                case MONEY:
                    basicData.setMoney(computation(act.getActionType(), this.getMoney(), act.getValue()));
                    break;
                case POPULARITY:
                    basicData.setBasicPopularity(computation(act.getActionType(), this.getBasicPopularity(), act.getValue()));
                    break;
                case MORAL:
                    basicData.setMoral(computation(act.getActionType(), basicData.getMoral(), act.getValue()));
                    break;
                case STUDENT:
                    basicData.setStudentNb(computation(act.getActionType(), basicData.getStudentNb(), act.getValue()),getMaxPopulation());
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