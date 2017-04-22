package muga.thegreatuniversity.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.enums.RoomType;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Room implements SavableLoadableJSON {
    private String name;
    private int capacity;
    private RoomType type;
    private int price;

    public Room() {}

    public Room(String name, int capacity, RoomType type, int price) {
        this.name = name;
        this.capacity = capacity;
        this.type = type;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public RoomType getType() {
        return type;
    }

    public int getPrice() { return price; }

    public static ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Classroom",20,RoomType.CLASS,500));
        rooms.add(new Room("Audience",100,RoomType.AUDIT,2000));
        return rooms;
    }

    @Override
    public JSONObject getAsJSON() throws JSONException {
        JSONObject obj = new JSONObject();

        obj.put("name", this.name);
        obj.put("capacity", this.capacity);
        obj.put("roomType", this.type.getName());
        obj.put("price", this.price);

        return obj;
    }

    @Override
    public void loadJSON(JSONObject jsonO) throws JSONException {
        this.name = jsonO.getString("name");
        this.capacity = jsonO.getInt("capacity");
        this.type = RoomType.getEnum(jsonO.getString("roomType"));
        this.price = jsonO.getInt("price");
    }
}
