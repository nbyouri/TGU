package muga.thegreatuniversity.lists;

import java.util.ArrayList;

/**
 * Created on 10/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */


public class Assets {
    private ArrayList<String> wordListAdjectives;
    private ArrayList<String> wordListAnimals;

    public Assets() {
        this.wordListAdjectives = new ArrayList<>();
        this.wordListAnimals = new ArrayList<>();
    }

    private static class AssetsHolder {
        private final static Assets instance = new Assets();
    }

    public static Assets get() {
        return AssetsHolder.instance;
    }

    public ArrayList<String> getWordListAdjectives() {
        return wordListAdjectives;
    }

    public void setWordListAdjectives(ArrayList<String> wordListAdjectives) {
        this.wordListAdjectives = wordListAdjectives;
    }

    public ArrayList<String> getWordListAnimals() {
        return wordListAnimals;
    }

    public void setWordListAnimals(ArrayList<String> wordListAnimals) {
        this.wordListAnimals = wordListAnimals;
    }
}
