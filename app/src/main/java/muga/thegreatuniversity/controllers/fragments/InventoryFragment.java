package muga.thegreatuniversity.controllers.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.University;

/**
 * Created on 28/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class InventoryFragment extends ListFragment {
    private HireAdapter hireAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_inventory, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create different choice for user
        ArrayList<Professor> profs = University.get().getProfessors();
        // Apply this choice on ListView
        hireAdapter = new HireAdapter(getActivity().getApplicationContext(), profs);
        setListAdapter(hireAdapter);
    }

    public void onStart() {
        super.onStart();
    }

    private void hireProf(Professor p) {
        University.get().addProfessor(p);
    }

    private void printGame() {
        ((MainActivity) getActivity()).updateView();
    }

}
