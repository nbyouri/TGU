package muga.thegreatuniversity.controllers.tutorial;

import android.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 28-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public abstract class TutorialFragment extends Fragment {

    boolean active;
    TutorialLayout tutorialLayout;

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

        ImageView help = this.getButtonHelp();
        if (help !=null){
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    printHelp();
                }
            });
        }

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

    public abstract ImageView getButtonHelp();

    public abstract FragmentType getFragmentType();

    private void printHelp(){
        switch (getFragmentType()) {
            case BUILD_ROOM:
                PopUp.helpPopUp(getActivity(), getActivity().getString(R.string.help_room_more));
                break;
        }
    }

}
