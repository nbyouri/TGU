package muga.thegreatuniversity.models.events;

import muga.thegreatuniversity.lists.enums.EventActionType;
import muga.thegreatuniversity.lists.enums.EventValueType;

/**
 * Created on 10-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventAction {

    private double value;
    private EventActionType actionType;
    private EventValueType valueType;

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
}
