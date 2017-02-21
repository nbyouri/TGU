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

    private void createUnivPopUp(MainActivity main) {

        AlertDialog.Builder helpBuilder = new AlertDialog.Builder(main);
        helpBuilder.setTitle("Pop Up");
        helpBuilder.setMessage("Enter name of your University");
        final EditText input = new EditText(main);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        helpBuilder.setView(input);
        helpBuilder.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        // Do nothing but close the dialog
                        changeName(input.getText().toString());
                    }
                });

        // Remember, create doesn't show the dialog
        AlertDialog helpDialog = helpBuilder.create();
        helpDialog.show();
    }

    private void changeName(String name) {

    }

}
