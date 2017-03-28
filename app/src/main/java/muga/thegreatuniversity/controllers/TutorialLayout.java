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
import android.view.View;
import android.widget.LinearLayout;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 21-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialLayout extends LinearLayout {
    private Bitmap windowFrame;

    public TutorialLayout(Context context) {
        super(context);
    }

    public TutorialLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TutorialLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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

    public void refreshLayout(View focus){

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
        myPaint.setStrokeWidth(10);
        myPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(rectStroke, myPaint);
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
