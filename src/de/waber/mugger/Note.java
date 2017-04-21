package de.waber.mugger;

import javax.sound.sampled.SourceDataLine;

public class Note {

    private byte[] sin;
    private int length;
    private double seconds;
    private double amplitude = 127;

    Note(int sampleRate, Pitch pitch, Duration duration, double bpm) {
        this.seconds = duration.getSeconds(bpm);
        this.length = (int) (sampleRate * seconds);
        this.sin = new byte[this.length];

        for (int i = 0; i < sin.length; i++) {
            double period = i * 1.0d / sampleRate;
            sin[i] = (byte) (amplitude * Math.sin(period * 2.0 * Math.PI * pitch.getFrequency()));
            if (i > sin.length - 10) {
                double damp = (sin.length - i - 1) / 10.0;
                sin[i] *= damp;
            }
        }
    }

    public void play(SourceDataLine line) {
        line.write(sin, 0, length);
    }

    public static Note random(int sampleRate, double bpm) {
        return new Note(sampleRate, Pitch.random(), Duration.random(), bpm);
    }

}