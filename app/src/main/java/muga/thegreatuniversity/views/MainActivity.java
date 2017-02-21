package muga.thegreatuniversity.views;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class MainActivity extends Activity {

    private void load() {
        // 1.5s splash screen
        int SPLASH_TIME_OUT = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView title = (TextView)findViewById(R.id.title_txt);
                title.setVisibility(View.GONE);
                View login = (View)findViewById(R.id.layout_login);
                login.setVisibility(View.VISIBLE);
                ImageView logo = (ImageView)findViewById(R.id.img_logo);
                logo.setVisibility(View.GONE);
            }
        }, SPLASH_TIME_OUT);

        University.getInstance();
        University.getInstance().setName("UCL");
        TextView tag = (TextView)findViewById(R.id.tag_txt);
        tag.setText(University.getInstance().getName());
        University.getLogger().Info(University.getInstance().getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
    }

}
