package muga.thegreatuniversity.app;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import muga.thegreatuniversity.controllers.MainActivity;
import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.lists.enums.ProfType;
import muga.thegreatuniversity.models.Course;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.models.events.EventManager;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.models.events.Events;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.SaveManager;
import muga.thegreatuniversity.utils.TutorialManager;

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
        assetsLoaded = false;
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
            if (SaveManager.isSaveExist(getApplicationContext())) {
                SaveManager.loadUniversity(getApplicationContext());
            }

            SaveManager.loadSettings(getApplicationContext());

            try {
                AssetManager assets = getApplicationContext().getAssets();
                Assets ass = Assets.get();
                ass.setWordListAdjectives(loadTxtFile("Adjectives.txt", assets));
                ass.setWordListAnimals(loadTxtFile("Animals.txt", assets));
                ass.setMITcourses(loadTxtFile("MITcourses.txt", assets));
                University.get().setEvents(loadJSON("Events.json", assets));
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
                arrayString.add(scn.nextLine());
            }

            return arrayString;
        }

        private Events loadJSON(String filename, AssetManager assets) throws Exception {
            ArrayList<Event> causal = new ArrayList<>();
            ArrayList<Event> others = new ArrayList<>();
            InputStream is = assets.open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String bufferString = new String(buffer);
            JSONArray jsonArray = new JSONArray(bufferString);
            for (int i = 0; i < jsonArray.length(); i++) {
                Event ev = new Event();
                ev.loadJSON(jsonArray.getJSONObject(i));
                if (ev.isCausal()) {
                    causal.add(ev);
                } else {
                    others.add(ev);
                }
            }
            return new Events(causal, others);
        }

        @Override
        protected void onPostExecute(Integer params) {
            assetsLoaded = true;
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