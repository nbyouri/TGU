package muga.thegreatuniversity.controllers.tutorial;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.List;

import muga.thegreatuniversity.lists.DefaultValues;
import muga.thegreatuniversity.utils.Logger;

/**
 * Created on 04-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class TutorialCanvas extends Canvas {

    private int widthScreen,sideTextMargin,topTextMargin;

    public TutorialCanvas(Bitmap windowFrame, int widthScreen, int sideTextMargin, int topTextMargin){
        super(windowFrame);
        this.widthScreen = widthScreen;
        this.sideTextMargin = sideTextMargin;
        this.topTextMargin = topTextMargin;
    }

    public void displayFuzzy(int fuzzyColor){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        RectF outerRectangle = new RectF(0, 0, getWidth(), getHeight());
        paint.setColor(fuzzyColor);
        this.drawRect(outerRectangle, paint);
    }

    public void displayReverse(int[] posRect){
        // REVERSE BLACK TRANSPARENT
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.TRANSPARENT);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT));

        RectF inRectangle = new RectF(posRect[0],posRect[1],posRect[2],posRect[3]);
        Logger.info(inRectangle.toString());

        this.drawRect(inRectangle, paint);
    }

    public void displayStrokeRect( int[] posRect){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        Rect rectStroke = new Rect(posRect[0],posRect[1],posRect[2],posRect[3]);
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(DefaultValues.STROKE_WIDTH);
        paint.setStyle(Paint.Style.STROKE);
        this.drawRect(rectStroke, paint);
    }

    public void displayText(String msgStep, String msgToContinue, int[] posFocus, int sizeText){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //int sizeText = widthScreen/DefaultValues.TEXT_SIZE_RAPPORT;
        paint.setColor(Color.CYAN);
        paint.setTextSize(sizeText);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        paint.getTextBounds(msgStep, 0, msgStep.length(), bounds);

        float yTxt;
        if (!morePlaceTop(posFocus)) {
            yTxt = posFocus[1] + posFocus[3] - bounds.top*1.5f + topTextMargin ;
        } else {
            yTxt =  -bounds.top*1.5f + topTextMargin;
        }

        for (String line : slipPrintableText(msgStep, paint)){
            this.drawText(line, getWidth()/2, yTxt, paint);
            yTxt = yTxt + (-bounds.top*1.5f);
        }

        paint.setTextSize(sizeText/2);
        this.drawText(msgToContinue, getWidth()/2, yTxt, paint);
    }

    public void displayText(String msgStep, String msgToContinue, int sizeText){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.CYAN);
        paint.setTextSize(sizeText);
        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        paint.setTextAlign(Paint.Align.CENTER);

        Rect bounds = new Rect();
        paint.getTextBounds(msgStep, 0, msgStep.length(), bounds);

        float yTxt = getHeight()/2;

        for (String line : slipPrintableText(msgStep, paint)){
            this.drawText(line, getWidth()/2, yTxt, paint);
            yTxt = yTxt + (-bounds.top*1.5f);
        }
        paint.setTextSize(sizeText/2);
        this.drawText(msgToContinue, getWidth()/2, yTxt, paint);
    }

    private List<String> slipPrintableText(String message, Paint paint){
        StringBuilder mesBuilder = new StringBuilder(message);

        ArrayList<String> slipStrings = new ArrayList<>();
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

}
