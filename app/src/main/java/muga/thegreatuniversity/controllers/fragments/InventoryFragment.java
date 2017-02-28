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
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.controllers.adapters.InventoryAdapter;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 28/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class InventoryFragment extends ListFragment {
    private InventoryAdapter inventoryAdapter;

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
        ArrayList<Professor> profs = University.getU().getProfessors();

        // Apply this choice on ListView
        inventoryAdapter = new InventoryAdapter(getActivity().getApplicationContext(), profs);
        setListAdapter(inventoryAdapter);
    }

    public void onStart() {
        super.onStart();
    }

    private void hireProf(Professor p) {
        University.getU().addProfessor(p);
    }

    private void printGame() {
        ((MainActivity) getActivity()).updateView();
    }

    private void newEventAlert(String event) {
        PopUp.alertNewEvent((MainActivity) getActivity(), event);
    }
}