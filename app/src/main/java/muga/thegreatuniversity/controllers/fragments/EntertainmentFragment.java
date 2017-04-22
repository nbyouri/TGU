package muga.thegreatuniversity.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.controllers.adapters.EntertainmentAdapter;
import muga.thegreatuniversity.controllers.tutorial.TutorialListFragment;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.Entertainment;
import muga.thegreatuniversity.models.University;

/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EntertainmentFragment extends TutorialListFragment implements AdapterView.OnItemClickListener{
    private EntertainmentAdapter entertainmentAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_entertainment, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create different choice for user
        ArrayList<Entertainment> entertainments = Entertainment.getEntertainments();

        // Apply this choice on ListView
        entertainmentAdapter = new EntertainmentAdapter(getActivity().getApplicationContext(), entertainments);
        setListAdapter(entertainmentAdapter);
        getListView().setOnItemClickListener(this);

    }

    private void BuyEntertainemnt(Entertainment e){
        int n = University.get().getMoney() - e.getPrice();
        if(n < 0) {
            PopUp.notMoney(getActivity());
        } else {
            University.get().addEntertainments(e);
            University.get().setMoney(n);
            University.get().addMoral(e.getMoral());
            ((MainActivity)getActivity()).passOneWeek();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Entertainment e = ((Entertainment) parent.getItemAtPosition(position));
        BuyEntertainemnt(e);
        entertainmentAdapter.notifyDataSetChanged();

    }

    @Override
    public View getButtonHelp() {
        return (getActivity().findViewById(R.id.room_help));
    }

    @Override
    public FragmentType getFragmentType() {
        return FragmentType.ENTERTAINMENT;
    }
}

