package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.events.Event;

/**
 * Created on 18/04/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventAdapter extends ArrayAdapter<Event> {

//    private boolean detail;

//    public EventAdapter(Context context, List<Event> objects, boolean detail) {
//        super(context, 0, objects);
//        this.detail = true;
//    }

    public EventAdapter(Context context, List<Event> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        Event ev = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }

        // Lookup view for data population
        TextView description = (TextView) convertView.findViewById(R.id.event_description);
        TextView duration = (TextView) convertView.findViewById(R.id.event_duration);

        // Populate the data into the template view using the data object
        if (ev != null) {
            description.setText(ev.getMessage());
            duration.setText(getContext().getString(R.string.event_duration, ev.getDuration()));
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
