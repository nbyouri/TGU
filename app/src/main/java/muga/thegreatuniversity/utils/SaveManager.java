package muga.thegreatuniversity.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import muga.thegreatuniversity.models.University;

/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class SaveManager {

    public static String SAVE_FILE = "Save.json";

    public static void saveUniversity(Context context){
        String toWrite = "Impossible To save";
        try {
            JSONObject uni = University.get().getAsJSON();
            toWrite = uni.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(SAVE_FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(toWrite);
            outputStreamWriter.close();

            Logger.info("Save write : " + toWrite);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void loadUniversity(Context context){
        try {
            InputStream inputStream = context.openFileInput(SAVE_FILE);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                try {
                    String save = stringBuilder.toString();
                    University.get().loadJSON(new JSONObject(save));
                    Logger.info("Save read : "+save);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
