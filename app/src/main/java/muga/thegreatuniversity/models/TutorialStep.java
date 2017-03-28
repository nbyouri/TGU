package muga.thegreatuniversity.models;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialStep {

    public TutorialStep(String message, int idView) {
        this.message = message;
        this.idView = idView;
    }

    public String getMessage() {
        return message;
    }

    public int getIdView() {
        return idView;
    }

    private String message;
    private int idView;


}
