package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 28/02/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum EventType {
    DETERMINIST("determinist"), //Determinist event
    TWO_CHOICES("two_choices"); //Not determinist event

    private String name = "";
    private static final Map<String, EventType> lookup = new HashMap<>();

    static {
        for (EventType r : EnumSet.allOf(EventType.class))
            lookup.put(r.getName(), r);
    }

    public static EventType getEnum(String type) {
        return lookup.get(type);
    }

    public String getName() {
        return name;
    }

    EventType(String name) {
        this.name = name;
    }
}
