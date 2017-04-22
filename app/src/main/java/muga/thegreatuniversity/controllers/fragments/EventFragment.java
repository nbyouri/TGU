package muga.thegreatuniversity.controllers.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;


import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.models.events.EventAction;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 22/04/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstance) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_event_description, container, false);

        String json = getArguments().getString("event");

        Event ev = new Event();
        try {
            ev.loadJSON(new JSONObject(json));
        } catch (JSONException e) {
            Logger.error("Failed to loadEvent professor" + e.getMessage());

        }
        TextView textView_desc = (TextView) view.findViewById(R.id.layout_event_description_textview);
        textView_desc.setText(ev.getDescription());

        TextView textView_firstchoice = (TextView) view.findViewById(R.id.layout_event_description_yesactionTitle);
        textView_firstchoice.setText(ev.getFirstChoice());

        TextView textView_yes_action = (TextView) view.findViewById(R.id.layout_event_description_yesactionDescription);
        textView_yes_action.setText(R.string.event_action_does);
        for(EventAction event: ev.getYesAction().getActions()) {
            textView_yes_action.append(getString(R.string.event_a));
            textView_yes_action.append(event.getActionType().getName());
            textView_yes_action.append(getString(R.string.event_on));
            textView_yes_action.append(event.getValueType().getName());
            textView_yes_action.append(getString(R.string.event_of));
            textView_yes_action.append(String.valueOf(event.getValue()));
            textView_yes_action.append(getString(R.string.event_newline));
        }

        TextView textView_secondchoice = (TextView) view.findViewById(R.id.layout_event_description_noactionDescription);
        textView_secondchoice.setText(ev.getSecondChoice());

        TextView textView_no_action = (TextView) view.findViewById(R.id.layout_event_description_noactiontext);
        textView_no_action.setText(R.string.event_action_does);
        for(EventAction event: ev.getNoAction().getActions()) {
            textView_no_action.append(getString(R.string.event_a));
            textView_no_action.append(event.getActionType().getName());
            textView_no_action.append(getString(R.string.event_on));
            textView_no_action.append(event.getValueType().getName());
            textView_no_action.append(getString(R.string.event_of));
            textView_no_action.append(String.valueOf(event.getValue()));
            textView_no_action.append(getString(R.string.event_newline));
        }


        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
    }

}
