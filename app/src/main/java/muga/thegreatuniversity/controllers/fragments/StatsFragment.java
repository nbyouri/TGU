package muga.thegreatuniversity.controllers.fragments;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.adapters.StatsAdapter;

/**
 * Created by tristanmoers on 7/03/17.
 */

public class StatsFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private StatsAdapter statsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_room, container, false);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
