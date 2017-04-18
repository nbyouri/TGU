package muga.thegreatuniversity.lists.enums;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.utils.Context;
import muga.thegreatuniversity.utils.DistributedRandomGenerator;
import muga.thegreatuniversity.utils.Tools;

/**
 * Created on 10/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum ProfType {
    COMMON("Common", 1),
    UNCOMMON("Uncommon", 2),
    RARE("Rare", 3),
    VERY_RARE("Very rare", 4),
    LEGENDARY("Legendary", 5);

    private String name = "";
    private int id = 0;
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

    // price ranges
    private static final int common_min_price = 50;
    private static final int common_max_price = 100;
    private static final int uncommon_min_price = 100;
    private static final int uncommon_max_price = 200;
    private static final int rare_min_price = 200;
    private static final int rare_max_price = 400;
    private static final int very_rare_min_price = 400;
    private static final int very_rare_max_price = 800;
    private static final int legendary_max_price = 500;

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

    public int getId() { return id; }

    ProfType(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public static ProfType getType() {
        return rnd.getDistributedRandom();
    }

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

    public int getPrice() {
        int rnd = 0;
        switch (this) {
            case COMMON: return Tools.randInt(common_min_price, common_max_price);
            case UNCOMMON: return Tools.randInt(uncommon_min_price, uncommon_max_price);
            case RARE: return Tools.randInt(rare_min_price, rare_max_price);
            case VERY_RARE: return Tools.randInt(very_rare_min_price, very_rare_max_price);
            case LEGENDARY: return Tools.randInt(uncommon_min_price, legendary_max_price);
            default: return rnd;
        }
    }

    public static int getAge() {
        return Tools.randInt(min_age, max_age);
    }

    public int getColor() {
        int color = Color.WHITE;
        switch (this) {
            case COMMON:
                color = ContextCompat.getColor(Context.getContext(), R.color.prof_common);
                break;
            case UNCOMMON:
                color = ContextCompat.getColor(Context.getContext(), R.color.prof_uncommon);
                break;
            case RARE:
                color = ContextCompat.getColor(Context.getContext(), R.color.prof_rare);
                break;
            case VERY_RARE:
                color = ContextCompat.getColor(Context.getContext(), R.color.prof_very_rare);
                break;
            case LEGENDARY:
                color =  ContextCompat.getColor(Context.getContext(), R.color.prof_legendary);
                break;
        }
        return color;
    }

    public int ranking() {
        return this.getId();
    }

    public static Map<String, ProfType> getLookup() {
        return lookup;
    }
}
