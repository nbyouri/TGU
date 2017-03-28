package muga.thegreatuniversity.models;

/**
 * Created on 28/03/17 .
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class Properties {

    private String name;
    private int basicPopularity;
    private int money;
    private int moral;
    private int studentNb;
    private int week;

    public Properties(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBasicPopularity() {
        return basicPopularity;
    }

    public void setBasicPopularity(int basicPopularity) {
        this.basicPopularity = basicPopularity;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        if(money < 0) {
            this.money = 0;
        }
        else {
            this.money = money;
        }
    }

    public int getMoral() {
        return moral;
    }

    public void setMoral(int moral) {
        this.moral = moral;
    }

    public int getStudentNb() {
        return studentNb;
    }

    public void setStudentNb(int studentNb) {
        if (studentNb > University.get().getMaxPopulation()) {
            this.studentNb = University.get().getMaxPopulation();
        }
        this.studentNb = studentNb;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }
}
