package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.EntertainmentType;

/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Entertainment {
    private String name;
    private int price;
    private int moral;
    private EntertainmentType type;

    Entertainment() {}

    private Entertainment(String name, int price, int moral, EntertainmentType type) {
        this.name = name;
        this.price = price;
        this.moral = moral;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() { return price; }

    public int getMoral() { return moral; }

    public static ArrayList<Entertainment> getEntertainments() {
        ArrayList<Entertainment> entertainments = new ArrayList<>();
        entertainments.add(new Entertainment("Sports facilities", 1500, 15, EntertainmentType.SPORTS_FACILITIES));
        entertainments.add(new Entertainment("Library", 1000, 10, EntertainmentType.LIBRARY));
        entertainments.add(new Entertainment("Student club", 2500, 25, EntertainmentType.STUDENT_CLUB));
        entertainments.add(new Entertainment("Student party", 500, 5, EntertainmentType.STUDENT_PARTY));
        return entertainments;
    }

    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("price", this.price);
        obj.put("entertainmentType", this.type.getName());
        obj.put("moral", this.moral);

        return obj;
    }

    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.type = EntertainmentType.getEnum(jsonO.getString("entertainmentType"));
        this.price = jsonO.getInt("price");
        this.moral = jsonO.getInt("moral");
    }

}
