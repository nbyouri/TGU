package muga.thegreatuniversity.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.University;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class MainActivity extends Activity {

    private static final int TITLE_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
    }

    private void load() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                printLoginLayout();
            }
        }, TITLE_TIME_OUT);
        University.getInstance();
    }

    private void printLoginLayout(){
        View loginLayout = findViewById(R.id.layout_login);
        loginLayout.setVisibility(View.VISIBLE);

        Button button= (Button) findViewById(R.id.button_createuniversity);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUniv();
            }
        });
    }

    private void createUniv(){
        PopUp.createUnivPopUp(this);
    }

}
