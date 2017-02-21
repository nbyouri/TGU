package muga.thegreatuniversity.views;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.models.University;

public class MainActivity extends Activity {

    private void load() {
        // 1.5s splash screen
        int SPLASH_TIME_OUT = 6000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                TextView title = (TextView)findViewById(R.id.title_txt);
                title.setVisibility(View.GONE);
            }
        }, SPLASH_TIME_OUT);

        University.getInstance();
        University.getInstance().setName("UCL");
        TextView tag = (TextView)findViewById(R.id.tag_txt);
        tag.setText(University.getInstance().getName());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        load();
    }
}
