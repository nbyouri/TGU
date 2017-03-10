package muga.thegreatuniversity.app;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Scanner;

import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.models.events.EventManager;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.SaveManager;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class App extends Application {

    private static Context context;
    public static boolean assetsLoaded;


    public void onCreate() {
        super.onCreate();
//        App.context = getApplicationContext();
//        University.get();
//        EventManager.get();
//        if (SaveManager.isSaveExist(getApplicationContext())) {
//            SaveManager.loadUniversity(getApplicationContext());
//        }
        new loadAssets().execute();
    }

    public static Context getAppContext() {
        return App.context;
    }

    private class loadAssets extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {
            App.context = getApplicationContext();
            University.get();
            EventManager.get();
            if (SaveManager.isSaveExist(getApplicationContext())) {
                SaveManager.loadUniversity(getApplicationContext());
            }

            try {
                AssetManager assets = getApplicationContext().getAssets();
                Scanner adjectivesFile = new Scanner(assets.open("Adjectives.txt"));

                ArrayList<String> adjectives = new ArrayList<>();
                while (adjectivesFile.hasNext()) {
                    adjectives.add(adjectivesFile.next());
                }

                Scanner animalsFile = new Scanner(assets.open("Animals.txt"));
                ArrayList<String> animals = new ArrayList<>();
                while (animalsFile.hasNext()) {
                    animals.add(animalsFile.next());
                }

                Assets ass = Assets.get();
                ass.setWordListAdjectives(adjectives);
                ass.setWordListAnimals(animals);
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }

            return 1;
        }

        @Override
        protected void onPostExecute(Integer params) {
            MainActivity.handler.sendEmptyMessage(0);
        }

        @Override
        protected void onPreExecute() {
            // setup loading bar
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // update loading bar
        }
    }
}