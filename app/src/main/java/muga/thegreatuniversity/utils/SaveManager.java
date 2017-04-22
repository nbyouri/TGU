package muga.thegreatuniversity.utils;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
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

    public static String UNIVERSITY_FILE = "Save.json";
    public static String SETTINGS_FILE = "Settings.json";

    public static void saveUniversity(Context context){
        String toWrite = "Impossible To save";
        try {
            JSONObject uni = University.get().getAsJSON();
            toWrite = uni.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(UNIVERSITY_FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(toWrite);
            outputStreamWriter.close();

            Logger.info("Save write University : " + toWrite);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean isFileExist(Context context, String fileName){
        File file = context.getFileStreamPath(fileName);
        return !(file == null || !file.exists());
    }

    public static void deleteFile(Context context, String fileName){
        if (isFileExist(context, fileName)) {
            File dir = context.getFilesDir();
            File file = new File(dir, "my_filename");
            if (!file.delete()){
                Logger.error("Impossible to Delete de save File");
            }
        }
    }

    public static boolean loadUniversity(Context context){
        try {
            InputStream inputStream = context.openFileInput(UNIVERSITY_FILE);

        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            StringBuilder stringBuilder = new StringBuilder();

            while ((receiveString = bufferedReader.readLine()) != null) {
                stringBuilder.append(receiveString);
            }

            inputStream.close();
            String save = stringBuilder.toString();
            University.get().loadJSON(new JSONObject(save));
            return true;
        }
        return false;
    }

    public static void saveSetting(Context context){
        String toWrite = "";
        try {
            JSONObject uni = TutorialManager.get().getAsJSON();
            toWrite = uni.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(SETTINGS_FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(toWrite);
            outputStreamWriter.close();

            Logger.info("Save write Setting : " + toWrite);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean loadSettings(Context context){
        try {
            InputStream inputStream = context.openFileInput(SETTINGS_FILE);

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
                    TutorialManager.get().loadJSON(new JSONObject(save));
                    Logger.info("Save read Settings : " + save);
                    return true;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
