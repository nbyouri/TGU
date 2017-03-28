package muga.thegreatuniversity.controllers.abstracts;

import android.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.controllers.PopUp;
import muga.thegreatuniversity.controllers.TutorialLayout;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.TutorialStep;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.TutorialManager;

/**
 * Created on 28-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public abstract class TutorialListFragment extends ListFragment {

    boolean active;

    @Override
    public void onPause(){
        super.onPause();
        active =false;
    }

    @Override
    public void onStart(){
        super.onStart();

        active = true;

        ImageView help = this.getButtonHelp();
        if (help !=null){
            help.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    printHelp();
                }
            });
        }

        ViewGroup layout = (ViewGroup) getView().getRootView();
        ViewTreeObserver vto = layout.getViewTreeObserver();

        vto.addOnGlobalLayoutListener (new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (active) tutorialDraw();
            }
        });
    }

    private void tutorialDraw(){
        final TutorialLayout tuto = (TutorialLayout) getActivity().findViewById(R.id.layout_game);
        nextTutorialStep(tuto);
        tuto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logger.info("tutorialDraw : Next Tuto Plz");
                TutorialManager.get().changeStep(getFragmentType());
                nextTutorialStep(tuto);
            }
        });
    }

    private void nextTutorialStep(TutorialLayout tuto){

        TutorialStep step = TutorialManager.get().currentStep(getFragmentType());

        if (step !=null ){
            View v = getActivity().findViewById(step.getIdView());
            if (v !=null && tuto !=null){
                tuto.refreshLayout(v);
                tuto.invalidate();
            }
        } else {
            Logger.info("Finish tuto for "+ getFragmentType() + " Fragment");
            tuto.refreshLayout( getActivity().findViewById(R.id.layout_main));
            tuto.invalidate();
        }
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
