package muga.thegreatuniversity.models;

import java.util.ArrayList;

import muga.thegreatuniversity.models.events.Event;

/**
 * Created on 22-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Turn {

    public final int week;
    public int newStudent;
    public double newMoral;
    public int newCash;
    public int resultTurn;
    public ArrayList<Event> events;

    Turn(int week) {
        this.week = week;
        this.resultTurn = 0;
    }

}
