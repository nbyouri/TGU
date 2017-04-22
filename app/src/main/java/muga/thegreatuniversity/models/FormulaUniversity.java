package muga.thegreatuniversity.models;

import muga.thegreatuniversity.utils.Tools;

/**
 * Created on 18-04-17.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class FormulaUniversity {

    private University university;

    public FormulaUniversity(University university){
        this.university = university;
    }

    public int newStudent(){
        int[] range = rangeNewStudent();
        return Math.round(Tools.randInt(range[0], range[1]));
    }

    public int[] rangeNewStudent(){
        double RAND_RANGE_STUDENT = 1;
        int popularity = university.getPopularity();

        double basicStudentNb = (university.getMoral() - 50) / 10; // between -5 -> 5

        double max = basicStudentNb + RAND_RANGE_STUDENT;
        double min = basicStudentNb - RAND_RANGE_STUDENT;

        double minRange = newStudentComputation(min, popularity, university.getStudentNb());
        double maxRange = newStudentComputation(max, popularity, university.getStudentNb());

        int[] range = new int[2];
        range[0] = (int)  Math.round(minRange);
        range[1] = (int) Math.round(maxRange);
        return range;
    }

    private double newStudentComputation(double base, int popularity, int studentNb){
        double variation = Math.log10(popularity); // 0 -> log10(popularity)

        if (base >= 0){
            base = base*variation; // ((moral - 50) / 10 ) * log10(popularity) > 0
        } else {
            base = base/variation; // ((moral - 50) / 10 ) / log10(popularity) < 0
        }
        if (studentNb > 0){
            base *= Math.log10(studentNb*10);
        }

        return base;
    }



}
