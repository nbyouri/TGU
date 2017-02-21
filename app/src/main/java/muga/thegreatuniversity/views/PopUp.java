package muga.thegreatuniversity.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.University;

/**
 * Created by tristanmoers on 21/02/17.
 */

public class PopUp {

    static public void createUnivPopUp(final MainActivity main) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(main);
        helpBuilder.setTitle("@string/createUniv");
        helpBuilder.setMessage("@string/enterName");
        final EditText input = new EditText(main);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        helpBuilder.setView(input);
        helpBuilder.setPositiveButton("@string/ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        alertCreateUniv(main, input.getText().toString());
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    static public void alertCreateUniv(MainActivity main, String name) {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(main);
        helpBuilder.setMessage("@string/congratulations");
        helpBuilder.setPositiveButton("@string/ok",
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
