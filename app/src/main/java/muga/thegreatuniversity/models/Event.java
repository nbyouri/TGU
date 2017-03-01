package muga.thegreatuniversity.models;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.EventType;

/**
 * Created on 28/02/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Event {
    private String event;
    private String firstChoice;
    private String secondChoice;
    private EventType type;
    private int id;

    public Event(String event, String firstChoice, String secondChoice, int id, EventType type){
        this.event=event;
        this.firstChoice=firstChoice;
        this.secondChoice=secondChoice;
        this.type=type;
        this.id=id;
    }

    public String getEvent() {
        return event;
    }

    public String getFirstChoice() {
        return firstChoice;
    }

    public void setSecondChoice(String secondChoice) {
        this.secondChoice = secondChoice;
    }

    public EventType getType() {
        return type;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public void setFirstChoice(String firstChoice) {
        this.firstChoice = firstChoice;
    }

    public String getSecondChoice() {
        return secondChoice;
    }

    public void setType(EventType type) {
        this.type = type;
    }

    public static ArrayList<Event> genEvent(){
        ArrayList<Event> events = new ArrayList<>();

        events.add(new Event("You lost half your money", null, null, 1, EventType.DETEV));
        events.add(new Event("Do you want to organize the 24hvelo", "Yes", "No", 2, EventType.NODETEV));
        return events;
    }

    public int getId() {
        return this.id;
    }
}
