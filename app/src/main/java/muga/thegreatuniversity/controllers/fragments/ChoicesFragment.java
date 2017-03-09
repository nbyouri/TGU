package muga.thegreatuniversity.controllers.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.adapters.ChoicesAdapter;
import muga.thegreatuniversity.lists.ChoiceType;
import muga.thegreatuniversity.lists.FragmentType;
import muga.thegreatuniversity.models.Choice;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class ChoicesFragment extends ListFragment implements OnItemClickListener {

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

        // Apply this choice on ListView
        ChoicesAdapter choicesAdapter = new ChoicesAdapter(getActivity().getApplicationContext(), choices);
        setListAdapter(choicesAdapter);
        getListView().setOnItemClickListener(this);
    }

    public void onStart(){
        super.onStart();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChoiceType choiceType = ((Choice) parent.getItemAtPosition(position)).getType();
        Logger.info("Click on choice type = " + choiceType);
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
        }
    }
}
