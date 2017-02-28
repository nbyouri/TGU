package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
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
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.lists.ChoiceType;
import muga.thegreatuniversity.lists.FragmentType;
import muga.thegreatuniversity.models.Choice;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 28/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class HireFragment extends ListFragment implements OnItemClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hire, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create different choice for user
        ArrayList<Professor> profs = new ArrayList<>();
        profs.add(new Professor("Schauss", 1, null, 42));
        profs.add(new Professor("Deville", 8, null, 42));
        profs.add(new Professor("Mens", 9, null, 42));
        profs.add(new Professor("Bonaventure", 7, null, 42));
        profs.add(new Professor("Pecheur", 8, null, 42));

        // Apply this choice on ListView
        HireAdapter hireAdapter = new HireAdapter(getActivity().getApplicationContext(), profs);
        setListAdapter(hireAdapter);
        getListView().setOnItemClickListener(this);
    }

    public void onStart(){
        super.onStart();
    }

    private void hireProf(Professor p){
        //((MainActivity)getActivity()).changeMainFragment(FragmentType.HIRE_PROF);
        University.getU().addProfessor(p);
    }

    private void printGame(){
        ((MainActivity)getActivity()).updateView();
    }

    private void newEventAlert(String event){
        PopUp.alertNewEvent((MainActivity)getActivity(), event);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Professor p = ((Professor) parent.getItemAtPosition(position));
        Logger.info("Hired "+p.getName());
        hireProf(p);
    }
}
