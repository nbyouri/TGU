package muga.thegreatuniversity.utils;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.media.Image;
import android.widget.ImageView;

import muga.thegreatuniversity.lists.enums.ProfType;

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

        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    public static void colorFilter(ImageView img, int color){
        if (img == null){
            Logger.error("ColorFilter : Image equals to null");
            return;
        }
        img.setColorFilter(color, PorterDuff.Mode.MULTIPLY);

    }

}
