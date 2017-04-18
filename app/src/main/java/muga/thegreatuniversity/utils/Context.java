package muga.thegreatuniversity.utils;


import muga.thegreatuniversity.app.App;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Context {
    public static String getString(int id) {
        return App.getAppContext().getString(id);
    }

    public static android.content.Context getContext() {
        return App.getAppContext();
    }
}
