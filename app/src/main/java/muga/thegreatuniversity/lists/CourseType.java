package muga.thegreatuniversity.lists;

/**
 * Created on 20/02/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
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
