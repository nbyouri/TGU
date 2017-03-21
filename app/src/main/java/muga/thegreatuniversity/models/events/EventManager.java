package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.EventType;
import muga.thegreatuniversity.lists.enums.EventValueType;
import muga.thegreatuniversity.utils.Logger;

import static muga.thegreatuniversity.lists.enums.EventValueType.MONEY;

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
        // TODO random from events
        //int random = (int) Math.floor(Math.random()*this.sizeEvents()*3); //1 chance out of 5 to get an event
        //if (random < this.sizeEvents())
        Event firstevent = getEvent(0);
        if (firstevent.getConds().check())
            return firstevent;
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

        EventConditions conds = null;
        /*!!!!!!!!!!!!! Event 24h Velo !!!!!!!!!!!!!!!!!!!!!!!!!! */
        try {
            conds = new EventConditions(new String[]{"money"},
                            new String[]{"<="}, new int[]{3000});
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }

        EventAction act = new EventAction(EventActionType.MULTIPLICATION, MONEY, 0.7);
        yAct.addAction(act);
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.POPULARITY, 1.2);
        yAct.addAction(act);

        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.POPULARITY, -0.6);
        nAct.addAction(act);

        events.add(new Event(EventType.TWO_CHOICES, "Do you want to organize the 24H velo", "Yes", "No", yAct, nAct, conds));

        /* !!!!!!!!!!!!! Event you lost half your money !!!!!!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, MONEY, 0.8);
        yAct.addAction(act);

        events.add(new Event(EventType.DETERMINIST,"You got robbed by a student, you lost some of your money", null, null, yAct, null, null));

        /* !!!!!!!!!!!!!!! Event finish thesis !!!!!!!!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.POPULARITY, 1.2);
        yAct.addAction(act);
        act = new EventAction(EventActionType.MULTIPLICATION, MONEY, 1.3);
        yAct.addAction(act);

        events.add(new Event(EventType.DETERMINIST,"One of your teacher finished his thesis, you gain money and popularity", null, null, yAct, null, null));

        /* !!!!!!!!!!!!!! Event you loose a student*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.STUDENT, 0.8);
        yAct.addAction(act);

        events.add(new Event(EventType.DETERMINIST,"You lost students because your university suck", null, null, yAct, null, null));

        /* !!!!!!!!!!! Event Erasmus !!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.STUDENT, 1.3);
        yAct.addAction(act);
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.POPULARITY, 1.1);
        yAct.addAction(act);
        act = new EventAction(EventActionType.MULTIPLICATION, MONEY, 0.7);
        yAct.addAction(act);

        nAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.POPULARITY, 0.8);
        nAct.addAction(act);

        events.add(new Event(EventType.TWO_CHOICES, "Set a new Erasmus partnership in a new country", "Yes", "No", yAct, nAct, null));

        /*!!!!!!!!!!! Event bourse d'étude !!!!!!!!!!!!!!!!*/
        yAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, MONEY, 0.7);
        yAct.addAction(act);
        act = new EventAction(EventActionType.MULTIPLICATION, EventValueType.POPULARITY, 1.2);
        yAct.addAction(act);

        nAct = new EventResult();
        act = new EventAction(EventActionType.MULTIPLICATION, MONEY, 1.1);
        nAct.addAction(act);

        events.add(new Event(EventType.TWO_CHOICES,"Create a new scolarship for your student","Yes", "No", yAct, nAct, null));
        return events;
    }
}

