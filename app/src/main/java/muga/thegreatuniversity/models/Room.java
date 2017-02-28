package muga.thegreatuniversity.models;

import java.util.ArrayList;

import muga.thegreatuniversity.lists.RoomType;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Room {
    private String name;
    private int capacity;
    private RoomType type;
    private int price;

    public Room(String name, int capacity, RoomType type, int price) {
        this.name = name;
        this.capacity = capacity;
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

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public ArrayList<Room> getRooms() {
        ArrayList<Room> rooms = new ArrayList<>();
        rooms.add(new Room("Classroom",20,RoomType.CLASS,500));
        rooms.add(new Room("Audience",100,RoomType.AUDIT,2000));
        rooms.add(new Room("Science laboratory",20,RoomType.LAB_SC,1000));
        rooms.add(new Room("Agronomy laboratory",20,RoomType.LAB_AG,1000));
        rooms.add(new Room("IT laboratory",20,RoomType.LAB_IT,1000));
        return rooms;
    }
}
