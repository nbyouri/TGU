package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.Kot;

/**
 * Created by tristanmoers on 18/04/17.
 */

public class KotAdapter extends ArrayAdapter<Kot> {

    public KotAdapter(Context context, List<Kot> objects) {
        super(context, 0, objects);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Kot k = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_kot, parent, false);
        }

        // Lookup view for data population
        TextView kotName = (TextView) convertView.findViewById(R.id.txt_name_kot);
        TextView kotPrice = (TextView) convertView.findViewById(R.id.txt_price_kot);
        TextView kotSize = (TextView) convertView.findViewById(R.id.txt_size_kot);


        // Populate the data into the template view using the data object
        kotName.setText(k.getName());
        kotPrice.setText(String.valueOf(k.getPrice()));
        kotSize.setText("Capacity: "+ k.getCapacity());
        // Return the completed view to render on screen
        return convertView;
    }
}
