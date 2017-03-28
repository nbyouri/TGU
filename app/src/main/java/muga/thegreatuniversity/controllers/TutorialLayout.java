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

    private Bitmap windowFrame;

    public TutorialLayout(Context context) {
        super(context);
        widthScreen =  getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public TutorialLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        widthScreen =  getContext().getResources().getDisplayMetrics().widthPixels;
    }

    public TutorialLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        widthScreen =  getContext().getResources().getDisplayMetrics().widthPixels;
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

        windowFrame = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(windowFrame);

        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        // TRANSPARENT BLACK
        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());

        paint.setColor(ContextCompat.getColor(this.getContext(), R.color.tutorial_background)); // This is the color of your activity background
        canvas.drawRect(outerRectangle, paint);

        // REVERSE BLACK TRANSPARENT
        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        int[] pos = new int[2];
        focus.getLocationOnScreen(pos);

        RectF inRectangle = new RectF(pos[0]-10, pos[1]-10, pos[0]+focus.getWidth()+10, pos[1]+focus.getHeight()+10);

        Logger.info(inRectangle.toString());

        canvas.drawRect(inRectangle, paint);

        // STROKE
        Paint myPaint = new Paint();
        Rect rectStroke = new Rect(pos[0]-10, pos[1]-10, pos[0]+focus.getWidth()+10, pos[1]+focus.getHeight()+10);
        myPaint.setColor(Color.GREEN);
        myPaint.setStrokeWidth(5);
        myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectStroke, myPaint);

        displayText(canvas, step, focus);
    }

    private void displayFuzzy(View focus){

    }

    private void displayReverse(View focus){

    }

    private void displayStrokeRect(View focus){

    }

    private void displayText(Canvas canvas, TutorialStep step, View focus){
        Paint paint = new Paint();
        int sizeText = widthScreen/DefaultValues.MARGIN_REVERSE;

        paint.setColor(Color.WHITE);
        paint.setTextSize(sizeText);

        //int posX = v.getScrollX();
        //int posY = v.getScrollY();
        canvas.drawText(step.getMessage(), 10, 25, paint);
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
