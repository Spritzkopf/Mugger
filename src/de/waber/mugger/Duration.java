package de.waber.mugger;

import java.util.Random;

public enum Duration {

    WHOLE(1.0), HALF(0.5), QUARTER(0.25), EIGTH(0.125);

    private static final Random random = new Random();

    private double value;

    Duration(double value) {
        this.value = value;
    }

    public double getSeconds(double bpm) {
        return value / bpm * 60;
    }

    public static Duration random() {
        int index = random.nextInt(Duration.values().length - 1);
        return Duration.values()[index];
    }

}
