package muga.thegreatuniversity.lists;

import muga.thegreatuniversity.R;
import muga.thegreatuniversity.utils.Context;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public enum CourseType {
    MAG(Context.getString(R.string.courseType_magistral)),
    LAB_SC(Context.getString(R.string.courseType_scienceLab)),
    LAB_AG(Context.getString(R.string.courseType_agronomyLab)),
    LAB_IT(Context.getString(R.string.courseType_ITLab));

    private String name = "";

    CourseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
