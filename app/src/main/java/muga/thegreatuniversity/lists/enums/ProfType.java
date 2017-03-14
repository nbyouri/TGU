package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import muga.thegreatuniversity.utils.DistributedRandomGenerator;
import muga.thegreatuniversity.utils.Tools;

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
    // probablity per type
    private static DistributedRandomGenerator<ProfType> rnd = new DistributedRandomGenerator<>();

    // popularity ranges
    private static final int common_max_popularity = 50;
    private static final int uncommon_max_popularity = 70;
    private static final int rare_max_popularity = 80;
    private static final int very_rare_max_popularity = 90;
    private static final int legendary_max_popularity = 100;

    // age ranges
    private static final int min_age = 30;
    private static final int max_age = 90;

    static {
        for(ProfType r : EnumSet.allOf(ProfType.class))
            lookup.put(r.getName(), r);
        rnd.add(COMMON, 0.6d);
        rnd.add(UNCOMMON, 0.3d);
        rnd.add(RARE, 0.08d);
        rnd.add(VERY_RARE, 0.018d);
        rnd.add(LEGENDARY, 0.002d);
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

    public static ProfType getType() {
        return rnd.getDistributedRandom();
    }

    /* TODO: (get ranges : popularity, age, names), (probability of occurrence) */
    public int getPopularity() {
        int rnd = 0;
        switch (this) {
            case COMMON: return Tools.randInt(0, common_max_popularity);
            case UNCOMMON: return Tools.randInt(common_max_popularity, uncommon_max_popularity);
            case RARE: return Tools.randInt(uncommon_max_popularity, rare_max_popularity);
            case VERY_RARE: return Tools.randInt(rare_max_popularity, very_rare_max_popularity);
            case LEGENDARY: return Tools.randInt(very_rare_max_popularity, legendary_max_popularity);
            default: return rnd;
        }
    }

    public static int getAge() {
        return Tools.randInt(min_age, max_age);
    }



}
