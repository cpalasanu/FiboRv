package com.example.cristi.fiborv.data;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class FiboSource {

    /**
     * @param max largest number in the sequence. Must be >= 0
     * @return the longest Fibonacci sequence with the last value <= max
     * @throws IllegalArgumentException
     */
    public static List<BigInteger> getFibonacciSequence(BigInteger max) throws IllegalArgumentException {
        if (max.compareTo(BigInteger.ZERO) < 0) throw new IllegalArgumentException("max must be >= 0");

        List<BigInteger> sequence = new ArrayList<>();
        sequence.add(BigInteger.valueOf(0));

        if (max.compareTo(BigInteger.valueOf(1)) > 0) {
            BigInteger lastValue = BigInteger.valueOf(1);
            while(lastValue.compareTo(max) < 0) {
                sequence.add(lastValue);
                lastValue = lastValue.add(sequence.get(sequence.size() - 2));
            }
        } else if (max.compareTo(BigInteger.valueOf(1)) == 0) {
            sequence.add(BigInteger.valueOf(1));
        }

        return sequence;
    }
}
