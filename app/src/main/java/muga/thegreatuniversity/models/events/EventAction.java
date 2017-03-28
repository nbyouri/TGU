package muga.thegreatuniversity.models.events;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.EventValueType;
import muga.thegreatuniversity.models.SavableLoadableJSON;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 10-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventAction {

    private double value;
    private EventActionType actionType;
    private EventValueType valueType;

    public EventAction() {}

    public EventAction(EventActionType actionType, EventValueType valueType, double value) {
        this.value = value;
        this.actionType = actionType;
        this.valueType = valueType;
    }

    public double getValue() {
        return value;
    }

    public EventActionType getActionType() {
        return actionType;
    }

    public EventValueType getValueType() {
        return valueType;
    }

    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.value = jsonO.getDouble("value");
        this.actionType = EventActionType.getEnum(jsonO.getString("action_type"));
        this.valueType = EventValueType.getEnum(jsonO.getString("value_type"));

        Logger.info(this.toString());
    }

    @Override
    public String toString() {
        return "EventAction{" +
                "value=" + value +
                ", actionType=" + actionType +
                ", valueType=" + valueType +
                '}';
    }
}
