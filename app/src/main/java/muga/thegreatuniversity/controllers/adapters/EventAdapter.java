package muga.thegreatuniversity.controllers.adapters;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.List;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.AnsType;
import muga.thegreatuniversity.lists.enums.EventType;
import muga.thegreatuniversity.models.events.Event;

/**
 * Created on 18/04/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventAdapter extends ArrayAdapter<Event>  {

    private boolean inPopup;

    public EventAdapter(Context context, List<Event> objects) {
        super(context, 0, objects);
        inPopup = false;
    }

    public EventAdapter(Context context, List<Event> objects, boolean inPopup) {
        super(context, 0, objects);
        this.inPopup = inPopup;
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        // Get the data item for this position
        final Event ev = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_event, parent, false);
        }

        if (ev == null) return convertView;

        RadioButton rbYes = (RadioButton) convertView.findViewById(R.id.event_cb_choice_one);
        RadioButton rbNo = (RadioButton) convertView.findViewById(R.id.event_cb_choice_two);

        RadioGroup group = (RadioGroup) convertView.findViewById(R.id.event_rg);

        TextView description = (TextView) convertView.findViewById(R.id.event_txt_description);
        TextView duration = (TextView) convertView.findViewById(R.id.event_txt_duration);

        rbYes.setText(ev.getFirstChoice());
        if (ev.getType()== EventType.TWO_CHOICES) {
            rbNo.setText(ev.getSecondChoice());
        } else {
            rbNo.setVisibility(View.GONE);
        }

        description.setText(ev.getMessage());
        duration.setText(getContext().getString(R.string.event_duration, ev.getDurationMax() - ev.getCount()));

        if (inPopup && ev.getDurationMax() - ev.getCount() == ev.getDurationMax()) {
            // POPUP NEED TO CHOOSE
            ev.setAns(AnsType.YES);
            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                    if (checkedId == R.id.event_cb_choice_one){
                        ev.setAns(AnsType.YES);
                    } else if (checkedId == R.id.event_cb_choice_two){
                        ev.setAns(AnsType.NO);
                    }
                }
            });

        } else {
            // DISABLE RADIO BUTTON
            for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setEnabled(false);
            }

            // SHOW ONLY CHOOSE
            if (ev.getType()== EventType.TWO_CHOICES) {
                if (ev.getAns() == AnsType.YES){
                    rbNo.setVisibility(View.GONE);
                } else {
                    rbYes.setVisibility(View.GONE);
                }
            }

        }

        // Return the completed view to render on screen
        return convertView;
    }
}
