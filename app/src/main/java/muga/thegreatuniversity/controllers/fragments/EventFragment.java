package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
import android.app.usage.UsageEvents;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.adapters.EntertainmentAdapter;
import muga.thegreatuniversity.models.Entertainment;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 22/04/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventFragment extends Fragment {
    private Event ev;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_description, container, false);

        String json = getArguments().getString("event");

        ev = new Event();
        try {
            ev.loadJSON(new JSONObject(json));
        } catch (JSONException e) {
            Logger.error("Failed to loadEvent professor" + e.getMessage());

        }
        TextView textView_desc = (TextView) view.findViewById(R.id.layout_event_description_textview);
        textView_desc.setText(ev.getDescription());


        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

}
