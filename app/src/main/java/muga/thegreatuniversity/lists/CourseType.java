package muga.thegreatuniversity.lists;

/**
 * Created by Remy on 21-02-17.
 */

public enum CourseType {
    MAG("@string/courseType_magistral"),
    LAB_SC("@string/scienceLab"),
    LAB_AG("@string/agronomyLab"),
    LAB_IT("@string/ITLab");

    private String name = "";

    CourseType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
