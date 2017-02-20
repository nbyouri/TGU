package muga.thegreatuniversity.models;

/**
 * Created by youri on 20/02/2017.
 */

enum roomType {
    AUDIT ("@string/roomType_auditorium"),    // 100 people class
    CLASS ("@string/roomType_class"),         // 20 people generic class
    LAB_SC ("@string/courseType_scienceLab"), // needed for lab courses, 20 people max
    LAB_AG ("@string/courseType_agronomyLab"),// needed for lab courses, 20 people max
    LAB_IT ("@string/courseType_ITLab");      // needed for lab courses, 20 people max

    private String name = "";

    roomType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}

public class Room {
    private String name;
    private int capacity;
    private roomType type;

    public Room(String name, int capacity, roomType type) {
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

    public roomType getType() {
        return type;
    }

    public void setType(roomType type) {
        this.type = type;
    }
}
