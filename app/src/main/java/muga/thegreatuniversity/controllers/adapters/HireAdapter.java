package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.ProfType;
import muga.thegreatuniversity.models.Professor;
import muga.thegreatuniversity.utils.Tools;

/**
 * Created on 28-02-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class HireAdapter extends ArrayAdapter<Professor> {

    public HireAdapter(Context context, List<Professor> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        Professor prof = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_prof, parent, false);
        }

        // Lookup view for data population
        TextView hireName = (TextView) convertView.findViewById(R.id.txt_name_prof);
        TextView ageProf = (TextView) convertView.findViewById(R.id.txt_age_prof);
        TextView course = (TextView) convertView.findViewById(R.id.txt_course_prof);
        TextView price = (TextView) convertView.findViewById(R.id.txt_price_prof);
        TextView efficiency = (TextView) convertView.findViewById(R.id.txt_efficiency_prof);

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon_prof);

        ProfType type = prof != null ? prof.getType() : null;
        if (type != null)
            Tools.colorFilter(icon, type.getColor());

        ageProf.setText(getContext().getString(R.string.prof_age_value, prof != null ? prof.getAge() : 0));
        course.setText(getContext().getString(R.string.prof_cours, prof != null ? prof.getCourses().size() : 0));
        // Populate the data into the template view using the data object
        hireName.setText(prof != null ? prof.getName() : null);
        price.setText(String.valueOf(prof != null ? prof.getPrice() : 0));
        efficiency.setText(getContext().getString(R.string.prof_popularity_value, prof != null ? prof.getPopularity() : 0));

        // Return the completed view to render on screen
        return convertView;
    }
}
