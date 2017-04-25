package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.KotType;


/**
 * Created on 06-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Kot {
    private String name;
    private int capacity;
    private int income;
    private int price;
    private KotType type;

    Kot() {}

    private Kot(String name, int capacity, int income, int price, KotType type) {
        this.name = name;
        this.capacity = capacity;
        this.price = price;
        this.income = income;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getPrice() { return price; }

    public int getIncome() { return income; }

    public static ArrayList<Kot> getKot() {
        ArrayList<Kot> kots = new ArrayList<>();
        kots.add(new Kot("residence", 20, 10, 1000, KotType.BASIC));
        kots.add(new Kot("building", 50, 50, 5000, KotType.BUILDING));
        return kots;
    }

    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("name", this.name);
        obj.put("capacity", this.capacity);
        obj.put("kotType", this.type.getName());
        obj.put("price", this.price);
        obj.put("income", this.income);

        return obj;
    }

    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.capacity = jsonO.getInt("capacity");
        this.type = KotType.getEnum(jsonO.getString("kotType"));
        this.price = jsonO.getInt("price");
        this.income = jsonO.getInt("income");
    }
}
