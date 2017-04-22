package muga.thegreatuniversity.controllers.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.controllers.adapters.KotAdapter;
import muga.thegreatuniversity.controllers.tutorial.TutorialListFragment;
import muga.thegreatuniversity.controllers.adapters.BuildAdapter;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.Kot;
import muga.thegreatuniversity.models.Room;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

import static android.R.id.list;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class BuildFragment extends TutorialListFragment implements AdapterView.OnItemClickListener {
    private BuildAdapter buildAdapter;
    private KotAdapter kotAdapter;

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
        ArrayList<Kot> kots = Kot.getKot();

        // Apply this choice on ListView
        buildAdapter = new BuildAdapter(getActivity().getApplicationContext(), rooms);
        setListAdapter(buildAdapter);
        getListView().setOnItemClickListener(this);
       // ListView listRoom = (ListView) getActivity().findViewById(R.id.@android_id/list);
       // listRoom.setAdapter(buildAdapter);

       kotAdapter = new KotAdapter(getActivity().getApplicationContext(), kots);
        ListView listKot = (ListView) getActivity().findViewById(R.id.list_kot);
        listKot.setAdapter(kotAdapter);
        listKot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Kot k = ((Kot) parent.getItemAtPosition(position));
                Logger.info("Build "+k.getName());
                BuildKot(k);
                kotAdapter.notifyDataSetChanged();

            }
        });
    }

    private void BuildKot(Kot k){
        int n = University.get().getMoney() - k.getPrice();
        if(n < 0) {
            PopUp.notMoney(getActivity());
        } else {
            University.get().addKot(k);
            University.get().setMoney(n);
            ((MainActivity)getActivity()).passOneWeek();
        }
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
        BuildRoom(r);
        buildAdapter.notifyDataSetChanged();

    }

    @Override
    public View getButtonHelp() {
        return (getActivity().findViewById(R.id.room_help));
    }

    @Override
    public FragmentType getFragmentType() {
        return FragmentType.BUILD_ROOM;
    }
}
