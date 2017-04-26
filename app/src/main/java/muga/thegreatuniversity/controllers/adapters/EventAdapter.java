package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.EventItem;
import muga.thegreatuniversity.models.events.Event;

/**
 * Created on 18/04/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventAdapter extends ArrayAdapter<Event>  {

    private final boolean inPopup;

    public EventAdapter(Context context, List<Event> objects, boolean inPopup) {
        super(context, 0, objects);
        this.inPopup = inPopup;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        final Event ev = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
            if (ev == null) return convertView;
            EventItem eventItem = new EventItem(convertView, ev,inPopup);
            eventItem.create();
        }

        // Return the completed view to render on screen
        return convertView;
    }

}
