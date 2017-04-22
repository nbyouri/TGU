package muga.thegreatuniversity.utils;

import java.util.HashMap;

/**
 * Created on 14/03/2017.
 * Authors : Rime Antoine, Moers Tristan, Mouton Youri, Voet Rémy
 * Muga Copyright
 */

public class DistributedRandomGenerator<K> {

    private final HashMap<K, Double> distribution;
    private double distSum;

    public DistributedRandomGenerator() {
        distribution = new HashMap<>();
    }

    public void add(K value, double distribution) {
        if (this.distribution.get(value) != null) {
            distSum -= this.distribution.get(value);
        }
        this.distribution.put(value, distribution);
        distSum += distribution;
    }

    public K getDistributedRandom() {
        double rand = Math.random();
        double ratio = 1.0f / distSum;
        double tempDist = 0;
        for (K i : distribution.keySet()) {
            tempDist += distribution.get(i);
            if (rand / ratio <= tempDist) {
                return i;
            }
        }
        return null;
    }

}