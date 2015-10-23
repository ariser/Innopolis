package main;

import java.util.LinkedList;
import java.util.List;

public class CircleApproximation {
    private int R;
    private int verticesNumber;

    private List<Coords> polygonVertices = new LinkedList<Coords>();

    CircleApproximation(int R, int verticesNumber) {
        this.R = R;
        this.verticesNumber = verticesNumber;

        calculate();
    }

    private void calculate() {
        double circumference = 2 * Math.PI;
        for (int i = 0; i < verticesNumber; i++) {
            double tetha = circumference / verticesNumber * i;
            polygonVertices.add(new Coords(R * Math.cos(tetha), R * Math.sin(tetha)));
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Coords vertex : polygonVertices) {
            sb.append(vertex).append("\n");
        }

        return sb.toString();
    }

    private class Coords {
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
}
