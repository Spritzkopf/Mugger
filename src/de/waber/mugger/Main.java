package de.waber.mugger;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import java.util.concurrent.LinkedBlockingQueue;

public class Main {

    private static final int SAMPLE_RATE = 16 * 1024;
    private static final double BPM = 100;

    public static void main(String[] args) throws LineUnavailableException, InterruptedException {
        final AudioFormat af =
                new AudioFormat(SAMPLE_RATE, 8, 1, true, true);
        SourceDataLine line = AudioSystem.getSourceDataLine(af);
        line.open(af, SAMPLE_RATE);
        line.start();

        LinkedBlockingQueue<Note> noteQueue = new LinkedBlockingQueue<>(100);

        Thread t1 = new Thread(() -> {
            while (true) {
                try {
                    noteQueue.put(Note.random(SAMPLE_RATE, BPM));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            while (true) {
                try {
                    noteQueue.take().play(line);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();

        line.drain();
        line.close();
    }

}
