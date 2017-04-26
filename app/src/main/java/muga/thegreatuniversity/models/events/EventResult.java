package muga.thegreatuniversity.models.events;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 10-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventResult {

    private final ArrayList<EventAction> actions;
    private EventComputation computation;

    EventResult(){
        actions = new ArrayList<>();
    }

    public ArrayList<EventAction> getActions(){
        return actions;
    }

    public JSONArray getAsJson() throws JSONException {
        JSONArray jsonAction = new JSONArray();

        for(EventAction ac: actions) {
            if (ac != null)
                jsonAction.put(ac.getAsJSON());
        }
        return jsonAction;
    }

    public void loadJSON(JSONArray json) throws JSONException {
        for (int i = 0; i < json.length(); i++) {
            EventAction ea = new EventAction();
            ea.loadJSON(json.getJSONObject(i));
            actions.add(ea);
        }
        Logger.info(this.toString());
    }

    @Override
    public String toString() {
        return "EventResult{" +
                "actions=" + actions +
                '}';
    }

    public EventComputation getComputation() {
        return computation;
    }

    public void setComputation(EventComputation computation) {
        this.computation = computation;
    }
}
