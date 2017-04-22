package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.Room;

/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class BuildAdapter extends ArrayAdapter<Room> {

    public BuildAdapter(Context context, List<Room> objects) {
        super(context, 0, objects);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Room r = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_room, parent, false);
        }

        // Lookup view for data population
        TextView roomName = (TextView) convertView.findViewById(R.id.txt_name_room);
        TextView roomPrice = (TextView) convertView.findViewById(R.id.txt_price_room);
        TextView roomSize = (TextView) convertView.findViewById(R.id.txt_size_room);


        // Populate the data into the template view using the data object
        roomName.setText(r.getName());
        roomPrice.setText(String.valueOf(r.getPrice()));
        roomSize.setText(getContext().getString(R.string.rooms_cap, r.getCapacity()));
        // Return the completed view to render on screen
        return convertView;
    }
}
