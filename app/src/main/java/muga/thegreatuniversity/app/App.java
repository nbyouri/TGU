package muga.thegreatuniversity.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.AsyncTask;

import com.crashlytics.android.Crashlytics;
import io.fabric.sdk.android.Fabric;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

import muga.thegreatuniversity.lists.Assets;
import muga.thegreatuniversity.models.events.Event;
import muga.thegreatuniversity.models.University;
import muga.thegreatuniversity.models.events.Events;
import muga.thegreatuniversity.utils.Logger;
import muga.thegreatuniversity.utils.SaveManager;
import muga.thegreatuniversity.utils.Tools;
import muga.thegreatuniversity.utils.TutorialManager;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class App extends Application {

    @SuppressLint("StaticFieldLeak")
    private static Context context;
    public static boolean assetsLoaded;

    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
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
            if (SaveManager.isFileExist(getApplicationContext(),SaveManager.UNIVERSITY_FILE)) {
                try {
                    SaveManager.loadUniversity(getApplicationContext());
                } catch (Exception e) {
                    Tools.deleteAndRestart(getBaseContext(), "Failed to read save : ", e);
                }
            }

            if (SaveManager.isFileExist(getApplicationContext(),SaveManager.SETTINGS_FILE)) {
                try {
                    SaveManager.loadSettings(getApplicationContext());
                } catch (Exception e) {
                    Tools.deleteAndRestart(getBaseContext(), "Failed to read settings : ", e);
                }
            } else {
                TutorialManager.get().createAllTutorial();
                try {
                    SaveManager.saveSetting(getApplicationContext());
                } catch (Exception e) {
                    Tools.deleteAndRestart(getBaseContext(), "Failed to save settings : ", e);
                }
            }

            try {
                AssetManager assets = getApplicationContext().getAssets();
                Assets ass = Assets.get();
                ass.setWordListAdjectives(loadTxtFile("Adjectives.txt", assets));
                ass.setWordListAnimals(loadTxtFile("Animals.txt", assets));
                ass.setMITcourses(loadTxtFile("MITcourses.txt", assets));
                University.get().setEvents(loadJSON(assets));
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

        private Events loadJSON(AssetManager assets) throws Exception {
            ArrayList<Event> causal = new ArrayList<>();
            ArrayList<Event> others = new ArrayList<>();
            InputStream is = assets.open("Events.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            int read = is.read(buffer);
            if (read == 0) {
                throw new Exception("Empty json file");
            }
            is.close();
            try {
                JSONArray jsonArray = new JSONArray(new String(buffer));
                for (int i = 0; i < jsonArray.length(); i++) {
                    Event ev = new Event();
                    JSONObject obj = jsonArray.getJSONObject((i));
                    if (obj == null) {
                        Logger.error("Failed to get JSON objects : " + i);
                        System.exit(0);
                    }
                    ev.loadJSON(obj);
                    if (ev.isCausal()) {
                        causal.add(ev);
                    } else {
                        others.add(ev);
                    }
                }
            } catch (JSONException je) {
                Logger.error("EVENTS JSON ERROR :" + je.getMessage());
                System.exit(0);
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