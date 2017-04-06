package muga.thegreatuniversity.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.SavableLoadableJSON;
import muga.thegreatuniversity.models.TutorialStep;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialManager implements SavableLoadableJSON {

    private Map<FragmentType, LinkedList<TutorialStep>> tutorials;

    public void createAllTutorial() {
        tutorials = new EnumMap<>(FragmentType.class);

        for (FragmentType fType : FragmentType.values()){
            tutorials.put(fType,tutorialSteps(fType));
        }

    }

    private static LinkedList<TutorialStep> tutorialSteps(FragmentType fragmentType){
        LinkedList<TutorialStep> steps = new LinkedList<>();

        switch (fragmentType){
            case BUILD_ROOM:
                steps.add(new TutorialStep("Room must be build to improve the maximum of population. Each construction pass a week.",false));
                steps.add(new TutorialStep("Capacity means the number of population that this build can offer",R.id.txt_size_room));
                steps.add(new TutorialStep("Price of the building (one time, no needed maintenance).",R.id.txt_price_room));
                break;
            case CHOICES:
                steps.add(new TutorialStep("You can choose different actions",R.id.layout_choices));
                break;
            case INVENTORY:
                steps.add(new TutorialStep("You can see all your professor here (and fire if you are in trouble).",false));
                break;
            case OPTIONS:

                break;
            case STATISTICS:

                break;
            case STAT:
                steps.add(new TutorialStep("Welcome in The Great University, the goal is become the greater university", false));
                steps.add(new TutorialStep("This game is a management game of University. You have a lot of parameters to manage and become the most popular University", false));
                steps.add(new TutorialStep("Can you begin ?", false));
                steps.add(new TutorialStep("First of all, you can see you money here, you earn 5 UCash per student",R.id.layout_stat_cash));
                steps.add(new TutorialStep("The popularity improve the number of student who show up each week",R.id.layout_stat_popularity));
                steps.add(new TutorialStep("The population is indicate here, the maximum can grow up when you buy building",R.id.layout_stat_student));
                steps.add(new TutorialStep("Week increase after some action and can trigger some random event. Event can be determinist or not.",R.id.layout_stat_turn));
                break;
            case HIRE_PROF:
                steps.add(new TutorialStep("Five professor are generate randomly each week", false));
                steps.add(new TutorialStep("You can preview each professor in the list", R.id.layout_prof_item));
                steps.add(new TutorialStep("Here is efficient of professor. More is efficient more you gain popularity", R.id.layout_prof_efficient));
                steps.add(new TutorialStep("But each professor has a price per week", R.id.layout_prof_price));
                steps.add(new TutorialStep("The color of icon show the rarity of professor.", R.id.icon_prof));

                break;
            case PROF_DETAIL:

                break;
        }

        return steps;
    }

    public TutorialStep currentStep(FragmentType type){
        TutorialStep tutorialStep = null;
        LinkedList<TutorialStep> steps = tutorials.get(type);
        if (steps != null && !steps.isEmpty()){
            tutorialStep = steps.peek();
            Logger.info("Current Tutorial Step : " + tutorialStep.getMessage());
        }
        return tutorialStep;
    }

    public void changeStep(FragmentType type, Context context){
        TutorialStep tutorialStep;
        LinkedList<TutorialStep> steps = tutorials.get(type);
        if (steps != null && !steps.isEmpty()){
            tutorialStep = steps.pop();
            Logger.info("Remove tutorial Step : " + tutorialStep.getMessage());
            if (steps.isEmpty()) SaveManager.saveSetting(context);
        }
    }

    private TutorialManager(){
        tutorials = new EnumMap<>(FragmentType.class);
    }

    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject tutorial = new JSONObject();

        // Is tutorial for one fragment is done isEmpty = true.
        for (FragmentType fType : FragmentType.values()){
            tutorial.put(fType.toString(), (tutorials.get(fType) == null || tutorials.get(fType).isEmpty()));
        }

        return tutorial;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {

        for (FragmentType fType : FragmentType.values()){
            boolean rep = false;
            try {
                rep = jsonO.getBoolean(fType.toString());

            } catch (JSONException exe) {
                Logger.error("Load Tutorial : Can find this type, considering like false");
            }
            if (!rep){
                tutorials.put(fType,tutorialSteps(fType));
            }
        }

    }

    private static class TutorialManagerHolder {
        private final static TutorialManager instance = new TutorialManager();
    }

    public static TutorialManager get() {
        return TutorialManager.TutorialManagerHolder.instance;
    }

}
