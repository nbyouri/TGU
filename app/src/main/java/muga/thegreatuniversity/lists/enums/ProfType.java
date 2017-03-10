package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 10/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum ProfType {
    COMMON("Common"),
    UNCOMMON("Uncommon"),
    RARE("Rare"),
    VERY_RARE("Very rare"),
    LEGENDARY("Legendary");

    private String name = "";
    private static final Map<String, ProfType> lookup = new HashMap<>();

    static {
        for(ProfType r : EnumSet.allOf(ProfType.class))
            lookup.put(r.getName(), r);
    }

    public static ProfType getEnum(String type) {
        return lookup.get(type);
    }


    public String getName() {
        return name;
    }

    ProfType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    /* TODO: (get ranges : popularity, age, names), (probability of occurrence) */

}
