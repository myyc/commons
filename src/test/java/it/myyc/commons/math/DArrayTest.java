package it.myyc.commons.math;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static junit.framework.Assert.assertEquals;

public class DArrayTest {
    DArray da;

    @Before
    public void setUp() {
        Set<Integer> set = new HashSet<>();

        set.add(1);
        set.add(2);
        set.add(3);
        set.add(4);
        set.add(100);

        da = new DArray(set);
    }

    @Test
    public void testMeanMedian() {
        assertEquals(da.median(), 3, 0.0001);
        assertEquals(da.mean(), 22, 0.0001);
    }
}
