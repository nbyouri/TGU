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

/**
 * Created on 28-02-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class InventoryAdapter extends ArrayAdapter<Professor> {

    public InventoryAdapter(Context context, List<Professor> objects) {
        super(context, 0, objects);
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        Professor prof = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_prof, parent, false);
        }

        // Lookup view for data population
        TextView hireName = (TextView) convertView.findViewById(R.id.txt_name_prof);

        // Populate the data into the template view using the data object
        hireName.setText("Nom: "+prof.getName()+"\nPopularity: "+String.valueOf(prof.getPopularity())
                +"\nCourse: "+prof.getCourse().getCourseName()+"\nAge: "+prof.getAge());

        // Return the completed view to render on screen
        return convertView;
    }
}
