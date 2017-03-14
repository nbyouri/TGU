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
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 28/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class HireFragment extends ListFragment implements OnItemClickListener {
    private HireAdapter hireAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hire, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Apply this choice on ListView
        hireAdapter = new HireAdapter(getActivity().getApplicationContext(),
                University.get().getAvailableHires());
        setListAdapter(hireAdapter);
        getListView().setOnItemClickListener(this);
    }

    public void onStart(){
        super.onStart();
    }

    private void hireProf(Professor p){
        University.get().addProfessor(p);
    }

    private void printGame(){
        ((MainActivity)getActivity()).updateView();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Professor p = ((Professor) parent.getItemAtPosition(position));
        Logger.info("Hired "+p.getName());
        hireProf(p);
        hireAdapter.remove(p);
        hireAdapter.notifyDataSetChanged();
    }
}
