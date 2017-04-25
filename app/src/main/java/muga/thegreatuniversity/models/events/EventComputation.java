package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.EventValueType;
import muga.thegreatuniversity.utils.Tuple;

/**
 * Created on 22-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventComputation {

    ArrayList<Tuple<EventValueType, Object>> newValues;

    public EventComputation(){
        newValues = new ArrayList<>();
    }

    public void add(EventValueType type, Object value){
        newValues.add(new Tuple<>(type, value));
    }

    public ArrayList<Tuple<EventValueType, Object>> getNewValues(){
        return newValues;
    }

}
