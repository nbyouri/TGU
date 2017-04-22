package muga.thegreatuniversity.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
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
                steps.add(new TutorialStep(R.string.tutorial_room,false));
                steps.add(new TutorialStep(R.string.tutorial_roomsize,R.id.txt_size_room));
                steps.add(new TutorialStep(R.string.tutorial_roomprice,R.id.txt_price_room));
                break;
            case CHOICES:
                //steps.add(new TutorialStep("You can choose different actions",R.id.layout_choices));
                break;
            case INVENTORY:
                steps.add(new TutorialStep(R.string.tutorial_inventory,false));
                break;
            case OPTIONS:

                break;
            case STATISTICS:

                break;
            case STAT:
                steps.add(new TutorialStep(R.string.tutorial_welcome, false));
                steps.add(new TutorialStep(R.string.tutorial_first_steps, false));
                steps.add(new TutorialStep(R.string.tutorial_newturn, false));
                steps.add(new TutorialStep(R.string.tutorial_money, R.id.layout_stat_cash));
                steps.add(new TutorialStep(R.string.tutorial_popularity, R.id.layout_stat_popularity));
                steps.add(new TutorialStep(R.string.layout_stat_moral, R.id.layout_stat_moral));
                steps.add(new TutorialStep(R.string.tutorial_population, R.id.layout_stat_student));
                steps.add(new TutorialStep(R.string.tutorial_turn, R.id.layout_stat_turn));
                break;
            case HIRE_PROF:
                steps.add(new TutorialStep(R.string.tutorial_proflist, false));
                steps.add(new TutorialStep(R.string.tutorial_profdetail, R.id.layout_prof_item));
                steps.add(new TutorialStep(R.string.tutorial_profpopularity, R.id.layout_prof_efficient));
                steps.add(new TutorialStep(R.string.tutorial_profprice, R.id.layout_prof_price));
                steps.add(new TutorialStep(R.string.tutorial_profrarity, R.id.icon_prof));
                steps.add(new TutorialStep(R.string.tutorial_help, R.id.img_help_hire));
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

    public void changeStep(FragmentType type, android.content.Context context){
        TutorialStep tutorialStep;
        LinkedList<TutorialStep> steps = tutorials.get(type);
        if (steps != null && !steps.isEmpty()){
            tutorialStep = steps.pop();
            Logger.info("Remove tutorial Step : " + tutorialStep.getMessage());
            if (steps.isEmpty()) {
                try {
                    SaveManager.saveSetting(context);
                } catch (Exception e) {
                    Tools.deleteAndRestart(context, "Failed to save settings : ", e);
                }
            }
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
