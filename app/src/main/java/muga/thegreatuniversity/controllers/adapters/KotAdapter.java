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
import muga.thegreatuniversity.models.Kot;

/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class KotAdapter extends ArrayAdapter<Kot> {

    public KotAdapter(Context context, List<Kot> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

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
        if (k.getName() != null)
            kotName.setText(k.getName());
        kotPrice.setText(String.valueOf(k.getPrice()));
        kotSize.setText(getContext().getString(R.string.kot_capacity, k.getCapacity()));
        // Return the completed view to render on screen
        return convertView;
    }
}
