package ru.auto.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DistanceTest {

    @Test
    public void testDistance1 () {
        Point first = new Point(3.0, -2.0);
        Point second = new Point(3.0, 5.0);
        double dist = Point.distance(first, second);
        Assert.assertEquals(dist,7.0);
    }

    @Test
    public void testDistance2 () {
        Point first = new Point(1.5, -1.0);
        Point second = new Point(2.0, 3.0);
        double dist = DistCalc.distance(first, second);
        Assert.assertEquals(Math.rint(100.0 * dist) / 100.0, 4.03);
    }
}
