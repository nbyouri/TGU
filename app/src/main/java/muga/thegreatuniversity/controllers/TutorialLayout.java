package muga.thegreatuniversity.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
    private int heightScreen;
    private int topTextMargin;
    private int sideTextMargin;

    private Bitmap windowFrame;

    // FUNCTION MANDATORY
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

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    // DISABLE clickListener of children when tutorial is printing
    public boolean onInterceptTouchEvent (MotionEvent ev){
        return windowFrame != null;
    }

    // Calculate one time some constant about screen
    private void constructorCall(){
        widthScreen =  getContext().getResources().getDisplayMetrics().widthPixels;
        heightScreen =  getContext().getResources().getDisplayMetrics().heightPixels;
        topTextMargin = (DefaultValues.MARGIN_TEXT_TOP_PR * heightScreen/100);
        sideTextMargin = (DefaultValues.MARGIN_TEXT_SIDE_PR * widthScreen/100);
        activeFragment = new HashSet<FragmentType>();
    }

    // MANAGE WHICH TUTORIAL TO DRAW
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
                Logger.info("refreshTutorial : Next Tuto Plz");
                TutorialManager.get().changeStep(fragmentType);
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
            Logger.info("Finish tuto for "+ fragmentType + " Fragment");
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

    // FUNCTION TO DRAW THE TUTORIAL
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (windowFrame != null) {
            Logger.info(" TUTORIAL LAYOUT : Draw Tutorial");
            canvas.drawBitmap(windowFrame, 0, 0, null);
        } else {
            Logger.info(" TUTORIAL LAYOUT : winFrame null");
        }

    }

    public void refreshLayout(TutorialStep step){

        if (!step.hasView()) {
            windowFrame = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(windowFrame);
            displayNoViewStep(canvas, step);
            return;
        }

        View focus = this.findViewById(step.getIdView());
        if (focus == null){
            Logger.error("TUTORIAL LAYOUT : view equals to null");
            return;
        }

        if (focus.getHeight() == 0 || focus.getWidth() == 0) return;
        // TODO Check if previous is the same position. If yes, do nothing

        windowFrame = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(windowFrame);

        int[] pos = new int[2];
        focus.getLocationOnScreen(pos);

        int[] posFocus = new int[4];

        posFocus[0] = pos[0];
        posFocus[1] = pos[1];
        posFocus[2] = focus.getWidth();
        posFocus[3] = focus.getHeight();

        int[] posRect = new int[4];

        posRect[0] = posFocus[0] - DefaultValues.MARGIN_REVERSE;
        posRect[1] = posFocus[1] - DefaultValues.MARGIN_REVERSE;
        posRect[2] = posFocus[0] + posFocus[2] + DefaultValues.MARGIN_REVERSE;
        posRect[3] = posFocus[1] + posFocus[3] + DefaultValues.MARGIN_REVERSE;

        displayFuzzy(canvas);
        displayReverse(canvas, posRect);
        displayStrokeRect(canvas, posRect);
        displayText(canvas, step, posFocus);
    }

    private void displayFuzzy(Canvas canvas){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());
        paint.setColor(ContextCompat.getColor(this.getContext(), R.color.tutorial_background)); // This is the color of your activity background
        canvas.drawRect(outerRectangle, paint);
    }

    private void displayReverse(Canvas canvas, int[] posRect){
        // REVERSE BLACK TRANSPARENT
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        RectF inRectangle = new RectF(posRect[0],posRect[1],posRect[2],posRect[3]);
        Logger.info(inRectangle.toString());

        canvas.drawRect(inRectangle, paint);
    }

    private void displayStrokeRect(Canvas canvas, int[] posRect){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Rect rectStroke = new Rect(posRect[0],posRect[1],posRect[2],posRect[3]);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(DefaultValues.STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectStroke, paint);
    }

    private void displayText(Canvas canvas, TutorialStep step, int[] posFocus){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int sizeText = widthScreen/DefaultValues.TEXT_SIZE_RAPPORT;
        paint.setColor(Color.CYAN);
        paint.setTextSize(sizeText);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        paint.getTextBounds(step.getMessage(), 0, step.getMessage().length(), bounds);

        float yTxt;
        if (!morePlaceTop(posFocus)) {
            yTxt = posFocus[1] + posFocus[3] - bounds.top*1.5f + topTextMargin ;
        } else {
            yTxt =  -bounds.top*1.5f + topTextMargin;
        }

        for (String line : slipPrintableText(step.getMessage(), paint)){
            canvas.drawText(line, getWidth()/2, yTxt, paint);
            yTxt = yTxt + (-bounds.top*1.5f);
        }

        paint.setTextSize(sizeText/2);
        canvas.drawText(getResources().getString(R.string.tutorial_click_continue), getWidth()/2, yTxt, paint);
    }

    private void displayNoViewStep(Canvas canvas, TutorialStep step){
        displayFuzzy(canvas);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        int sizeText = widthScreen/DefaultValues.TEXT_SIZE_RAPPORT;
        paint.setColor(Color.CYAN);
        paint.setTextSize(sizeText);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        paint.getTextBounds(step.getMessage(), 0, step.getMessage().length(), bounds);

        float yTxt = getHeight()/2;

        for (String line : slipPrintableText(step.getMessage(), paint)){
            canvas.drawText(line, getWidth()/2, yTxt, paint);
            yTxt = yTxt + (-bounds.top*1.5f);
        }
        paint.setTextSize(sizeText/2);
        canvas.drawText(getResources().getString(R.string.tutorial_click_continue), getWidth()/2, yTxt, paint);
    }

    private List<String> slipPrintableText(String message, Paint paint){
        StringBuilder mesBuilder = new StringBuilder(message);

        ArrayList<String> slipStrings = new ArrayList<String>();
        while (mesBuilder.length() > 0){
            StringBuilder temp = new StringBuilder(mesBuilder);

            float sizeT = paint.measureText(temp.toString());
            while (sizeT > widthScreen - sideTextMargin * 2){
                if (temp.lastIndexOf(" ") == -1) break;
                temp.delete(temp.lastIndexOf(" "), temp.length());
                sizeT = paint.measureText(temp.toString());
            }

            mesBuilder.delete(0,temp.length());
            slipStrings.add(temp.toString());
        }
        return slipStrings;
    }

    private boolean morePlaceTop(int[] posFocus){
        return ((posFocus[1]) > (getHeight()-(posFocus[1]+posFocus[3])));
    }

    public void cleanCanvas(){
        windowFrame = null;
    }
}
