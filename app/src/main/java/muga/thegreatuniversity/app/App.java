package muga.thegreatuniversity.app;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import java.io.IOException;
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
        App.context = getApplicationContext();
        new loadAssets().execute();
    }

    public static Context getAppContext() {
        return App.context;
    }

    private class loadAssets extends AsyncTask<Void, Integer, Integer> {

        @Override
        protected Integer doInBackground(Void... params) {

            University.get();
            EventManager.get();
            if (SaveManager.isSaveExist(getApplicationContext())) {
                SaveManager.loadUniversity(getApplicationContext());
            }

            try {
                AssetManager assets = getApplicationContext().getAssets();
                Assets ass = Assets.get();
                ass.setWordListAdjectives(loadTxtFile("Adjectives.txt", assets));
                ass.setWordListAnimals(loadTxtFile("Animals.txt", assets));
            } catch (Exception e) {
                Logger.error(e.getMessage());
            }
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }

        private ArrayList<String> loadTxtFile(String filename, AssetManager assets) throws IOException {
            Scanner scn = new Scanner(assets.open(filename));
            ArrayList<String> arrayString = new ArrayList<>();
            while (scn.hasNext()) {
                arrayString.add(scn.next());
            }

            return arrayString;
        }

        @Override
        protected void onPostExecute(Integer params) {
            if (MainActivity.handler !=null) {
                MainActivity.handler.sendEmptyMessage(0);
            }
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