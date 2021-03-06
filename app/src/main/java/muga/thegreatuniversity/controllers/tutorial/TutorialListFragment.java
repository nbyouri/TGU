package muga.thegreatuniversity.controllers.tutorial;

import android.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.utils.Context;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 28-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public abstract class TutorialListFragment extends ListFragment {

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

        View help = this.getButtonHelp();
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

    protected abstract View getButtonHelp();

    protected abstract FragmentType getFragmentType();

    private void printHelp(){
        switch (getFragmentType()) {
            case BUILD_ROOM:
                PopUp.helpPopUp(getActivity(), Context.getString(R.string.help_room_more));
                break;
            case HIRE_PROF:
                PopUp.helpPopUp(getActivity(), Context.getString(R.string.help_hire_more));
                break;
            case INVENTORY:
                PopUp.helpPopUp(getActivity(), Context.getString(R.string.help_inventory_more));
                break;
            case ENTERTAINMENT:
                PopUp.helpPopUp(getActivity(), Context.getString(R.string.help_entertainment));
                break;
        }
    }
}
