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

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.lists.DefaultValues;
import muga.thegreatuniversity.models.TutorialStep;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialLayout extends LinearLayout {

    private int widthScreen;
    private int heightScreen;
    private int topTextMargin;

    private Bitmap windowFrame;

    public TutorialLayout(Context context) {
        super(context);
        calculateConst();
    }

    public TutorialLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        calculateConst();
    }

    public TutorialLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        calculateConst();
    }

    private void calculateConst(){
        widthScreen =  getContext().getResources().getDisplayMetrics().widthPixels;
        heightScreen =  getContext().getResources().getDisplayMetrics().heightPixels;
        topTextMargin = (DefaultValues.MARGIN_TEXT_TOP_PR * heightScreen/100);
    }

    public boolean onInterceptTouchEvent (MotionEvent ev){
        return windowFrame != null;
    }

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

        View focus = this.findViewById(step.getIdView());

        if (focus == null){
            Logger.error("TUTORIAL LAYOUT : view equals to null");
            return;
        }

        if (focus.getHeight() == 0 || focus.getWidth() == 0) return;
        // TODO Check if previuous is the same position. If yes, do nothing

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

        canvas.drawText(step.getMessage(), getWidth()/2, yTxt, paint);
        paint.setTextSize(sizeText/2);
        canvas.drawText(getResources().getString(R.string.tutorial_click_continue), getWidth()/2, yTxt + (-bounds.top*1.5f), paint);
    }

    private boolean morePlaceTop(int[] posFocus){
        return ((posFocus[1]) > (getHeight()-(posFocus[1]+posFocus[3])));
    }

    public void cleanCanvas(){
        windowFrame = null;
    }

    @Override
    public boolean isInEditMode() {
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }
}
