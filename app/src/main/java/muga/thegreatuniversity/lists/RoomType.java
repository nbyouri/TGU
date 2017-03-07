package muga.thegreatuniversity.lists;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.app.App;
import muga.thegreatuniversity.utils.Context;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */
public enum RoomType {
    AUDIT (Context.getString(R.string.roomType_auditorium)),    // 100 people class
    CLASS (Context.getString(R.string.roomType_class)),         // 20 people generic class
    LAB_SC (Context.getString(R.string.courseType_scienceLab)), // needed for lab courses, 20 people max
    LAB_AG (Context.getString(R.string.courseType_agronomyLab)),// needed for lab courses, 20 people max
    LAB_IT (Context.getString(R.string.courseType_ITLab));      // needed for lab courses, 20 people max

    private String name = "";
    private static final Map<String, RoomType> lookup = new HashMap<>();

    static {
        for(RoomType r : EnumSet.allOf(RoomType.class))
            lookup.put(r.getName(), r);
    }

    public static RoomType getEnum(String type) {
        return lookup.get(type);
    }


    public String getName() {
        return name;
    }

    RoomType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
