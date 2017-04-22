package muga.thegreatuniversity.models;

/**
 * Created on 18-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class UniversityBasicData {

    private int basicPopularity;
    private int money;
    private int moral;
    private int studentNb;

    public int getBasicPopularity() {
        return basicPopularity;
    }

    public void setBasicPopularity(int basicPopularity) {
        if (basicPopularity < 1) {
            this.basicPopularity = 1;
        } else {
            this.basicPopularity = basicPopularity;
        }
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        if (moral > 100) {
            this.moral = 100;
        } else if (moral < 0) {
            this.moral = 0;
        } else {
            this.moral = moral;
        }
    }

    public int getStudentNb() {
        return studentNb;
    }

    public void setStudentNb(int studentNb, int maxPopulation) {
        if (studentNb > maxPopulation) {
            this.studentNb = maxPopulation;
        } else if (studentNb < 1) {
            this.studentNb = 1;
        } else {
            this.studentNb = studentNb;
        }
    }
}
