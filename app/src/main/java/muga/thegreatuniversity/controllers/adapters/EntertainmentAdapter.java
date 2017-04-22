package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.Entertainment;
import muga.thegreatuniversity.models.Room;

/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EntertainmentAdapter extends ArrayAdapter<Entertainment> {

    public EntertainmentAdapter(Context context, List<Entertainment> objects) {
        super(context, 0, objects);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Entertainment e = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_entertainment, parent, false);
        }

        // Lookup view for data population
        TextView entertainmentName = (TextView) convertView.findViewById(R.id.txt_name_entertainment);
        TextView entertainnmentPrice = (TextView) convertView.findViewById(R.id.txt_price_entertainment);


        // Populate the data into the template view using the data object
        entertainmentName.setText(e.getName());
        entertainnmentPrice.setText(String.valueOf(e.getPrice()));
        // Return the completed view to render on screen
        return convertView;
    }

}
