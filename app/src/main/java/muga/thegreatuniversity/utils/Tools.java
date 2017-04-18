package muga.thegreatuniversity.utils;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONException;
import org.json.JSONObject;

import muga.thegreatuniversity.lists.enums.ProfType;

import java.util.Iterator;
import java.util.Random;

/**
 * Created on 10/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Tools {
    public static String Capitalize(String s) {
        StringBuilder sb = new StringBuilder(s.toLowerCase());
        sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        return sb.toString();
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();

        return rand.nextInt((max - min) + 1) + min;
    }

    public static double randDouble (double min, double max){
        Random r = new Random();
        return min + (max - min) * r.nextDouble();
    }

    public static void colorFilter(ImageView img, int color){
        if (img == null){
            Logger.error("ColorFilter : Image equals to null");
            return;
        }
        img.setColorFilter(color, PorterDuff.Mode.MULTIPLY);

    }

    public static View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }
}
