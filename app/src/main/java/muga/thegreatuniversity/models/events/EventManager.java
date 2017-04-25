package muga.thegreatuniversity.models.events;


import java.util.ArrayList;

import muga.thegreatuniversity.models.University;

/**
 * Created on 28/02/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventManager {
    public static ArrayList<Event> getEvents() {
        ArrayList<Event> ev = new ArrayList<>();
        // 1 chance out of 5 to get an event
        int random = (int) Math.floor(Math.random() * University.get().getEvents().size() * 5);
        if (random < University.get().getEvents().size()) {
            Event e = University.get().getEvents().getEvent(random);
            if (e.getConds().check())
                ev.add(e);
        }

        // add causal
        ev.addAll(University.get().getEvents().getCausalEvents());
        return ev;
    }
}