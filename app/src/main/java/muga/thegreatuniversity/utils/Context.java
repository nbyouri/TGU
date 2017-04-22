package muga.thegreatuniversity.utils;

import muga.thegreatuniversity.app.App;

/**
 * Created by youri on 22/04/2017.
 */

public class Context {
    public static String getString(int id) {
        return App.getAppContext().getString(id);
    }

    public static android.content.Context getContext() {
        return App.getAppContext();
    }
}
