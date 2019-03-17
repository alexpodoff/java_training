package ru.auto.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    @Test
    public void testDistance1 () {
        Point first = new Point(3.0, -2.0);
        Point second = new Point(3.0, 5.0);
        Assert.assertEquals(first.distance(second), 7.0, 0.001);
    }

    @Test
    public void testDistance2 () {
        Point first = new Point(1.3, -1.1);
        Point second = new Point(2.0, 2.0);
        Assert.assertEquals(DistCalc.distance(first, second), 3.178, 0.001);
    }
}
