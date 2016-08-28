package com.example.cristi.fiborv.data;

import java.util.ArrayList;
import java.util.List;

public class FiboSource {

    /**
     * @param max largest number in the sequence. Must be >= 0
     * @return the longest Fibonacci sequence with the last value <= max
     * @throws IllegalArgumentException
     */
    public static List<Integer> getFibonacciSequence(int max) throws IllegalArgumentException {
        if (max < 0) throw new IllegalArgumentException("max must be >= 0");

        List<Integer> sequence = new ArrayList<>();
        sequence.add(0);

        if (max > 1) {
            long lastValue = 1;
            while(lastValue <= Integer.MAX_VALUE && lastValue <= max) {
                sequence.add((int) lastValue);
                lastValue += sequence.get(sequence.size() - 2);
            }
        } else if (max == 1) {
            sequence.add(1);
        }

        return sequence;
    }
}
