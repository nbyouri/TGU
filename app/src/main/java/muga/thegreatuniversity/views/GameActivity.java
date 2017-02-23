package muga.thegreatuniversity.views;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.fragments.ChoicesFragment;
import muga.thegreatuniversity.fragments.HireFragment;
import muga.thegreatuniversity.fragments.StatFragment;

/**
 * Created on 23-02-17.
 * Auteurs : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        addFragments();
    }

    private void addFragments(){
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.frame_stat) != null) {

            // Create a new Fragment to be placed in the activity layout
            StatFragment firstFragment = new StatFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction().add(R.id.frame_stat, firstFragment).commit();
        }

        if (findViewById(R.id.frame_main) != null) {

            // Create a new Fragment to be placed in the activity layout
            ChoicesFragment firstFragment = new ChoicesFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            // For saved
            // firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getFragmentManager().beginTransaction().add(R.id.frame_main, firstFragment).commit();
        }

    }

    public void hireProf(){
        FragmentTransaction transaction = getFragmentManager().beginTransaction();

        // Thanck anim : https://developer.android.com/training/animation/cardflip.html
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
