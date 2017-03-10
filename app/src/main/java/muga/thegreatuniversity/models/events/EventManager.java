package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.AnsType;

/**
 * Created on 28/02/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventManager {
    private ArrayList<Event> events;
    private AnsType type;

    public EventManager(ArrayList<Event> events){
        this.events=events;
        this.type=AnsType.NONE;
    }

    private static class EventManagerHolder {
        private final static EventManager instance = new EventManager(Event.genEvent());
    }

    public static EventManager get() {
        return EventManagerHolder.instance;
    }

    // TODO Return a Event, not need Activity
    public Event newEvent() {
        int random = (int) Math.floor(Math.random()*this.sizeEvents()*5); //1 chance out of 5 to get an event
        if(random < this.sizeEvents())
            return getEvent(random);
        return null;
    }

    public void setType(AnsType type) {
        this.type = type;
    }

    public AnsType getType() {
        return type;
    }

    public Event getEvent(int index) {
        return events.get(index);
    }

    public int sizeEvents(){
        return events.size();
    }
}
