package com.example.cristi.fiborv;

import com.example.cristi.fiborv.data.FiboSource;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FiboSourceTest {
    private static int MAX_GREATER_THAN_1 = 50000;

    @Test(expected = IllegalArgumentException.class)
    public void testFibonacciSequence_MaxIsNegative() {
        // GIVEN
        int max = -1;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        // exception thrown
    }

    @Test
    public void testFibonacciSequence_MaxIs_0() {
        // GIVEN
        int max = 0;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        assertEquals(1, sequence.size());
        int firstItem = sequence.get(0);
        assertEquals(0, firstItem);
    }

    @Test
    public void testFibonacciSequence_MaxIs_1() {
        // GIVEN
        int max = 1;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        assertEquals(2, sequence.size());
        int firstItem = sequence.get(0);
        int secondItem = sequence.get(1);
        assertEquals(0, firstItem);
        assertEquals(1, secondItem);
    }

    @Test
    public void testFibonacciCondition_MaxGreaterThan_1() {
        // GIVEN
        int max = MAX_GREATER_THAN_1;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkIsFibonacciSequence(sequence);
    }

    @Test
    public void testFibonacciCondition_MaxIntValue() {
        // GIVEN
        int max = Integer.MAX_VALUE;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkIsFibonacciSequence(sequence);
    }

    private void checkIsFibonacciSequence(List<Integer> sequence) {
        for (int i = 2; i < sequence.size(); i++) {
            int currentNumber = sequence.get(i);
            int sum2PreviousNumbers = sequence.get(i - 1) + sequence.get(i - 2);
            assertEquals(currentNumber, sum2PreviousNumbers);
        }
    }

    @Test
    public void testLastNumber_isLessThanMax_MaxGreaterThan_1() {
        // GIVEN
        int max = MAX_GREATER_THAN_1;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkLastNumberLessThanMax(sequence, max);
    }

    @Test
    public void testLastNumber_isLessThanMax_MaxIntValue() {
        // GIVEN
        int max = Integer.MAX_VALUE;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkLastNumberLessThanMax(sequence, max);
    }

    private void checkLastNumberLessThanMax(List<Integer> sequence, int max) {
        int lastNumber = sequence.get(sequence.size() - 1);
        assertTrue(lastNumber <= max);
    }

    @Test
    public void testNextNumber_isNotLessThanMax_MaxGreaterThan_1() {
        // GIVEN
        int max = MAX_GREATER_THAN_1;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkNextNumberNotLessThanMax(sequence, max);
    }

    @Test
    public void testNextNumber_isNotLessThanMax_MaxIntValue() {
        // GIVEN
        int max = Integer.MAX_VALUE;

        // WHEN
        List<Integer> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkNextNumberNotLessThanMax(sequence, max);
    }

    private void checkNextNumberNotLessThanMax(List<Integer> sequence, int max) {
        long lastNumber = sequence.get(sequence.size() - 1);
        long secondToLastNumber = sequence.get(sequence.size() - 2);
        long nextNumber = lastNumber + secondToLastNumber;
        assertFalse(nextNumber < Integer.MAX_VALUE && nextNumber <= max);
    }
}
