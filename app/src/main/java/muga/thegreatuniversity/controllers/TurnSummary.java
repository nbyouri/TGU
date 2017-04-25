package muga.thegreatuniversity.controllers;

import android.app.AlertDialog;
import android.graphics.Rect;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.adapters.EventAdapter;
import muga.thegreatuniversity.models.Turn;
import muga.thegreatuniversity.utils.Context;

/**
 * Created on 22-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TurnSummary {

    private MainActivity act;
    private Turn turn;

    private View dialogLayout;
    private LinearLayout warningLayout;

    private ListView eventsList;
    private EventAdapter eventAdapter;

    private  Button validate;
    private TextView textNewCash;
    private TextView textNewStudent;
    private TextView textNewTurn;
    private TextView textNewMorale;

    // FOR LOSE
    private TextView textWarning;


    final private AlertDialog dialog;

    public TurnSummary(Turn turn, MainActivity act){
        this.act = act;
        this.turn = turn;

        // retrieve display dimensions
        Rect displayRectangle = new Rect();
        Window window = act.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);


        dialogLayout = View.inflate(Context.getContext(), R.layout.layout_popup_summary, null);
        dialogLayout.setMinimumWidth((int)(displayRectangle.width() * 0.8f));
        dialogLayout.setMinimumHeight((int)(displayRectangle.height() * 0.5f));
        AlertDialog.Builder builder = new AlertDialog.Builder(act);

        warningLayout = (LinearLayout) dialogLayout.findViewById(R.id.popup_layout_warning);

        eventsList = (ListView) dialogLayout.findViewById(R.id.popup_list_event);

        validate = (Button) dialogLayout.findViewById(R.id.popup_validate);
        textNewCash = (TextView) dialogLayout.findViewById(R.id.popup_txt_cash);
        textNewStudent = (TextView) dialogLayout.findViewById(R.id.popup_txt_nb_student);
        textNewTurn = (TextView) dialogLayout.findViewById(R.id.popup_txt_turn);
        textNewMorale = (TextView) dialogLayout.findViewById(R.id.popup_txt_moral);
        textWarning = (TextView)  dialogLayout.findViewById(R.id.popup_txt_warning);

        eventAdapter = new EventAdapter(act.getApplicationContext(),turn.events, true);
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
                act.applyTurn(turn);
                dialog.dismiss();
            }
        });
    }

    public void update(Turn turn){
        textNewCash.setText(String.valueOf(turn.newCash));
        textNewStudent.setText(String.valueOf(turn.newStudent));
        textNewTurn.setText(String.valueOf(turn.week));
        textNewMorale.setText(String.valueOf(turn.newMoral));

        if (turn.resultTurn == 1) {
            warningLayout.setVisibility(View.VISIBLE);
            textWarning.setText(act.getApplicationContext().getString(R.string.lose_best_prof));
        } else if (turn.resultTurn == 2) {
            warningLayout.setVisibility(View.VISIBLE);
            textWarning.setText(act.getApplicationContext().getString(R.string.game_over));
        }

        eventAdapter.notifyDataSetChanged();
    }



}
