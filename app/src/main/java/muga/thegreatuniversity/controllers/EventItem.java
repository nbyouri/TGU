package muga.thegreatuniversity.controllers;

import android.content.Context;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.View;
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
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.Tools;
import muga.thegreatuniversity.utils.Tuple;

/**
 * Created on 26-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class EventItem {

    private final RadioButton rbYes;
    private final RadioButton rbNo;

    private final RadioGroup group;

    private final TextView description;
    private final TextView duration;
    private final LinearLayout infoLayout;

    private final Event ev;
    private final View convertView;
    private final boolean inPopup;

    public EventItem(View convertView, Event ev, boolean inPopup){

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

        if (ev.getType()== EventType.TWO_CHOICES) {
            rbYes.setText(ev.getFirstChoice());
            rbNo.setText(ev.getSecondChoice());
        } else {
            rbYes.setVisibility(View.GONE);
            rbNo.setVisibility(View.GONE);
        }

        description.setText(ev.getMessage());
        duration.setText(convertView.getContext().getString(R.string.event_duration, ev.getDurationMax() - ev.getCount()));

        if (inPopup){
            infoLayout.setVisibility(View.GONE);
        }

        if (isNotChoose()){
            ev.setAns(AnsType.YES);
            if (ev.getType()== EventType.TWO_CHOICES) {
                group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                        if (checkedId == R.id.event_cb_choice_one) {
                            ev.setAns(AnsType.YES);
                        } else if (checkedId == R.id.event_cb_choice_two) {
                            ev.setAns(AnsType.NO);
                        }
                    }
                });
            }
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
            if (ev.getAns() == AnsType.YES) {
                rbNo.setVisibility(View.GONE);
                createTable(ev, convertView, AnsType.YES);
            } else if (ev.getAns() == AnsType.NO) {
                rbYes.setVisibility(View.GONE);
                rbNo.toggle();
                createTable(ev, convertView, AnsType.NO);
            }

        }
    }

    private boolean isNotChoose(){
        return (inPopup && ev.getAns()==null);
    }

    private void createTable(Event ev, View convertView, AnsType ans){
        // CONSEQUENCE OF EVENT
        LinearLayout choiceLayout;
        if (ans == AnsType.YES){
            choiceLayout = (LinearLayout) convertView.findViewById(R.id.event_layout_choice_one);
        } else {
            choiceLayout = (LinearLayout) convertView.findViewById(R.id.event_layout_choice_two);
        }
        Logger.info("TABLE : " + ans);
        for (Tuple<EventValueType, Object> computation : ev.getResult(ans).getComputation().getNewValues()) {
            Logger.info("TABLE computation : " + computation.item1 + " | " + String.valueOf(computation.item2));
            RelativeLayout layout = new RelativeLayout(convertView.getContext());
            TextView text = new TextView(convertView.getContext());
            ImageView icon = new ImageView(convertView.getContext());

            int idText = View.generateViewId();
            text.setId(idText);
            Context cont = convertView.getContext();
            text.setTextSize(Tools.pixelsToSp(cont,cont.getResources().getDimension(R.dimen.text_medium)));
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
