package muga.thegreatuniversity.lists.enums;

import android.app.Fragment;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import muga.thegreatuniversity.models.events.Entertainment;

/**
 * Created by tristanmoers on 18/04/17.
 */

public enum EntertainmentType {
    SPORTS_FACILITIES("sports_facilities"),
    LIBRARY("librairy"),
    STUDENT_CLUB("student club");

    private String name = "";
    private static final Map<String, EntertainmentType> lookup = new HashMap<>();

    static {
        for(EntertainmentType r : EnumSet.allOf(EntertainmentType.class))
            lookup.put(r.getName(), r);
    }

    public static EntertainmentType getEnum(String type) {
        return lookup.get(type);
    }


    public String getName() {
        return name;
    }

    EntertainmentType(String name) {
        this.name = name;
    }

}
