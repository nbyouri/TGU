package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Events {
    private final ArrayList<Event> causal;
    private final ArrayList<Event> others;

    public Events(ArrayList<Event> causal, ArrayList<Event> others) {
        this.causal = causal;
        this.others = others;
    }

    public int size() {
        return others.size();
    }

    public Event getEvent(int i) {
        return others.get(i);
    }

    public ArrayList<Event> getCausalEvents() {
        ArrayList<Event> ev = new ArrayList<>();
        for (int i = 0; i < causal.size(); i++) {
            if (causal.get(i).getConds().check()) {
                ev.add(causal.get(i));
            }
        }
        return ev;
    }
}
