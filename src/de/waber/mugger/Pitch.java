package de.waber.mugger;


import java.util.Random;

public enum Pitch {

    //REST, A4, A4$, B4, C4, C4$, D4, D4$, E4, F4, F4$, G4, G4$, A5;
    C4(261.63), D4(293.67), E4(329.63), F4(349.23), G4(392), A4(440), B4(493.88);

    private static final Random random = new Random();

    private double frequency;

    Pitch(double frequency) {
        this.frequency = frequency;
    }

    public double getFrequency() {
        return frequency;
    }

    public static Pitch random() {
        int index = random.nextInt(Pitch.values().length - 1);
        return Pitch.values()[index];
    }

}
