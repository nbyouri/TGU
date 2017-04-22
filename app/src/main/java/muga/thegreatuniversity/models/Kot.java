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

public class Kot implements SavableLoadableJSON {
    private String name;
    private int capacity;
    private int income;
    private int price;
    private KotType type;

    public Kot() {}

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

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price;}

    public int getIncome() { return income; }

    public void setIncome(int income) { this.income = income;}

    public KotType getType() {
        return type;
    }

    public void setType(KotType type) {
        this.type = type;
    }

    public static ArrayList<Kot> getKot() {
        ArrayList<Kot> kots = new ArrayList<>();
        kots.add(new Kot("basic", 10, 10, 10000, KotType.BASIC));
        kots.add(new Kot("building",50, 50, 50000, KotType.BUILDING));
        return kots;
    }


    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("name", this.name);
        obj.put("capacity", this.capacity);
        obj.put("kotType", this.type.getName());
        obj.put("price", this.price);
        obj.put("income", this.income);

        return obj;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.capacity = jsonO.getInt("capacity");
        this.type = KotType.getEnum(jsonO.getString("kotType"));
        this.price = jsonO.getInt("price");
        this.income = jsonO.getInt("income");
    }
}
