package WeekThree;

import java.util.Comparator;

/**
 * Created by JiashuoWang on 8/3/14.
 */public class Point implements Comparable<Point> {

    // compare points by slope
    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            if (slopeTo(o1) < slopeTo(o2))
                return -1;
            else if (slopeTo(o1) > slopeTo(o2))
                return 1;
            else
                return 0;
        }
    };

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    // plot this point to standard drawing
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    public double slopeTo(Point that) {
        /* YOUR CODE HERE */
        if (this.x == that.x && this.y == that.y)
            return 0;
        else if (this.y == that.y)
            return 0;
        else if (this.x == that.x && this.y < that.y)
            return Double.POSITIVE_INFINITY;
        else if (this.x == that.x && this.y > that.y)
            return Double.NEGATIVE_INFINITY;
        else
            return (double)(this.y - that.y) / (this.x - that.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        if (this.y < that.y)
            return -1;
        else if (this.y > that.y)
            return 1;
        else if (this.y == that.y) {
            if (this.x < that.y)
                return -1;
            else if (this.x > that.x)
                return 1;
            else if (this.x == that.x)
                return 0;
        }
        return 0;
    }

    // return string representation of this point
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}
