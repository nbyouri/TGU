package muga.thegreatuniversity.models;

/**
 * Created on 18-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

class UniversityBasicData {

    private int basicPopularity;
    private int money;
    private double moral;
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

    public void addMoney(int money){
        setMoney(this.money + money);
    }

    public void addMoral(double moral){
        setMoral(this.moral + moral);
    }

    public void addStudentNb(int studentNb, int maxPop){
        setStudentNb(this.studentNb + studentNb, maxPop);
    }

    public void addBasicPopularity(int basicPopularity){
        setBasicPopularity(this.basicPopularity = basicPopularity);
    }

    public double getMoral() {
        return moral;
    }

    public void setMoral(double moral) {
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
