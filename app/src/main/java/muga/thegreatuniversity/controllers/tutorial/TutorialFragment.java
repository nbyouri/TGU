package muga.thegreatuniversity.controllers.tutorial;

import android.app.Fragment;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 28-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public abstract class TutorialFragment extends Fragment {

    private boolean active;
    private TutorialLayout tutorialLayout;

    @Override
    public void onPause(){
        super.onPause();
        active = false;
        tutorialLayout.removeCurrentFragment(getFragmentType());
    }

    @Override
    public void onStart(){
        super.onStart();
        active = true;

        tutorialLayout = (TutorialLayout) getActivity().findViewById(R.id.layout_game);
        tutorialLayout.addCurrentFragment(getFragmentType());

        if (getView() == null) {
            Logger.error("OnStart TutorialFragment : No View");
            return;
        }
        ViewGroup layout = (ViewGroup) getView().getRootView();

        ViewTreeObserver vto = layout.getViewTreeObserver();

        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (active) tutorialLayout.refreshTutorial();
            }
        });
    }

    protected abstract FragmentType getFragmentType();
}
