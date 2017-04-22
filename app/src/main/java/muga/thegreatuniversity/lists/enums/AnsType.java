package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 01/03/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum AnsType {
    YES("yes"),
    NO("no"),
    NONE("none");

    private String name = "";
    private static final Map<String, AnsType> lookup = new HashMap<>();

    static {
        for (AnsType r : EnumSet.allOf(AnsType.class))
            lookup.put(r.getName(), r);
    }

    public static AnsType getEnum(String type) {
        return lookup.get(type);
    }

    private String getName() {
        return name;
    }

    AnsType(String name) {
        this.name = name;
    }

}
