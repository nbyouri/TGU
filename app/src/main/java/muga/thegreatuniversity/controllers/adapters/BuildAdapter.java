package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.models.Room;

/**
 * Created by tristanmoers on 28/02/17.
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
        TextView hireName = (TextView) convertView.findViewById(R.id.txt_room);

        // Populate the data into the template view using the data object
        hireName.setText("Name: "+r.getName()+"\nCapacity: "+r.getCapacity()
                +"\nPrice: "+r.getPrice());

        // Return the completed view to render on screen
        return convertView;
    }
}
