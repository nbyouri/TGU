package muga.thegreatuniversity.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import muga.thegreatuniversity.R;
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
        builderDialog.setPositiveButton(Context.getString(R.string.popUp_enterName),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertCreateUniv(mainAct, input.getText().toString());
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog univDialog = builderDialog.create();
        univDialog.show();
    }

    static public void alertCreateUniv(final MainActivity mainAct, final String name) {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(mainAct);
        helpBuilder.setMessage(Context.getString(R.string.popUp_congratulations));
        helpBuilder.setPositiveButton(Context.getString(R.string.popUp_ok),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        University.getInstance().setName(name);
                        Bundle mes = new Bundle();
                        mes.putString("type", "CreateUniv");
                        mainAct.callback(mes);
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

}
