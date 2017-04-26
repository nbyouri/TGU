package muga.thegreatuniversity.utils;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.*;
import android.graphics.PorterDuff;
//import android.view.View;
import android.widget.ImageView;
//import android.widget.ListView;

import java.util.Random;

import static android.content.Context.ACTIVITY_SERVICE;

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

    public static float pixelsToSp(android.content.Context context, float px) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return px/scaledDensity;
    }

//    public static double randDouble (double min, double max){
//        Random r = new Random();
//        return min + (max - min) * r.nextDouble();
//    }

    public static void colorFilter(ImageView img, int color){
        if (img == null){
            Logger.error("ColorFilter : Image equals to null");
            return;
        }
        img.setColorFilter(color, PorterDuff.Mode.MULTIPLY);

    }

//    public static View getViewByPosition(int pos, ListView listView) {
//        final int firstListItemPosition = listView.getFirstVisiblePosition();
//        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;
//
//        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
//            return listView.getAdapter().getView(pos, null, listView);
//        } else {
//            final int childIndex = pos - firstListItemPosition;
//            return listView.getChildAt(childIndex);
//        }
//    }

    /* utility to avoid corrupted saves */
    public static void deleteAndRestart(android.content.Context ctx, String msg, Exception e) {
        Logger.error(msg + e.getCause() + "\n" + e.getMessage());
        ((ActivityManager) ctx.getSystemService(ACTIVITY_SERVICE))
                .clearApplicationUserData();
        Intent mStartActivity = new Intent(ctx, ctx.getClass());
        int mPendingIntentId = 123456;
        PendingIntent mPendingIntent = PendingIntent.getActivity(ctx, mPendingIntentId,
                mStartActivity, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager mgr = (AlarmManager)ctx.getSystemService(android.content.Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
        System.exit(0);
    }
}
