package main;

public class PointInPolygon {
    public static boolean isPointInPolygon(MyLinkedList<Coords> polygon, Coords point) {
        for (Coords vertex : polygon) {

        }

        return false;
    }

    public static boolean intersects(Coords a, Coords b, Coords c, Coords d) {
        double[][] A = new double[2][2];
        A[0][0] = b.x - a.x;
        A[1][0] = b.y - a.y;
        A[0][1] = c.x - d.x;
        A[1][1] = c.y - d.y;

        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];

        double detU = (c.x - a.x) * A[1][1] - (c.y - a.y) * A[0][1];
        double detV = A[0][0] * (c.y - a.y) - A[1][0] * (c.x - a.x);

        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }
}
