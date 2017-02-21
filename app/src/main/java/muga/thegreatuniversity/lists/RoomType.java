package muga.thegreatuniversity.lists;

/**
 * Created by Remy on 21-02-17.
 */

public enum RoomType {
    AUDIT ("@string/roomType_auditorium"),    // 100 people class
    CLASS ("@string/roomType_class"),         // 20 people generic class
    LAB_SC ("@string/courseType_scienceLab"), // needed for lab courses, 20 people max
    LAB_AG ("@string/courseType_agronomyLab"),// needed for lab courses, 20 people max
    LAB_IT ("@string/courseType_ITLab");      // needed for lab courses, 20 people max

    private String name = "";

    RoomType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
