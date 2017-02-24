package muga.thegreatuniversity.views;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.fragments.ChoicesFragment;
import muga.thegreatuniversity.fragments.HireFragment;
import muga.thegreatuniversity.fragments.SplashFragment;
import muga.thegreatuniversity.fragments.StatFragment;
import muga.thegreatuniversity.models.University;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class MainActivity extends Activity implements CallbackActivity {

    @Override
    public void callback(Bundle bundle) {
        if (bundle.getString("type").equals("CreateUniv")){
            printGame();
        }
    }

    private static final int TITLE_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                printLoginLayout();
            }
        }, TITLE_TIME_OUT);

        University.getInstance();
        printSplashScreen();
    }

    private void printSplashScreen(){
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.frame_all) != null) {

            // Create a new Fragment to be placed in the activity layout
            SplashFragment splashF = new SplashFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction().add(R.id.frame_all, splashF).commit();
        }
    }

    private void printGame(){
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.frame_stat) != null
                && findViewById(R.id.frame_main) != null
                && findViewById(R.id.frame_all) != null) {

            FrameLayout fAll = (FrameLayout) findViewById(R.id.frame_all);
            FrameLayout fStat = (FrameLayout) findViewById(R.id.frame_stat);
            FrameLayout fMain = (FrameLayout) findViewById(R.id.frame_main);

            fAll.setVisibility(View.GONE);
            fStat.setVisibility(View.VISIBLE);
            fMain.setVisibility(View.VISIBLE);

            // Create a new Fragment to be placed in the activity layout
            StatFragment statF = new StatFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction().add(R.id.frame_stat, statF).commit();

            // Create a new Fragment to be placed in the activity layout
            ChoicesFragment choicesF = new ChoicesFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            // For saved
            // firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction().add(R.id.frame_main, choicesF).commit();
        }

    }

    private void printLoginLayout(){

        View loginLayout = findViewById(R.id.layout_login);
        loginLayout.setVisibility(View.VISIBLE);

        Button button= (Button) findViewById(R.id.btn_create_university);
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

    public void hireProf(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Thanks anim : https://developer.android.com/training/animation/cardflip.html
        transaction.setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out);


        HireFragment newFragment = new HireFragment();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.frame_main, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
    }

}
