package main;

public class Coords {
    public double x;
    public double y;

    Coords(double x, double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
