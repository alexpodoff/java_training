package ru.auto.sandbox;

public class DistCalc {

    public static double distance(Point p1, Point p2) {
        return Math.sqrt(Math.pow(p2.p1 - p1.p1, 2) + Math.pow(p2.p2 - p1.p2, 2));
    }

    public static void main(String[] args) {

        Point first = new Point(1.0, 2.0);
        Point second = new Point(3.0, 5.0);

        // calculating distance with class method
        System.out.println("Distance with class Point method is: " + first.distance(second));

        Point third = new Point(2.0, 4.0);
        Point forth = new Point(4.2, 5.0);

        // calculating distance with static method
        System.out.println("Distance with static Point method is: "  + distance(third, forth));
    }
}
