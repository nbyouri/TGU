package muga.thegreatuniversity.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.Choice;

/**
 * Created on 27-02-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class ChoicesAdapter extends ArrayAdapter<Choice> {

    public ChoicesAdapter(Context context, List<Choice> objects) {
        super(context, 0, objects);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Choice choice = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_choice, parent, false);
        }

        // Lookup view for data population
        TextView choiceName = (TextView) convertView.findViewById(R.id.txt_name_choice);

        // Populate the data into the template view using the data object
        choiceName.setText(choice.getName());

        // Return the completed view to render on screen
        return convertView;
    }
}
