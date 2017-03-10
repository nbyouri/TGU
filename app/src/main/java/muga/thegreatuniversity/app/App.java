package muga.thegreatuniversity.app;

import android.app.Application;
import android.content.Context;

import muga.thegreatuniversity.models.events.EventManager;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.SaveManager;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class App extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        App.context = getApplicationContext();
        University.get();
        EventManager.get();
        if (SaveManager.isSaveExist(getApplicationContext())) {
            SaveManager.loadUniversity(getApplicationContext());
        }
    }

    public static Context getAppContext() {
        return App.context;
    }
}