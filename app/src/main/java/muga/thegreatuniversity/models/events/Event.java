package muga.thegreatuniversity.models.events;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import muga.thegreatuniversity.lists.enums.AnsType;
import muga.thegreatuniversity.lists.enums.EventType;
import muga.thegreatuniversity.models.SavableLoadableJSON;
import muga.thegreatuniversity.utils.Logger;

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

    private EventConditions conds;
    private AnsType ans;

    private boolean causal;

    public Event() {}

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

    public void setConds(EventConditions conds) {
        this.conds = conds;
    }

    public EventConditions getConds() {
        return conds;
    }

    public void loadJSON(JSONObject jsonO) throws Exception {
        this.message = jsonO.getString("message");
        this.type = EventType.getEnum(jsonO.getString("type"));
        this.firstChoice = jsonO.getString("first_choice");
        this.secondChoice = jsonO.getString("second_choice");

        this.yesAction = new EventResult();
        this.yesAction.loadJSON(jsonO.getJSONArray("yes_action"));
        this.noAction = new EventResult();
        this.noAction.loadJSON(jsonO.getJSONArray("no_action"));

        this.conds = new EventConditions();
        this.conds.loadJSON(jsonO.getJSONObject("conditions"));

        this.causal = jsonO.getBoolean("causal");
    }

    public boolean isCausal() {
        return causal;
    }
}
