package muga.thegreatuniversity.controllers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.utils.Context;

/**
 * Created on 21/02/17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class PopUp {

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

// --Commented out by Inspection START (27/04/2017, 00:35):
//    static void alertNewEvent(final MainActivity mainAct, final Event event, final Turn newTurn) {
//        AlertDialog.Builder eventBuilder = new AlertDialog.Builder(mainAct);
//        eventBuilder.setCancelable(false);
//        switch (event.getType()) {
//            case DETERMINIST:
//                eventBuilder.setTitle(event.getMessage());
//                eventBuilder.setPositiveButton(Context.getString(R.string.popUp_ok), new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        event.setAns(AnsType.YES);
//                        University.get().applyTurn(newTurn);
//                        mainAct.updateView();
//                    }
//                });
//                break;
//            case TWO_CHOICES:
//                eventBuilder.setTitle(event.getMessage());
//                eventBuilder.setPositiveButton(event.getFirstChoice(), new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        event.setAns(AnsType.YES);
//                        University.get().applyTurn(newTurn);
//                        mainAct.updateView();
//                    }
//                });
//                eventBuilder.setNegativeButton(event.getSecondChoice(), new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int id) {
//                        event.setAns(AnsType.NO);
//                        University.get().applyTurn(newTurn);
//                        mainAct.updateView();
//                    }
//                });
//                break;
//        }
//        AlertDialog eventDialog = eventBuilder.create();
//        eventDialog.show();
//    }
// --Commented out by Inspection STOP (27/04/2017, 00:35)

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

// --Commented out by Inspection START (27/04/2017, 00:36):
//    static void simplePopUp(final Activity mainAct, String message){
//        AlertDialog.Builder builderDialog = new AlertDialog.Builder(mainAct);
//        builderDialog.setMessage(message);
//        builderDialog.setCancelable(false);
//        builderDialog.setPositiveButton(Context.getString(R.string.popUp_ok),
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                    }
//                });
//
//        // Remember, create doesn't show the dialog
//        AlertDialog univDialog = builderDialog.create();
//        univDialog.show();
//    }
// --Commented out by Inspection STOP (27/04/2017, 00:36)

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
