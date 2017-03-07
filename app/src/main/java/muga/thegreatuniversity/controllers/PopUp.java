package muga.thegreatuniversity.controllers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.AnsType;
import muga.thegreatuniversity.models.Event;
import muga.thegreatuniversity.models.EventManager;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Context;

/**
 * Created on 21/02/17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class PopUp {

    static public void createUnivPopUp(final MainActivity mainAct) {

        AlertDialog.Builder builderDialog = new AlertDialog.Builder(mainAct);
        builderDialog.setTitle(Context.getString(R.string.popUp_createUniv));
        builderDialog.setMessage(Context.getString(R.string.popUp_enterName));
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

    static public void nameTestValidity(final MainActivity mainAct, final  String name) {
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

        } else
            alertCreateUniv(mainAct, name);
    }

    static public void alertCreateUniv(final MainActivity mainAct, final String name) {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(mainAct);
        helpBuilder.setMessage(Context.getString(R.string.popUp_Verify)+" "+name);
        helpBuilder.setPositiveButton(Context.getString(R.string.popUp_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Bundle mes = new Bundle();
                        mes.putString("Type", "CreateUniv");
                        mes.putString("NameUniv", name);
                        mainAct.callback(mes);
                    }
                });
        helpBuilder.setNegativeButton(Context.getString(R.string.popUp_cancel),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        createUnivPopUp(mainAct);
            }
        });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    static public void alertNewEvent(final MainActivity mainAct, final Event event) {
        AlertDialog.Builder eventBuilder = new AlertDialog.Builder(mainAct);
        eventBuilder.setCancelable(false);
        switch (event.getType()) {
            case DETEV:
            eventBuilder.setTitle(event.getEvent());
            eventBuilder.setPositiveButton(Context.getString(R.string.popUp_ok), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    event.setAns(AnsType.NOANS);
                    University.get().eventAction(event);
                    mainAct.updateView();
                }
            });
                break;
            case NODETEV:
            eventBuilder.setTitle(event.getEvent());
            eventBuilder.setPositiveButton(event.getFirstChoice(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    event.setAns(AnsType.YES);
                    University.get().eventAction(event);
                    mainAct.updateView();
                }
            });
            eventBuilder.setNegativeButton(event.getSecondChoice(), new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    event.setAns(AnsType.NO);
                    University.get().eventAction(event);
                    mainAct.updateView();
                }
            });
                break;
        }
        AlertDialog eventDialog = eventBuilder.create();
        eventDialog.show();
    }
}
