package muga.thegreatuniversity.models.events;

import java.util.ArrayList;

import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Events {
    private ArrayList<Event> causal;
    private ArrayList<Event> others;

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
