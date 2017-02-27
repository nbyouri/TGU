package muga.thegreatuniversity.views;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.fragments.ChoicesFragment;
import muga.thegreatuniversity.fragments.HireFragment;
import muga.thegreatuniversity.fragments.SplashFragment;
import muga.thegreatuniversity.fragments.StatFragment;
import muga.thegreatuniversity.lists.FragmentType;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class MainActivity extends Activity implements CallbackActivity {

    private StatFragment statF;
    private ChoicesFragment choicesF;

    private static final int TITLE_TIME_OUT = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadData();
    }

    @Override
    public void callback(Bundle bundle) {
        if (bundle.containsKey("Type")){
            String type = bundle.getString("Type");

            if (type != null && type.equals("CreateUniv")){
                String nameUni = bundle.getString("NameUniv");
                University.getInstance().newUniversity(nameUni);
                printGame();
            }

        } else {
            Logger.error("Invalid Callback, Bundle : " + bundle.toString());
        }
    }

    public void updateView(){
        if (statF.isVisible()){
            statF.printStat();
        }
    }

    public void changeMainFragement(FragmentType fragType){

        Fragment frag = null;

        // CHOOSE BY WHICH FRAGMENT
        switch (fragType) {
            case HIRE_PROF:
                frag = new HireFragment();
                break;
        }

        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        // Thanks anim : https://developer.android.com/training/animation/cardflip.html
        transaction.setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out);
        transaction.replace(R.id.frame_main, frag);
        transaction.addToBackStack(null);

        transaction.commit();

    }

    private void loadData() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                printLoginLayout();
            }
        }, TITLE_TIME_OUT);

        University.getInstance();

        // Create main Fragments
        statF = new StatFragment();
        choicesF = new ChoicesFragment();

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
        // frameLayout exist
        if (findViewById(R.id.frame_stat) != null
                && findViewById(R.id.frame_main) != null
                && findViewById(R.id.frame_all) != null) {


            FrameLayout fAll = (FrameLayout) findViewById(R.id.frame_all);
            FrameLayout fStat = (FrameLayout) findViewById(R.id.frame_stat);
            FrameLayout fMain = (FrameLayout) findViewById(R.id.frame_main);

            fAll.setVisibility(View.GONE);
            fStat.setVisibility(View.VISIBLE);
            fMain.setVisibility(View.VISIBLE);

            // Add the fragment on frame layout
            getFragmentManager().beginTransaction().replace(R.id.frame_stat, statF).commit();
            getFragmentManager().beginTransaction().replace(R.id.frame_main, choicesF).commit();

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

}
