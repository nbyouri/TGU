package muga.thegreatuniversity.app;

import android.app.Application;
import android.content.Context;

/**
 * Created on 21-02-17.
 * Auteurs : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class App extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return App.context;
    }
}