package muga.thegreatuniversity.controllers.tutorial;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.Arrays;
import java.util.HashSet;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.DefaultValues;
import muga.thegreatuniversity.lists.enums.FragmentType;
import muga.thegreatuniversity.models.TutorialStep;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.TutorialManager;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialLayout extends LinearLayout {

    private int widthScreen;
    private int topTextMargin;
    private int sideTextMargin;
    private int textSize;

    private Bitmap windowFrame;

    // ---------- FUNCTION MANDATORY ----------------
    public TutorialLayout(Context context) {
        super(context);
        constructorCall();
    }

    public TutorialLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        constructorCall();
    }

    public TutorialLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        constructorCall();
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    /**
     * DISABLE clickListener of children when tutorial is printing
     */
    public boolean onInterceptTouchEvent (MotionEvent ev){
        return windowFrame != null;
    }

    /**
     * Calculate one time some constant about screen (optimization)
     */
    private void constructorCall(){
        widthScreen =  getContext().getResources().getDisplayMetrics().widthPixels;
        int heightScreen =  getContext().getResources().getDisplayMetrics().heightPixels;
        topTextMargin = (DefaultValues.MARGIN_TEXT_TOP_PR * heightScreen/100);
        sideTextMargin = (DefaultValues.MARGIN_TEXT_SIDE_PR * widthScreen/100);
        textSize = (widthScreen/DefaultValues.TEXT_SIZE_RAPPORT);
        activeFragment = new HashSet<>();
    }

    // ------- MANAGE WHICH TUTORIAL TO DRAW -------------

    private HashSet<FragmentType> activeFragment;
    private FragmentType currentFragmentTutorial;

    public void addCurrentFragment(FragmentType fragmentType){
        activeFragment.add(fragmentType);
        if (currentFragmentTutorial == null){
            currentFragmentTutorial = this.choiceOneTutorialFrag();
        }
    }

    public void removeCurrentFragment(FragmentType fragmentType){
        activeFragment.remove(fragmentType);
        if (currentFragmentTutorial == fragmentType){
            currentFragmentTutorial = this.choiceOneTutorialFrag();
        }
    }

    public void refreshTutorial(){
        final FragmentType fragmentType = currentFragmentTutorial;
        this.nextTutorialStep(fragmentType);

        this.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TutorialManager.get().changeStep(fragmentType, getContext());
                nextTutorialStep(fragmentType);
            }
        });
    }

    private void nextTutorialStep(FragmentType fragmentType){
        TutorialStep step = TutorialManager.get().currentStep(fragmentType);
        if (step !=null ){
            this.refreshLayout(step);
            this.invalidate();
        } else {
            this.cleanCanvas();
            this.invalidate();
            currentFragmentTutorial = this.choiceOneTutorialFrag();
            if (currentFragmentTutorial !=null) refreshTutorial();
        }
    }

    private FragmentType choiceOneTutorialFrag(){
        FragmentType fragmentType = null;
        for (FragmentType frag : activeFragment){
            if (TutorialManager.get().currentStep(frag) != null){
                fragmentType = frag;
            }
        }
        return fragmentType;
    }

    // Called to draw inside
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (windowFrame != null) {
            canvas.drawBitmap(windowFrame, 0, 0, null);
        }

    }

    private void refreshLayout(TutorialStep step){
        if (step.hasView()){
            focusTutorial(step);
        } else {
            noFocusTutorial(step);
        }
    }

    private void noFocusTutorial(TutorialStep step){
        windowFrame = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        TutorialCanvas canvas = new TutorialCanvas(windowFrame, widthScreen, sideTextMargin, topTextMargin);
        String msgContinue = getResources().getString(R.string.tutorial_click_continue);
        String msg = step.getMessage();
        canvas.displayFuzzy(ContextCompat.getColor(this.getContext(), R.color.tutorial_background));
        canvas.displayText(msg, msgContinue,textSize);
    }

    private int[] oldPosFocus;

    private void focusTutorial(TutorialStep step){

        View focus = this.findViewById(step.getIdView());
        if (focus==null){
            Logger.error("TUTORIAL LAYOUT : view equals to null");
            return;
        }

        // OPTIMIZATION ------------------------------
        if (focus.getHeight() == 0 || focus.getWidth() == 0) return;

        int[] posXY = new int[2];
        focus.getLocationOnScreen(posXY);

        int[] posFocus = new int[4];

        posFocus[0] = posXY[0];
        posFocus[1] = posXY[1];
        posFocus[2] = focus.getWidth();
        posFocus[3] = focus.getHeight();

        if (Arrays.equals(oldPosFocus, posFocus)) return;
        // --------------------------------------------

        windowFrame = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        TutorialCanvas canvas = new TutorialCanvas(windowFrame, widthScreen, sideTextMargin, topTextMargin);
        String msgContinue = getResources().getString(R.string.tutorial_click_continue);
        String msg = step.getMessage();

        int[] posRect = new int[4];

        posRect[0] = posFocus[0] - DefaultValues.MARGIN_REVERSE;
        posRect[1] = posFocus[1] - DefaultValues.MARGIN_REVERSE;
        posRect[2] = posFocus[0] + posFocus[2] + DefaultValues.MARGIN_REVERSE;
        posRect[3] = posFocus[1] + posFocus[3] + DefaultValues.MARGIN_REVERSE;

        canvas.displayFuzzy(ContextCompat.getColor(this.getContext(), R.color.tutorial_background));
        canvas.displayReverse(posRect);
        canvas.displayStrokeRect(posRect);
        canvas.displayText(msg,msgContinue,posFocus,textSize);

        oldPosFocus = posFocus;
    }

    /**
     * Clean the tutorial canvas
     */
    private void cleanCanvas(){
        windowFrame = null;
    }
}
