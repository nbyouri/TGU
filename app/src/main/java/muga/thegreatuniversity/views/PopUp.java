package muga.thegreatuniversity.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Context;

/**
 * Created by tristanmoers on 21/02/17.
 */

public class PopUp {

    static public void createUnivPopUp(final Activity main) {

        AlertDialog.Builder builderDialog = new AlertDialog.Builder(main);
        builderDialog.setTitle(Context.getString(R.string.create_univ_button));
        builderDialog.setMessage(Context.getString(R.string.PopUp_enterName));
        final EditText input = new EditText(main);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builderDialog.setView(input);
        builderDialog.setPositiveButton(Context.getString(R.string.PopUp_ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertCreateUniv(main, input.getText().toString());
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog univDialog = builderDialog.create();
        univDialog.show();
    }

    static public void alertCreateUniv(Activity main, String name) {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(main);
        helpBuilder.setMessage(Context.getString(R.string.PopUp_congratulations));
        helpBuilder.setPositiveButton(Context.getString(R.string.PopUp_ok),
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog

                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

}
