package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum EventValueType {
    STUDENT("student"),
    MONEY("money"),
    POPULARITY("popularity"),
    MORAL("moral"),
    WEEK("week"),
    PROF("prof");

    private String name = "";
    public String getName() {
        return name;
    }

    private static final Map<String, EventValueType> lookup = new HashMap<>();

    static {
        for (EventValueType r : EnumSet.allOf(EventValueType.class))
            lookup.put(r.getName(), r);
    }

    EventValueType(String name) {
        this.name = name;
    }

    public static EventValueType getEnum(String type) {
        return lookup.get(type);
    }
}
