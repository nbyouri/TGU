package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
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
import muga.thegreatuniversity.models.University;
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
        TextView ageProf = (TextView) convertView.findViewById(R.id.txt_age_prof);
        TextView course = (TextView) convertView.findViewById(R.id.txt_course_prof);
        TextView price = (TextView) convertView.findViewById(R.id.txt_price_prof);
        TextView efficiency = (TextView) convertView.findViewById(R.id.txt_efficiency_prof);

        ImageView icon = (ImageView) convertView.findViewById(R.id.icon_prof);

        colorRarityProf(icon, prof.getType());

        ageProf.setText("Age : " + prof.getAge());
        course.setText("Courses : "+prof.getCourses().size());
        // Populate the data into the template view using the data object
        hireName.setText(prof.getName());
        price.setText(String.valueOf(prof.getPrice()));
        efficiency.setText( prof.getPopularity()+ "% ");

        // Return the completed view to render on screen
        return convertView;
    }

    private void colorRarityProf(ImageView img, ProfType type){
        int color = Color.WHITE;
        switch (type) {
            case COMMON:
                color = ContextCompat.getColor(this.getContext(), R.color.prof_common);
                break;
            case UNCOMMON:
                color = ContextCompat.getColor(this.getContext(), R.color.prof_uncommon);
                break;
            case RARE:
                color = ContextCompat.getColor(this.getContext(), R.color.prof_rare);
                break;
            case VERY_RARE:
                color = ContextCompat.getColor(this.getContext(), R.color.prof_very_rare);
                break;
            case LEGENDARY:
                color =  ContextCompat.getColor(this.getContext(), R.color.prof_legendary);
                break;
        }

       Tools.colorFilter(img, color);

    }



}
