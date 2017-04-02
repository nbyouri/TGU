package muga.thegreatuniversity.models;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialStep {

    private String message;
    private int idView;
    private boolean hasView;

    public TutorialStep(String message, int idView) {
        this.message = message;
        this.idView = idView;
        this.hasView = true;
    }

    public TutorialStep(String message, boolean hasView){
        this.message = message;
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
