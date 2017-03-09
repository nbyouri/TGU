package muga.thegreatuniversity.models;

import muga.thegreatuniversity.lists.ChoiceType;

/**
 * Created on 27-02-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Choice {
    private ChoiceType type;
    private String name;

    public Choice(ChoiceType type, String name){
        this.type = type;
        this.name = name;
    }

    public ChoiceType getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
