package muga.thegreatuniversity.lists;

import java.util.ArrayList;
import java.util.Random;

import muga.thegreatuniversity.models.events.Event;

/**
 * Created on 10/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */


public class Assets {
    private ArrayList<String> wordListAdjectives;
    private ArrayList<String> wordListAnimals;
    private ArrayList<String> MITcourses;
    private ArrayList<Event>  Events;

    public Assets() {
        this.wordListAdjectives = new ArrayList<>();
        this.wordListAnimals = new ArrayList<>();
        this.MITcourses = new ArrayList<>();
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

    public static String getRandomAdjective() {
        Random rng = new Random();
        int randomAdjective = rng.nextInt(Assets.get().getWordListAdjectives().size());
        return get().getWordListAdjectives().get(randomAdjective);
    }

    public ArrayList<String> getWordListAnimals() {
        return wordListAnimals;
    }

    public void setWordListAnimals(ArrayList<String> wordListAnimals) {
        this.wordListAnimals = wordListAnimals;
    }

    public static String getRandomAnimal() {
        Random rng = new Random();
        int randomAnimal = rng.nextInt(Assets.get().getWordListAnimals().size());
        return get().getWordListAnimals().get(randomAnimal);
    }

    public ArrayList<String> getMITcourses() {
        return MITcourses;
    }

    public void setMITcourses(ArrayList<String> MITcourses) {
        this.MITcourses = MITcourses;
    }

    public static String getRandomCourse() {
        Random rng = new Random();
        int randomCourse = rng.nextInt(Assets.get().getMITcourses().size());
        return get().getMITcourses().get(randomCourse);
    }
}
