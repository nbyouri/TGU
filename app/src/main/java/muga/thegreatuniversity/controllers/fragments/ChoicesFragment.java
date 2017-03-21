package muga.thegreatuniversity.controllers.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;
import java.util.logging.Handler;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.app.App;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.adapters.ChoicesAdapter;
import muga.thegreatuniversity.lists.enums.ChoiceType;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.Choice;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.SaveManager;
import muga.thegreatuniversity.utils.Tools;
import muga.thegreatuniversity.utils.TutorialManager;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class ChoicesFragment extends ListFragment implements OnItemClickListener {

    private ChoicesAdapter choicesAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choices, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create different choice for user
        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice(ChoiceType.HIRE_PROF, "Hire a professor"));
        choices.add(new Choice(ChoiceType.INVENTORY, "List of professors"));
        choices.add(new Choice(ChoiceType.BUILD_ROOM, "Build room"));
        choices.add(new Choice(ChoiceType.STATISTICS, "Statistics"));
        choices.add(new Choice(ChoiceType.PASS_WEEK, "Pass a week"));
        choices.add(new Choice(ChoiceType.OPTIONS, "Options"));

        // Apply this choice on ListView
        choicesAdapter = new ChoicesAdapter(getActivity().getApplicationContext(), choices);
        setListAdapter(choicesAdapter);
        getListView().setOnItemClickListener(this);

    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();


        getListView().addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                final android.os.Handler handlerLoop = new android.os.Handler();
                handlerLoop.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //tuto();
                    }
                },10);
            }
        });

    }

    public void tuto(){
        View toClick = Tools.getViewByPosition(0,getListView());
        TutorialManager.get().printCurrentTourGuide(getActivity(), toClick);
    }

    private void hireProf(){
        ((MainActivity)getActivity()).changeMainFragment(FragmentType.HIRE_PROF);
    }

    private void inventory() {
        ((MainActivity)getActivity()).changeMainFragment(FragmentType.INVENTORY);
    }

    private void build() {
        ((MainActivity)getActivity()).changeMainFragment(FragmentType.BUILD_ROOM);
    }

    private void stats() {
        ((MainActivity)getActivity()).changeMainFragment(FragmentType.STATISTICS);
    }

    private void options() {
        ((MainActivity)getActivity()).changeMainFragment(FragmentType.OPTIONS);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChoiceType choiceType = ((Choice) parent.getItemAtPosition(position)).getType();
        Logger.info("Click on choice type = " + choiceType);

        TutorialManager.get().removeTour();

        switch (choiceType){
            case HIRE_PROF:
                hireProf();
                break;
            case PASS_WEEK:
                ((MainActivity)getActivity()).passOneWeek();
                break;
            case INVENTORY:
                inventory();
                break;
            case BUILD_ROOM:
                build();
                break;
            case STATISTICS:
                stats();
                break;
            case OPTIONS:
                options();
                break;
        }
    }
}
