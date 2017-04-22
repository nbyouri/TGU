package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.EntertainmentType;

/**
 * Created by tristanmoers on 18/04/17.
 */

public class Entertainment implements SavableLoadableJSON {
    private String name;
    private int price;
    private int moral;
    private EntertainmentType type;

    public  Entertainment() {}

    public Entertainment(String name, int price, int moral, EntertainmentType type) {
        this.name = name;
        this.price = price;
        this.moral = moral;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() { return price; }

    public void setPrice(int price) { this.price = price;}

    public int getMoral() { return moral; }

    public void setMoral(int moral) { this.moral = moral;}

    public EntertainmentType getType() {
        return type;
    }

    public void setType(EntertainmentType type) {
        this.type = type;
    }

    public static ArrayList<Entertainment> getEntertainments() {
        ArrayList<Entertainment> entertainments = new ArrayList<>();
        entertainments.add(new Entertainment("Sports facilities", 1500, 15, EntertainmentType.SPORTS_FACILITIES));
        entertainments.add(new Entertainment("Library", 1000, 10, EntertainmentType.LIBRARY));
        entertainments.add(new Entertainment("Student club", 2500, 25, EntertainmentType.STUDENT_CLUB));
        entertainments.add(new Entertainment("Student party", 500, 5, EntertainmentType.STUDENT_PARTY));
        return entertainments;
    }

    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();
        obj.put("name", this.name);
        obj.put("price", this.price);
        obj.put("entertainmentType", this.type.getName());
        obj.put("moral", this.moral);

        return obj;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.type = EntertainmentType.getEnum(jsonO.getString("entertainmentType"));
        this.price = jsonO.getInt("price");
        this.moral = jsonO.getInt("moral");
    }

}
