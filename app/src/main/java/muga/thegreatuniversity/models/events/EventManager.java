package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.EventType;
import muga.thegreatuniversity.lists.enums.EventValueType;

/**
 * Created on 28/02/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventManager {
    private ArrayList<Event> events;

    private EventManager(ArrayList<Event> events){
        this.events=events;
    }

    private static class EventManagerHolder {
        private final static EventManager instance = new EventManager(genEvent());
    }

    public static EventManager get() {
        return EventManagerHolder.instance;
    }

    public Event newEvent() {
        int random = (int) Math.floor(Math.random()*this.sizeEvents()*5); //1 chance out of 5 to get an event
        if(random < this.sizeEvents())
            return getEvent(random);
        return null;
    }

    public Event getEvent(int index) {
        return events.get(index);
    }

    public int sizeEvents(){
        return events.size();
    }

    private static ArrayList<Event> genEvent(){
        ArrayList<Event> events = new ArrayList<>();


        EventResult yAct = new EventResult();
        EventResult nAct = new EventResult();

        EventAction act = new EventAction(EventActionType.ADD, EventValueType.MONEY, -100);
        yAct.addAction(act);
        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, 2);
        yAct.addAction(act);

        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, -10);
        nAct.addAction(act);

        events.add(new Event(EventType.TWO_CHOICES, "Do you want to organize the 24H velo", "Yes", "No", yAct, nAct));

        yAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.MONEY, 0.5);
        yAct.addAction(act);

        events.add(new Event(EventType.DETERMINIST,"You lost half your money", null, null, yAct, null));

        return events;
    }
}
