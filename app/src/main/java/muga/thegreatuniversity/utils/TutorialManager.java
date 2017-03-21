package muga.thegreatuniversity.utils;

import android.app.Activity;
import android.view.View;

import muga.thegreatuniversity.models.University;
import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialManager {

    private TutorialManager(){

    }

    private static class TutorialManagerHolder {
        private final static TutorialManager instance = new TutorialManager();
    }

    public static TutorialManager get() {
        return TutorialManager.TutorialManagerHolder.instance;
    }

    private TourGuide currentTourGuide;

    public void printCurrentTourGuide(Activity activity, View view){
        currentTourGuide = TourGuide.init(activity).with(TourGuide.Technique.Click)
                .setPointer(new Pointer())
                .setToolTip(new ToolTip().setTitle("Welcome!").setDescription("Click on Get Started to begin..."))
                .setOverlay(new Overlay());

        currentTourGuide.playOn(view);
    }

    public void removeTour(){
        if (currentTourGuide != null ){
            currentTourGuide.cleanUp();
            currentTourGuide = null;
        }
    }



}
