package muga.thegreatuniversity.models;

/**
 * Created on 22-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Turn {

    public int week;
    public int newStudent;
    public double newMoral;
    public int newCash;
    public int resultTurn;

    public Turn(int week) {
        this.week = week;
        this.resultTurn = 0;
    }

}
