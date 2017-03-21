package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10-03-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum EventActionType {

    ADD("add"),
    MULTIPLICATION("mul"),
    REMOVE("rem");

    private String name = "";
    private static final Map<String, EventActionType> lookup = new HashMap<>();

    static {
        for (EventActionType r : EnumSet.allOf(EventActionType.class))
            lookup.put(r.getName(), r);
    }

    public static EventActionType getEnum(String type) {
        return lookup.get(type);
    }

    public String getName() {
        return name;
    }

    EventActionType(String name) {
        this.name = name;
    }

    }
