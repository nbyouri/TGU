package muga.thegreatuniversity.lists.enums;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.utils.Context;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum CourseType {
    MAG(Context.getString(R.string.courseType_magistral));

    //LAB_SC(Context.getString(R.string.courseType_scienceLab)),
    //LAB_AG(Context.getString(R.string.courseType_agronomyLab)),
    //LAB_IT(Context.getString(R.string.courseType_ITLab));

    private String name = "";
    private static final Map<String, CourseType> lookup = new HashMap<>();

    static {
        for(CourseType s : EnumSet.allOf(CourseType.class))
            lookup.put(s.getName(), s);
    }

    public static CourseType getEnum(String type) {
        return lookup.get(type);
    }

    CourseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
