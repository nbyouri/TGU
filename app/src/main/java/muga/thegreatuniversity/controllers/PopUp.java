package muga.thegreatuniversity.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.AnsType;
import muga.thegreatuniversity.models.Turn;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Context;

/**
 * Created on 21/02/17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class PopUp {

    static void turnSummmaryPopUp(Activity act, Turn turn){
        View dialogLayout = View.inflate(Context.getContext(), R.layout.layout_popup_summary, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(act);

        Button validate = (Button) dialogLayout.findViewById(R.id.popup_validate);
        TextView textNewCash = (TextView) dialogLayout.findViewById(R.id.popup_txt_cash);
        TextView textNewStudent = (TextView) dialogLayout.findViewById(R.id.popup_txt_nb_student);
        TextView textNewTurn = (TextView) dialogLayout.findViewById(R.id.popup_txt_turn);
        TextView textNewMorale = (TextView) dialogLayout.findViewById(R.id.popup_txt_moral);

        textNewCash.setText(String.valueOf(turn.newCash));
        textNewStudent.setText(String.valueOf(turn.newStudent));
        textNewTurn.setText(String.valueOf(turn.week));
        textNewMorale.setText(String.valueOf(turn.newMoral));

        final AlertDialog dialog = builder.create();

        dialog.setView(dialogLayout);
        dialog.show();

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    public static void helpPopUp(Activity act, String helpMessage){
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(act);
        helpBuilder.setTitle(helpMessage);
        helpBuilder.setPositiveButton(Context.getString(R.string.popUp_ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
        });
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    static void createUnivPopUp(final MainActivity mainAct) {

        AlertDialog.Builder builderDialog = new AlertDialog.Builder(mainAct);
        builderDialog.setTitle(Context.getString(R.string.popUp_createUniv));
        builderDialog.setMessage(Context.getString(R.string.popUp_enterName));
        builderDialog.setCancelable(false);
        final EditText input = new EditText(mainAct);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builderDialog.setView(input);
        builderDialog.setPositiveButton(Context.getString(R.string.popUp_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        nameTestValidity(mainAct, input.getText().toString());
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog univDialog = builderDialog.create();
        univDialog.show();
    }

    static private void nameTestValidity(final MainActivity mainAct, final  String name) {
        if (name.length() < 3 || name.length() > 100) {

            AlertDialog.Builder helpBuilder = new AlertDialog.Builder(mainAct);
            helpBuilder.setMessage(Context.getString(R.string.popUp_error));
            helpBuilder.setNegativeButton(Context.getString(R.string.popUp_retry),
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            createUnivPopUp(mainAct);
                        }
                    });

            // Remember, create doesn't show the dialog
            AlertDialog helpDialog = helpBuilder.create();
            helpDialog.show();

        } else {
            Bundle mes = new Bundle();
            mes.putString("Type", "CreateUniv");
            mes.putString("NameUniv", name);
            mainAct.callback(mes);
        }
    }

    static void alertNewEvent(final MainActivity mainAct, final Event event, final Turn newTurn) {
        AlertDialog.Builder eventBuilder = new AlertDialog.Builder(mainAct);
        eventBuilder.setCancelable(false);
        switch (event.getType()) {
            case DETERMINIST:
                eventBuilder.setTitle(event.getMessage());
                eventBuilder.setPositiveButton(Context.getString(R.string.popUp_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        event.setAns(AnsType.YES);
                        University.get().applyTurn(newTurn);
                        mainAct.updateView();
                    }
                });
                break;
            case TWO_CHOICES:
                eventBuilder.setTitle(event.getMessage());
                eventBuilder.setPositiveButton(event.getFirstChoice(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        event.setAns(AnsType.YES);
                        University.get().applyTurn(newTurn);
                        mainAct.updateView();
                    }
                });
                eventBuilder.setNegativeButton(event.getSecondChoice(), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        event.setAns(AnsType.NO);
                        University.get().applyTurn(newTurn);
                        mainAct.updateView();
                    }
                });
                break;
        }
        AlertDialog eventDialog = eventBuilder.create();
        eventDialog.show();
    }

    static public void notMoney(final Activity mainAct) {

        AlertDialog.Builder builderDialog = new AlertDialog.Builder(mainAct);
        builderDialog.setMessage(Context.getString(R.string.popUp_money));
        builderDialog.setPositiveButton(Context.getString(R.string.popUp_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog univDialog = builderDialog.create();
        univDialog.show();
    }

    static void simplePopUp(final Activity mainAct, String message){
        AlertDialog.Builder builderDialog = new AlertDialog.Builder(mainAct);
        builderDialog.setMessage(message);
        builderDialog.setCancelable(false);
        builderDialog.setPositiveButton(Context.getString(R.string.popUp_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog univDialog = builderDialog.create();
        univDialog.show();
    }

    static public void resetUnivPopUp(final MainActivity mainAct) {

        AlertDialog.Builder builderDialog = new AlertDialog.Builder(mainAct);
        builderDialog.setMessage(Context.getString(R.string.popUp_Reset));

        builderDialog.setPositiveButton(Context.getString(R.string.popUp_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        createUnivPopUp(mainAct);
                        mainAct.updateView();
                    }
                });

        builderDialog.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog univDialog = builderDialog.create();
        univDialog.show();
    }

}
