package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
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
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.Choice;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created by tristanmoers on 21/03/17.
 */

public class OptionsFragment extends ListFragment implements AdapterView.OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_options, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Choice> choices = new ArrayList<>();
        choices.add(new Choice(ChoiceType.RESET, "Destroy your university"));

        ChoicesAdapter choicesAdapter = new ChoicesAdapter(getActivity().getApplicationContext(), choices);
        setListAdapter(choicesAdapter);
        getListView().setOnItemClickListener(this);
    }

    public void onStart(){
        super.onStart();
    }

    private void reset(){
        PopUp.resetUnivPopUp((MainActivity) getActivity());
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ChoiceType choiceType = ((Choice) parent.getItemAtPosition(position)).getType();
        Logger.info("Click on choice type = " + choiceType);
        switch (choiceType) {
            case RESET:
                reset();
                break;
        }
    }
}
