package muga.thegreatuniversity.utils;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.app.App;

/**
 * Created by youri on 21/02/2017.
 */

public class Context {
    public static String getString(int id) {
        return App.getAppContext().getString(id);
    }
}
