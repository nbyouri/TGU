package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

/**
 * Created on 10-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventResult {

    private ArrayList<EventAction> actions;

    EventResult(){
        actions = new ArrayList<EventAction>();
    }

    public void addAction(EventAction act){
        actions.add(act);
    }

    public ArrayList<EventAction> getActions(){
        return actions;
    }

}
