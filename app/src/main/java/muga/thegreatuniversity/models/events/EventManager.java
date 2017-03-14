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

        /*!!!!!!!!!!!!! Event 24h Velo !!!!!!!!!!!!!!!!!!!!!!!!!! */
        EventAction act = new EventAction(EventActionType.ADD, EventValueType.MONEY, -100);
        yAct.addAction(act);
        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, 2);
        yAct.addAction(act);

        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, -4);
        nAct.addAction(act);

        events.add(new Event(EventType.TWO_CHOICES, "Do you want to organize the 24H velo", "Yes", "No", yAct, nAct));

        /* !!!!!!!!!!!!! Event you lost half your money !!!!!!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.MONEY, 0.9);
        yAct.addAction(act);

        events.add(new Event(EventType.DETERMINIST,"You got robbed by a student, you lost some of your money", null, null, yAct, null));

        /* !!!!!!!!!!!!!!! Event finish thesis !!!!!!!!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, 2);
        yAct.addAction(act);
        act = new EventAction(EventActionType.ADD, EventValueType.MONEY, 150);
        yAct.addAction(act);

        events.add(new Event(EventType.DETERMINIST,"One of your teacher finished his thesis, you gain money and popularity", null, null, yAct, null));

        /* !!!!!!!!!!!!!! Event you loose a student*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.ADD, EventValueType.STUDENT, -1);
        yAct.addAction(act);

        events.add(new Event(EventType.DETERMINIST,"You lost a student because your university suck", null, null, yAct, null));

        /* !!!!!!!!!!! Event Erasmus !!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.ADD, EventValueType.STUDENT, +1);
        yAct.addAction(act);
        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, 2);
        yAct.addAction(act);
        act = new EventAction(EventActionType.ADD, EventValueType.MONEY, -200);
        yAct.addAction(act);

        nAct = new EventResult();
        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, -1);
        nAct.addAction(act);

        events.add(new Event(EventType.TWO_CHOICES, "Set a new Erasmus partnership in a new country", "Yes", "No", yAct, nAct));

        /*!!!!!!!!!!! Event bourse d'étude !!!!!!!!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.ADD, EventValueType.MONEY, -50);
        yAct.addAction(act);
        act = new EventAction(EventActionType.ADD, EventValueType.POPULARITY, 2);
        yAct.addAction(act);

        nAct = new EventResult();
        act = new EventAction(EventActionType.ADD, EventValueType.MONEY, 20);
        nAct.addAction(act);

        events.add(new Event(EventType.TWO_CHOICES,"Create a new scolarship for your student","Yes", "No", yAct, nAct));
        return events;
    }
}
