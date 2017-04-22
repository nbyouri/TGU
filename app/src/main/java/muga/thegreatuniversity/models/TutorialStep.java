package muga.thegreatuniversity.models;

import muga.thegreatuniversity.utils.Context;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialStep {

    private final String message;
    private int idView;
    private final boolean hasView;

    public TutorialStep(int message, int idView) {
        this.message = Context.getString(message);
        this.idView = idView;
        this.hasView = true;
    }

    public TutorialStep(int message, boolean hasView){
        this.message = Context.getString(message);
        this.hasView = hasView;
    }

    public String getMessage() {
        return message;
    }

    public int getIdView() {
        return idView;
    }

    public boolean hasView(){
        return hasView;
    }

}
