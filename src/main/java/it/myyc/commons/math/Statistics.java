package it.myyc.commons.math;

/**
 * static stats methods referenced from DArray, can also be used on their own.
 */
public class Statistics {
    // the quantiles are a convex combination of the k-th and (k+1)-th value where p*n (where n is the size) is in
    // [k,k+1].
    public static double quantile(double[] arr, double p) {
        if (p < 0. || p > 1.) {
            throw new IllegalArgumentException("p needs to be between 0 and 1");
        }
        if(arr.length == 1) {
            return arr[0];
        }

        double idx = p * arr.length - 0.5;
        int a = (int) Math.floor(idx);
        double t = idx - a;
        int b = a + 1;

        if(b == arr.length) {
            b--;
            a--;
        }

        return (1 - t) * arr[a] + t * arr[b];
    }

    // the median is the 50% quantile by definition
    public static double median(double[] arr) {
        return quantile(arr, 0.5);
    }

    public static double mean(double[] arr) {
        double s = 0.;
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
        }
        return s / arr.length;
    }

    // biased divides by n, unbiased divides by (n-1)
    public static double variance(double[] arr, boolean biased) {
        if (arr.length == 1) {
            return arr[0];
        }
        double m = mean(arr);

        double s = 0.;
        for (int i = 0; i < arr.length; i++) {
            s += (arr[i] * arr[i] - m);
        }

        return s / (biased ? arr.length : arr.length - 1);
    }

    public static double variance(double[] arr) {
        return variance(arr, false);
    }

    public static double stddev(double[] arr) {
        return Math.sqrt(variance(arr, false));
    }

    public static double min(double[] arr) {
        return arr[0];
    }

    public static double max(double[] arr) {
        return arr[arr.length-1];
    }
}
