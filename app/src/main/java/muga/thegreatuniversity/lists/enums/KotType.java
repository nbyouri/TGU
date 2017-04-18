package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by tristanmoers on 18/04/17.
 */

public enum KotType {
    BASIC ("basic"),
    BUILDING ("building");

    private String name = "";
    private static final Map<String, KotType> lookup = new HashMap<>();

    static {
        for(KotType r : EnumSet.allOf(KotType.class))
            lookup.put(r.getName(), r);
    }

    public static KotType getEnum(String type) {
        return lookup.get(type);
    }


    public String getName() {
        return name;
    }

    KotType(String name) {
        this.name = name;
    }
}
