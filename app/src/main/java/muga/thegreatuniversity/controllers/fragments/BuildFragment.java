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
import muga.thegreatuniversity.controllers.adapters.BuildAdapter;
import muga.thegreatuniversity.controllers.adapters.HireAdapter;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.Room;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created by tristanmoers on 28/02/17.
 */

public class BuildFragment extends ListFragment implements AdapterView.OnItemClickListener {
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

    public void onStart(){
        super.onStart();
    }

    private void BuildRoom(Room r){
        University.getU().addRoom(r);
    }

    private void printGame(){
        ((MainActivity)getActivity()).updateView();
    }

    private void newEventAlert(String event){
        PopUp.alertNewEvent((MainActivity)getActivity(), event);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Room r = ((Room) parent.getItemAtPosition(position));
        Logger.info("Build "+r.getName());
        BuildRoom(r);
        buildAdapter.remove(r);
        buildAdapter.notifyDataSetChanged();
    }

}
