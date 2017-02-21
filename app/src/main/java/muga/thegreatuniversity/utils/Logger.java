package muga.thegreatuniversity.utils;

import android.util.Log;

/**
 * Created by youri on 21/02/2017.
 */

public class Logger {
    private static final String TAG = "TGU";

    public void Info(String s) {
        Log.i(TAG, s);
    }

    public void Error(String s) {
        Log.e(TAG, s);
    }

    public void Wtf(String s) {
        Log.wtf(TAG, s);
    }
}
