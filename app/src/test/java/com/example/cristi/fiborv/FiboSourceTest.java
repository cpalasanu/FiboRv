package com.example.cristi.fiborv;

import com.example.cristi.fiborv.data.FiboSource;

import org.junit.Test;

import java.math.BigInteger;
import java.util.List;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;

public class FiboSourceTest {
    private static BigInteger MAX_GREATER_THAN_1 = BigInteger.valueOf(50000);
    private static BigInteger HUGE_MAX = new BigInteger("99999999999999999999999999999999999999999999999999999");

    @Test(expected = IllegalArgumentException.class)
    public void testFibonacciSequence_MaxIsNegative() {
        // GIVEN
        BigInteger max = BigInteger.valueOf(-1);

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        // exception thrown
    }

    @Test
    public void testFibonacciSequence_MaxIs_0() {
        // GIVEN
        BigInteger max = BigInteger.ZERO;

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        assertEquals(1, sequence.size());
        BigInteger firstItem = sequence.get(0);
        assertEquals(firstItem, BigInteger.ZERO);
    }

    @Test
    public void testFibonacciSequence_MaxIs_1() {
        // GIVEN
        BigInteger max = BigInteger.valueOf(1);

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        assertEquals(2, sequence.size());
        BigInteger firstItem = sequence.get(0);
        BigInteger secondItem = sequence.get(1);
        assertEquals(firstItem, BigInteger.ZERO);
        assertEquals(secondItem, BigInteger.valueOf(1));
    }

    @Test
    public void testFibonacciCondition_MaxGreaterThan_1() {
        // GIVEN
        BigInteger max = MAX_GREATER_THAN_1;

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkIsFibonacciSequence(sequence);
    }

    @Test
    public void testFibonacciCondition_MaxIntValue() {
        // GIVEN
        BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE);

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkIsFibonacciSequence(sequence);
    }

    @Test
    public void testFibonacciCondition_HugeValue() {
        // GIVEN
        BigInteger max = HUGE_MAX;

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkIsFibonacciSequence(sequence);
    }

    private void checkIsFibonacciSequence(List<BigInteger> sequence) {
        for (int i = 2; i < sequence.size(); i++) {
            BigInteger currentNumber = sequence.get(i);
            BigInteger sum2PreviousNumbers = sequence.get(i - 1).add(sequence.get(i - 2));
            assertEquals(currentNumber, sum2PreviousNumbers);
        }
    }

    @Test
    public void testLastNumber_isLessThanMax_MaxGreaterThan_1() {
        // GIVEN
        BigInteger max = MAX_GREATER_THAN_1;

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkLastNumberLessThanMax(sequence, max);
    }

    @Test
    public void testLastNumber_isLessThanMax_MaxIntValue() {
        // GIVEN
        BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE);

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkLastNumberLessThanMax(sequence, max);
    }

    @Test
    public void testLastNumber_isLessThanMax_HugeValue() {
        // GIVEN
        BigInteger max = HUGE_MAX;

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkLastNumberLessThanMax(sequence, max);
    }

    private void checkLastNumberLessThanMax(List<BigInteger> sequence, BigInteger max) {
        BigInteger lastNumber = sequence.get(sequence.size() - 1);
        assertTrue(lastNumber.compareTo(max) <= 0);
    }

    @Test
    public void testNextNumber_isNotLessThanMax_MaxGreaterThan_1() {
        // GIVEN
        BigInteger max = MAX_GREATER_THAN_1;

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkNextNumberNotLessThanMax(sequence, max);
    }

    @Test
    public void testNextNumber_isNotLessThanMax_MaxIntValue() {
        // GIVEN
        BigInteger max = BigInteger.valueOf(Integer.MAX_VALUE);

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkNextNumberNotLessThanMax(sequence, max);
    }

    @Test
    public void testNextNumber_isNotLessThanMax_HugeValue() {
        // GIVEN
        BigInteger max = HUGE_MAX;

        // WHEN
        List<BigInteger> sequence = FiboSource.getFibonacciSequence(max);

        // THEN
        checkNextNumberNotLessThanMax(sequence, max);
    }

    private void checkNextNumberNotLessThanMax(List<BigInteger> sequence, BigInteger max) {
        BigInteger lastNumber = sequence.get(sequence.size() - 1);
        BigInteger secondToLastNumber = sequence.get(sequence.size() - 2);
        BigInteger nextNumber = lastNumber.add(secondToLastNumber);
        assertFalse(nextNumber.compareTo(max) <= 0);
    }
}
