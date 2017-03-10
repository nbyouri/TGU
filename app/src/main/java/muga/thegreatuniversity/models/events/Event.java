package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.AnsType;
import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.EventType;
import muga.thegreatuniversity.lists.enums.EventValueType;

/**
 * Created on 28/02/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Event {
    private String message;
    private String firstChoice;
    private String secondChoice;
    private EventType type;

    private EventResult yesAction;
    private EventResult noAction;

    private AnsType ans;

    public Event(EventType type, String message, String firstChoice, String secondChoice,EventResult yesAction,
                 EventResult noAction){
        this.message = message;
        this.firstChoice=firstChoice;
        this.secondChoice=secondChoice;
        this.yesAction =yesAction;
        this.noAction = noAction;
        this.type=type;
    }

    public String getMessage() {
        return message;
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

    public void setMessage(String message) {
        this.message = message;
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

    public EventResult getResult(){
        switch (ans) {
            case YES:
                return yesAction;
            case NO:
                return noAction;
            default:
                return yesAction;
        }
    }

    public AnsType getAns() {
        return ans;
    }

    public void setAns(AnsType ans) {
        this.ans = ans;
    }

    public static ArrayList<Event> genEvent(){
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
