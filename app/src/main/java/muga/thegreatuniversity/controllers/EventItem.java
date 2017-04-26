package muga.thegreatuniversity.controllers;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.AnsType;
import muga.thegreatuniversity.lists.enums.EventType;
import muga.thegreatuniversity.lists.enums.EventValueType;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.utils.Tuple;

/**
 * Created on 26-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventItem {

    private RadioButton rbYes;
    private RadioButton rbNo;

    private RadioGroup group;

    private TextView description;
    private TextView duration;
    private LinearLayout infoLayout;

    private Event ev;
    private View convertView;
    private boolean inPopup;

    public EventItem(View convertView, @NonNull ViewGroup parent, Event ev, boolean inPopup){

        this.convertView = convertView;
        this.ev = ev;
        this.inPopup =inPopup;

        rbYes = (RadioButton) convertView.findViewById(R.id.event_cb_choice_one);
        rbNo = (RadioButton) convertView.findViewById(R.id.event_cb_choice_two);

        group = (RadioGroup) convertView.findViewById(R.id.event_rg);

        description = (TextView) convertView.findViewById(R.id.event_txt_description);
        duration = (TextView) convertView.findViewById(R.id.event_txt_duration);

        infoLayout = (LinearLayout) convertView.findViewById(R.id.layout_event_info);
    }

    public void create(){
        rbYes.setText(ev.getFirstChoice());
        if (ev.getType()== EventType.TWO_CHOICES) {
            rbNo.setText(ev.getSecondChoice());
        } else {
            rbNo.setVisibility(View.GONE);
        }

        description.setText(ev.getMessage());
        duration.setText(convertView.getContext().getString(R.string.event_duration, ev.getDurationMax() - ev.getCount()));

        if (inPopup){
            infoLayout.setVisibility(View.GONE);
        }

        /*
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
            createTable(ev, convertView, AnsType.YES);
            if (ev.getType() == EventType.TWO_CHOICES) {
                createTable(ev, convertView, AnsType.NO);
            }
        } else {
            // DISABLE RADIO BUTTON
            for (int i = 0; i < group.getChildCount(); i++) {
                group.getChildAt(i).setEnabled(false);
            }

            // SHOW ONLY CHOOSE
            if (ev.getType()== EventType.TWO_CHOICES) {
                if (ev.getAns() == AnsType.YES){
                    rbNo.setVisibility(View.GONE);
                    createTable(ev, convertView, AnsType.YES);
                } else {
                    rbYes.setVisibility(View.GONE);
                    createTable(ev, convertView, AnsType.NO);
                }
            }
        } */
    }

    private void createTable(Event ev, View convertView, AnsType ans){
        // CONSEQUENCE OF EVENT
        LinearLayout choiceLayout;
        if (ans == AnsType.YES){
            choiceLayout = (LinearLayout) convertView.findViewById(R.id.event_layout_choice_one);
        } else {
            choiceLayout = (LinearLayout) convertView.findViewById(R.id.event_layout_choice_two);
        }

        for (Tuple<EventValueType, Object> computation : ev.getResult(ans).getComputation().getNewValues()) {
            RelativeLayout layout = new RelativeLayout(convertView.getContext());
            TextView text = new TextView(convertView.getContext());
            ImageView icon = new ImageView(convertView.getContext());

            int idText = View.generateViewId();
            text.setId(idText);

            text.setText(String.valueOf(computation.item2));
            text.setGravity(Gravity.START);

            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            params.addRule(RelativeLayout.ALIGN_BOTTOM, idText);
            params.addRule(RelativeLayout.ALIGN_TOP, idText);
            params.addRule(RelativeLayout.RIGHT_OF, idText);

            icon.setImageResource(getIconId(computation.item1));

            layout.addView(text);
            layout.addView(icon);

            icon.setLayoutParams(params);

            choiceLayout.addView(layout);
        }
    }

    private int getIconId(EventValueType type){
        switch (type) {
            case MONEY:
                return R.mipmap.ic_money;
            case POPULARITY:
                return R.mipmap.ic_popularity;
            case MORAL:
                return R.mipmap.ic_moral_positive;
            case STUDENT:
                return R.mipmap.ic_people;
            case PROF: // TODO Review
                return R.mipmap.ic_professor;
            default:
                return R.mipmap.ic_help;
        }
    }

}
