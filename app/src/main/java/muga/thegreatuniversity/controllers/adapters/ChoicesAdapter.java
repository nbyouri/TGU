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
import muga.thegreatuniversity.lists.enums.ChoiceType;
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

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        Choice choice = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_choice, parent, false);
        }

        // Lookup views
        ImageView icon = (ImageView)  convertView.findViewById(R.id.icon_choice);
        TextView choiceName = (TextView) convertView.findViewById(R.id.txt_name_choice);

        // Populate the data into the template view using the data object
        ChoiceType ch = choice != null ? choice.getType() : null;
        if (ch != null)
            icon.setImageResource(getIcon(ch));

        choiceName.setText(choice != null ? choice.getName() : null);

        // Return the completed view to render on screen
        return convertView;
    }

    private int getIcon(ChoiceType type){
        switch (type) {
            case PASS_WEEK:
                return R.mipmap.ic_pass_week;
            case HIRE_PROF:
                return R.mipmap.ic_hire_prof;
            case BUILD_ROOM:
                return R.mipmap.ic_build_room;
            case INVENTORY:
                return R.mipmap.ic_people;
            case STATISTICS:
                return R.mipmap.ic_information;
            case SETTINGS:
                return R.mipmap.ic_settings;
            case ENTERTAINMENT:
                return R.mipmap.ic_entertainment;
        }



        return R.mipmap.ic_help;
    }

}
