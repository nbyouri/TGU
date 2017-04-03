package muga.thegreatuniversity.utils;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.TutorialStep;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialManager {

    Map<FragmentType, LinkedList<TutorialStep>> tutorials;

    private static Map<FragmentType, LinkedList<TutorialStep>> createTutorial() {
        Map<FragmentType, LinkedList<TutorialStep>> tutorials = new EnumMap<FragmentType, LinkedList<TutorialStep>>(FragmentType.class);

        LinkedList<TutorialStep> stats = new LinkedList<TutorialStep>();
        LinkedList<TutorialStep> hire = new LinkedList<TutorialStep>();
        LinkedList<TutorialStep> choices = new LinkedList<TutorialStep>();

        stats.add(new TutorialStep("You stat here",R.id.layout_stat));
        stats.add(new TutorialStep("You can see you money here, you earn 5 UCash per student",R.id.layout_stat_cash));
        stats.add(new TutorialStep("The popularity improve the number of student who show up each week",R.id.layout_stat_popularity));

        hire.add(new TutorialStep("Professor are generate randomly each week ", false));
        hire.add(new TutorialStep("You can preview each professor", R.id.layout_prof_item));

        choices.add(new TutorialStep("You can choose different action",R.id.layout_choices));

        //
        tutorials.put(FragmentType.HIRE_PROF, hire);
        tutorials.put(FragmentType.STAT, stats);
        tutorials.put(FragmentType.CHOICES, choices);

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
        tutorials = createTutorial();
    }

    private static class TutorialManagerHolder {
        private final static TutorialManager instance = new TutorialManager();
    }

    public static TutorialManager get() {
        return TutorialManager.TutorialManagerHolder.instance;
    }

}
