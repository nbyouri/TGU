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

    private void createUnivPopUp(final MainActivity main) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(main);
        helpBuilder.setTitle("Create university");
        helpBuilder.setMessage("Enter name of your University");
        final EditText input = new EditText(main);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        helpBuilder.setView(input);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        notifCreateUniv(main, input.getText().toString());
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void notifCreateUniv(MainActivity main, String name) {
        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(main);
        helpBuilder.setMessage("Congrulations your university is created !");
        helpBuilder.setPositiveButton("Ok",
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
