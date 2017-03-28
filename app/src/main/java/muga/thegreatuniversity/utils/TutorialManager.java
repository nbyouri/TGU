package muga.thegreatuniversity.utils;

import android.app.Activity;
import android.view.View;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.TutorialStep;
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

    Map<FragmentType, LinkedList<TutorialStep>> tutorials;

    private static Map<FragmentType, LinkedList<TutorialStep>>  createTuto() {
        Map<FragmentType, LinkedList<TutorialStep>> tutorials = new EnumMap<FragmentType, LinkedList<TutorialStep>>(FragmentType.class);

        LinkedList<TutorialStep> stats = new LinkedList<TutorialStep>();

        stats.add(new TutorialStep("You can see you money and popularity here",R.id.layout_stat_cash));
        stats.add(new TutorialStep("You stat here",R.id.layout_stat));
        //
        // tutorials.put(FragmentType.STAT, stats);

        return tutorials;
    }

    public TutorialStep currentStep(FragmentType type){
        TutorialStep tutorialStep = null;
        LinkedList<TutorialStep> steps = tutorials.get(type);
        if (steps != null && !steps.isEmpty()){
            tutorialStep = steps.peek();
            Logger.info("TUTO STEP : " + tutorialStep.getMessage());
        }
        return tutorialStep;
    }

    public void changeStep(FragmentType type){
        TutorialStep tutorialStep = null;
        LinkedList<TutorialStep> steps = tutorials.get(type);
        if (steps != null && !steps.isEmpty()){
            tutorialStep = steps.pop();
            Logger.info("Remove tuto STEP : " + tutorialStep.getMessage());
        }
    }

    private TutorialManager(){
        tutorials = createTuto();
    }

    private static class TutorialManagerHolder {
        private final static TutorialManager instance = new TutorialManager();
    }

    public static TutorialManager get() {
        return TutorialManager.TutorialManagerHolder.instance;
    }

    /*
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
    } */

}
