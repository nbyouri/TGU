package muga.thegreatuniversity.models;

import java.util.ArrayList;

import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.lists.AnsType;

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
        this.type=AnsType.NOANS;
    }

    private static class EventManagerHolder {
        private final static EventManager instance = new EventManager(Event.genEvent());
    }

    public static EventManager get() {
        return EventManagerHolder.instance;
    }

    // TODO Return a Event, not need Activity
    public void newEvent(MainActivity mainact) {
        int random = (int) Math.floor(Math.random()*this.sizeEvents()*5); //1 chance out of 5 to get an event
        if(random < this.sizeEvents())
            PopUp.alertNewEvent(mainact, getEvent(random));
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
