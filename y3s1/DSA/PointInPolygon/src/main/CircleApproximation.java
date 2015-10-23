package main;

public class CircleApproximation {
    private int R;
    private int verticesNumber;

    private MyLinkedList<Coords> polygonVertices = new MyLinkedList<Coords>();

    CircleApproximation(int R, int verticesNumber) {
        this.R = R;
        this.verticesNumber = verticesNumber;

        calculate();
    }

    private void calculate() {
        double circumference = 2 * Math.PI;
        for (int i = 0; i < verticesNumber; i++) {
            double tetha = circumference / verticesNumber * i;
            polygonVertices.append(new Coords(R * Math.cos(tetha), R * Math.sin(tetha)));
        }
    }

    public MyLinkedList<Coords> getPolygonVertices() {
        return polygonVertices;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Coords vertex : polygonVertices) {
            sb.append(vertex).append("\n");
        }

        return sb.toString();
    }
}
