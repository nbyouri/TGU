package muga.thegreatuniversity.models.events;

import org.json.JSONException;
import org.json.JSONObject;

import muga.thegreatuniversity.lists.enums.AnsType;
import muga.thegreatuniversity.lists.enums.EventType;
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
    private int duration;
    private int count;

    private EventResult yesAction;
    private EventResult noAction;

    private EventConditions conds;
    private AnsType ans;
    private String description;

    private boolean causal;
    private boolean displayable;

    public Event() {
        this.count = 0;
        this.duration = 1;
        this.displayable = true;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDisplayable() {
        return displayable;
    }

    public void setDisplayable(boolean displayable) {
        this.displayable = displayable;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setCount0(){
        this.count=0;
    }

    public void incrementCount(){
        this.count++;
    }

    public int getCount() {
        return count;
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

    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("message", message);
        obj.put("type", type.getName());
        obj.put("duration", duration);
        obj.put("first_choice", firstChoice);
        obj.put("second_choice", secondChoice);
        obj.put("description", description);
        if (yesAction != null)
            obj.put("yes_action", yesAction.getAsJson());
        if (noAction != null)
            obj.put("no_action", noAction.getAsJson());
        if (conds != null)
            obj.put("conditions", conds.getAsJSON());
        obj.put("causal", causal);

        return obj;
    }

    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.message = jsonO.getString("message");
        this.type = EventType.getEnum(jsonO.getString("type"));
        this.duration = jsonO.getInt("duration");
        this.firstChoice = jsonO.getString("first_choice");
        this.secondChoice = jsonO.getString("second_choice");
        if (jsonO.has("description")) {
            this.description = jsonO.getString("description");
        }
        else {
            Logger.info(this.message+ "no description");
        }
        if (jsonO.has("yes_action")) {
            this.yesAction = new EventResult();
            this.yesAction.loadJSON(jsonO.getJSONArray("yes_action"));
        }
        if (jsonO.has("no_action")) {
            this.noAction = new EventResult();
            this.noAction.loadJSON(jsonO.getJSONArray("no_action"));
        }

        if (jsonO.has("conditions")) {
            this.conds = new EventConditions();
            this.conds.loadJSON(jsonO.getJSONObject("conditions"));
        }

        this.causal = jsonO.getBoolean("causal");

        Logger.info(this.toString());
    }

    @Override
    public String toString() {
        return "Event"+
                this.message;
    }

    public boolean isCausal() {
        return causal;
    }
}
