package muga.thegreatuniversity.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.controllers.tutorial.TutorialListFragment;
import muga.thegreatuniversity.controllers.adapters.BuildAdapter;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.Room;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class BuildFragment extends TutorialListFragment implements AdapterView.OnItemClickListener {
    private BuildAdapter buildAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create different choice for user
        ArrayList<Room> rooms = Room.getRooms();

        // Apply this choice on ListView
        buildAdapter = new BuildAdapter(getActivity().getApplicationContext(), rooms);
        setListAdapter(buildAdapter);
        getListView().setOnItemClickListener(this);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    private void BuildRoom(Room r){
        int n = University.get().getMoney() - r.getPrice();
        if(n < 0) {
            PopUp.notMoney(getActivity());
        } else {
            University.get().addRoom(r);
            University.get().setMoney(n);
            ((MainActivity)getActivity()).passOneWeek();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Room r = ((Room) parent.getItemAtPosition(position));
        Logger.info("Build "+r.getName());
        BuildRoom(r);
        buildAdapter.notifyDataSetChanged();

    }

    @Override
    public ImageView getButtonHelp() {
        return ((ImageView) getActivity().findViewById(R.id.img_help_room));
    }

    @Override
    public FragmentType getFragmentType() {
        return FragmentType.BUILD_ROOM;
    }
}
