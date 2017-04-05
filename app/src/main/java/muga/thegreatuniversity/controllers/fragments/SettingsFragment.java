package muga.thegreatuniversity.controllers.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.controllers.adapters.ChoicesAdapter;
import muga.thegreatuniversity.lists.enums.ChoiceType;
import muga.thegreatuniversity.models.Choice;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.SaveManager;
import muga.thegreatuniversity.utils.TutorialManager;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class SettingsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice(ChoiceType.RESET_UNIVERSITY, "Destroy your university"));
        choices.add(new Choice(ChoiceType.RESET_TUTORIAL, "Reset the tutorial"));

        ChoicesAdapter choicesAdapter = new ChoicesAdapter(getActivity().getApplicationContext(), choices);
        setListAdapter(choicesAdapter);
        getListView().setOnItemClickListener(this);
    }

    public void onStart(){
        super.onStart();
    }

    private void resetUniversity(){
        PopUp.resetUnivPopUp((MainActivity) getActivity());
    }

    private void resetTutorial(){
        TutorialManager.get().createAllTutorial();
        SaveManager.saveSetting(getActivity().getApplicationContext());
        getFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChoiceType choiceType = ((Choice) parent.getItemAtPosition(position)).getType();
        Logger.info("Click on choice type = " + choiceType);
        switch (choiceType) {
            case RESET_UNIVERSITY:
                resetUniversity();
                break;
            case RESET_TUTORIAL:
                resetTutorial();
                break;
        }
    }
}
