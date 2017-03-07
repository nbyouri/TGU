package muga.thegreatuniversity.controllers;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.fragments.BuildFragment;
import muga.thegreatuniversity.controllers.fragments.ChoicesFragment;
import muga.thegreatuniversity.controllers.fragments.HireFragment;
import muga.thegreatuniversity.controllers.fragments.InventoryFragment;
import muga.thegreatuniversity.controllers.fragments.SplashFragment;
import muga.thegreatuniversity.controllers.fragments.StatFragment;
import muga.thegreatuniversity.lists.FragmentType;
import muga.thegreatuniversity.models.Event;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.SaveManager;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class MainActivity extends Activity implements CallbackActivity {

    private static final int TITLE_TIME_OUT = 1500;


    private boolean active;

    private StatFragment statF;
    private ChoicesFragment choicesF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFragment();
    }

    @Override
    public void onResume(){
        super.onResume();
        active = true;

        // TODO : CHECK IF IN SLAPH SCREEN
        if (!(statF != null && statF.isVisible())){
            Logger.info("Resume splash screen");
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (SaveManager.isSaveExist(getApplicationContext())) {
                        printGame();
                    } else {
                        printLoginLayout();
                    }
                }
            }, TITLE_TIME_OUT);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        active = false;
    }

    @Override
    public void callback(Bundle bundle) {
        if (bundle.containsKey("Type")){
            String type = bundle.getString("Type");

            if (type != null && type.equals("CreateUniv")){
                String nameUni = bundle.getString("NameUniv");
                University.get().createNewUniversity(nameUni);
                SaveManager.saveUniversity(getApplicationContext());
                printGame();
            }

        } else {
            Logger.error("Invalid Callback, Bundle : " + bundle.toString());
        }
    }

    /**
     * update all views (when printable value change)
     */
    public void updateView(){
        if (statF.isVisible()){
            statF.printStat();
        }
    }

    public void changeWeek(){
        University.get().newTurn();
        Event event = University.get().getCurrentEvent();
        if(event != null)
            PopUp.alertNewEvent(this, event);
        updateView();
        SaveManager.saveUniversity(this.getApplicationContext()); // TODO : SAVE CURRENT EVENT AND AFTER
    }

    /**
     * For change the fragment in the main FrameLayout
     * @param fragType : Type of replacing fragment
     */
    public void changeMainFragment(FragmentType fragType){

        Fragment frag  = null;

        // CHOOSE BY WHICH FRAGMENT
        switch (fragType) {
            case HIRE_PROF:
                frag = new HireFragment();
                break;
            case INVENTORY:
                frag = new InventoryFragment();
                break;
            case BUILD_ROOM:
                frag = new BuildFragment();
                break;
        }

        commitFrag(frag);

    }

    private void commitFrag(Fragment frag){
        if ((frag != null)){
            FragmentTransaction transaction = getFragmentManager().beginTransaction();

            Slide slEnter = new Slide();
            slEnter.setSlideEdge(Gravity.END);
            frag.setEnterTransition(slEnter);

            transaction.replace(R.id.frame_main, frag);
            transaction.addToBackStack(null);

            transaction.commit();
        } else {
            Logger.error("Fragment is null");
        }
    }

    private void loadFragment() {
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
                && findViewById(R.id.frame_all) != null && active) {


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

        if (!this.active){
            return;
        }

        View loginLayout = findViewById(R.id.layout_login);

        if (loginLayout != null) {
            loginLayout.setVisibility(View.VISIBLE);
        }

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
