package muga.thegreatuniversity.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.adapters.EventAdapter;
import muga.thegreatuniversity.models.Turn;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created on 22-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TurnSummary {

    View dialogLayout;

    ListView eventsList;
    EventAdapter eventAdapter;

    Button validate;
    TextView textNewCash;
    TextView textNewStudent;
    TextView textNewTurn;
    TextView textNewMorale;

    final AlertDialog dialog;

    public TurnSummary(Turn turn,Activity act){
        LayoutInflater inflater = (LayoutInflater) act.getApplicationContext().getSystemService(LAYOUT_INFLATER_SERVICE);
        dialogLayout = inflater.inflate(R.layout.layout_popup_summary, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(act);

        eventsList = (ListView) dialogLayout.findViewById(R.id.popup_list_event);

        validate = (Button) dialogLayout.findViewById(R.id.popup_validate);
        textNewCash = (TextView) dialogLayout.findViewById(R.id.popup_txt_cash);
        textNewStudent = (TextView) dialogLayout.findViewById(R.id.popup_txt_nb_student);
        textNewTurn = (TextView) dialogLayout.findViewById(R.id.popup_txt_turn);
        textNewMorale = (TextView) dialogLayout.findViewById(R.id.popup_txt_moral);

        eventAdapter = new EventAdapter(act.getApplicationContext(),turn.events);
        eventsList.setAdapter(eventAdapter);

        dialog = builder.create();
        dialog.setView(dialogLayout);

        update(turn);
    }

    public void display(){
        dialog.show();

        eventAdapter.notifyDataSetChanged();

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public void update(Turn turn){
        textNewCash.setText(String.valueOf(turn.newCash));
        textNewStudent.setText(String.valueOf(turn.newStudent));
        textNewTurn.setText(String.valueOf(turn.week));
        textNewMorale.setText(String.valueOf(turn.newMoral));
    }

}
