package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

// Interface how define a object savable as a JSON
public interface SavableLoadableJSON {

    JSONObject getAsJSON() throws JSONException;

    void loadJSON(JSONObject jsonO) throws JSONException;

}
