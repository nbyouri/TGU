package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.os.Bundle;
import android.transition.Slide;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;


import java.util.ArrayList;
import java.util.Arrays;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.controllers.tutorial.TutorialListFragment;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 28/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class InventoryFragment extends TutorialListFragment implements OnItemClickListener {
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
        getListView().setOnItemClickListener(this);
    }

    public void onStart() {
        super.onStart();
    }

    @Override
    public ImageView getButtonHelp() {
        return null;
    }

    @Override
    public FragmentType getFragmentType() {
        return FragmentType.INVENTORY;
    }

    @Override
    public void onResume() {
        super.onResume();
        hireAdapter = new HireAdapter(getActivity().getApplicationContext(),
                University.get().getProfessors());
        setListAdapter(hireAdapter);
        hireAdapter.notifyDataSetChanged();
        ((MainActivity) getActivity()).updateView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Professor p = ((Professor) parent.getItemAtPosition(position));
        Fragment frag = new ProfFragment();

        Bundle bundle = new Bundle();
        try {
            bundle.putString("prof", p.getAsJSON().toString());
            bundle.putBoolean("hire", false);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
        frag.setArguments(bundle);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        Slide slEnter = new Slide();
        slEnter.setSlideEdge(Gravity.END);
        frag.setEnterTransition(slEnter);

        transaction.replace(R.id.frame_main, frag);
        transaction.addToBackStack(null);
        transaction.hide(this);

        transaction.commit();
    }
}
