package it.myyc.commons.math;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collection;

/**
 * Wraps (almost) any sort of number Collection into a sorted array for statistical computations.
 * Create a DArray off a collection, get the mean, median, whatever you want.
 */
public class DArray {
    protected double[] arr;

    public DArray(Collection coll) {
        arr = new double[coll.size()];

        int k = 0;

        /*
         * Checks the type of the first element it gets. It assumes they're all the same (you could potentially use
         * a Collection<Object> filled with Double's and Long's and Integer's, but why would you?)
         */
        for (Object el : coll) {
            if (el instanceof Number) {
                if (el instanceof BigDecimal || el instanceof BigInteger || el instanceof Byte) {
                    throw new UnsupportedOperationException("DArrays with Big* or Bytes are not supported.");
                }
                arr[k] = ((Number) el).doubleValue();
                k++;
            } else {
                throw new UnsupportedOperationException("Unsupported type " + el.getClass());
            }
        }

        Arrays.sort(arr);
    }

    public int size() {
        return arr.length;
    }

    public double get(int idx) {
        return arr[idx];
    }

    public double quantile(double p) {
        return Statistics.quantile(arr, p);
    }

    public double median() {
        return Statistics.quantile(arr, 0.5);
    }

    public double mean() {
        return Statistics.mean(arr);
    }

    public double variance(boolean biased) {
        return Statistics.variance(arr, biased);
    }

    public double variance() {
        return Statistics.variance(arr, false);
    }

    public double stddev() {
        return Math.sqrt(Statistics.variance(arr, false));
    }

    public double min() {
        return arr[0];
    }

    public double max() {
        return arr[size() - 1];
    }

    /**
     * Prints out the first ten elements.
     *
     * @return the string representation
     */
    public String toString() {
        int bp = size() >= 10 ? 10 : size();

        String s = "[";

        for (int i = 0; i < bp; i++) {
            double v = arr[i];
            s += v + "" + (i <= bp - 2 ? ", " : "");
        }
        s += (size() <= 10 ? "" : ", ...") + "]";
        return s;
    }
}
