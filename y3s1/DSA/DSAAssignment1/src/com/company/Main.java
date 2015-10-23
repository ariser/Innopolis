package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Scanner;

/**
 * This is a template code for Assignment #1
 * for DSA course: Bachelor-3
 *
 * @author Stanislav I. Protasov
 * @company Innopolis University
 */
public class Main {
    public static double eps = 1e-3;

    public static boolean RealEq(double a, double b) {
        return Math.abs(a - b) <= eps;
    }

    public static boolean RealMoreEq(double a, double b) {
        return a - b >= eps;
    }

    public static boolean EqPoint(Point2D a, Point2D b) {
        return RealEq(a.x, b.x) && RealEq(a.y, b.y);
    }

    public static boolean isOnTheLine(Point2D point, Point2D a, Point2D b) {
        if (EqPoint(a, b)) {
            return EqPoint(a, b);
        } else {
            return RealEq((point.x - a.x) * (b.y - a.y) - (point.y - a.y) * (b.x - a.x), 0) && RealMoreEq(point.x, a.x) && RealMoreEq(b.x, point.x) || RealMoreEq(point.x, b.x) && RealMoreEq(a.x, point.x);
        }
    }

    /**
     * Intersection method checks if section ab intersects with section cd.
     *
     * @param a section 1 point 1
     * @param b section 1 point 2
     * @param c section 2 point 1
     * @param d section 2 point 2
     * @return true if sections intersect
     */
    public static boolean intersects(Point2D a, Point2D b, Point2D c, Point2D d) {
        if (isOnTheLine(a, c, d) || isOnTheLine(b, c, d)) {
            return true;
        }

        double[][] A = new double[2][2];
        A[0][0] = b.getX() - a.getX();
        A[1][0] = b.getY() - a.getY();
        A[0][1] = c.getX() - d.getX();
        A[1][1] = c.getY() - d.getY();

        double det0 = A[0][0] * A[1][1] - A[1][0] * A[0][1];

        double detU = (c.getX() - a.getX()) * A[1][1] - (c.getY() - a.getY()) * A[0][1];
        double detV = A[0][0] * (c.getY() - a.getY()) - A[1][0] * (c.getX() - a.getX());

        double u = detU / det0;
        double v = detV / det0;
        return u > 0 && u < 1 && v > 0 && v < 1;
    }

    public static boolean isBetween(Point2D a, Point2D b, Point2D point) {
        if (point.x >= a.x && point.x <= b.x && point.y >= a.y && point.y <= b.y) {
            return true;
        }
        return false;
    }

    /**
     * gets a stack that contains all rectangles that have intersection with the line
     *
     * @param rects      rectangles to check
     * @param lineStart  starting point of the line
     * @param lineFinish end point of the line
     * @return
     */
    public static MyStack<Rectangle> getIntersected(MyList<Rectangle> rects, Point2D lineStart, Point2D lineFinish) {
        MyStack<Rectangle> intersected = new MyStack<Rectangle>();
        for (Rectangle rect : rects) {
            Point2D[][] edges = rect.getEdges();
            for (int i = 0; i < 4; i++) {
                if (intersects(edges[i][0], edges[i][1], lineStart, lineFinish) || isBetween(rect.lowerLeft, rect.upperTop, lineStart)) {
                    intersected.push(rect);
                    break;
                }
            }
        }
        return intersected;
    }

    /**
     * Method takes text and calculates rectangles that represent words
     *
     * @param text          input string
     * @param oneLetterSize size of one letter in monospace font
     * @param startCorner   corner where text starts
     * @return list of rectangles associated with words in text
     */
    public static MyList<Rectangle> getRectangles(String text, Point2D oneLetterSize, Point2D startCorner) {
        MyList<Rectangle> list = new MyList<Rectangle>();
        String[] words = text.split(" ");
        int position = 0;
        for (String word : words) {
            if (word.length() > 0) {
                Point2D ll = new Point2D(
                    startCorner.getX() + position * oneLetterSize.getX(),
                    startCorner.getY());
                Point2D ut = new Point2D(
                    ll.getX() + word.length() * oneLetterSize.getX(),
                    startCorner.getY() + oneLetterSize.getY());
                list.addLast(new Rectangle(ll, ut, word));
            }
            position += word.length() + 1;
        }
        return list;
    }

    public static void main(String[] args) {
        String inputData = "";
        Point2D textCorner, letterSize, lineStart, lineEnd;

        textCorner = letterSize = lineEnd = lineStart = new Point2D(0, 0);

        try {
            Scanner in = new Scanner(new File("browser.in"), "utf-8");

            inputData = in.nextLine();

            double textCornerX = Double.parseDouble(in.next());
            double textCornerY = Double.parseDouble(in.next());
            textCorner = new Point2D(textCornerX, textCornerY);

            double letterSizeX = Double.parseDouble(in.next());
            double letterSizeY = Double.parseDouble(in.next());

            letterSize = new Point2D(letterSizeX, letterSizeY);

            double lineStartX = Double.parseDouble(in.next());
            double lineStartY = Double.parseDouble(in.next());
            lineStart = new Point2D(lineStartX, lineStartY);

            double lineEndX = Double.parseDouble(in.next());
            double lineEndY = Double.parseDouble(in.next());
            lineEnd = new Point2D(lineEndX, lineEndY);

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
        }

        MyList<Rectangle> list = getRectangles(inputData, letterSize, textCorner);
        MyStack<Rectangle> stack = getIntersected(list, lineStart, lineEnd);
        MyList<String> result = new MyList<String>();
        while (!stack.isEmpty()) {
            String word = ((Rectangle) stack.pop()).getTag();
            result.addLast(word);
        }
        File out = new File("browser.out");
        if (!out.exists()) {
            try {
                out.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fos = new FileOutputStream(out, false);
            OutputStreamWriter osw = new OutputStreamWriter(fos, Charset.forName("utf-8"));
            BufferedWriter bw = new BufferedWriter(osw);
            bw.write(String.join(" ", result));
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This is a class that represents a real 2D point
     */
    public static class Point2D {
        private double x, y;

        public Point2D(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public double getX() {
            return x;
        }

        public double getY() {
            return y;
        }

        public String toString() {
            return "[" + x + ", " + y + "]";
        }
    }

    /**
     * This is a class that represents rectangle
     * in Cartesian coordinates
     */
    public static class Rectangle {
        /**
         * these are corner points of rectangle
         */
        private Point2D lowerLeft, upperTop;
        /**
         * this tag is used to store additional info
         * in our case - to save a word represented by rectangle
         */
        private String tag;

        /**
         * Constructor for rectangle
         *
         * @param lowerLeft lower left angle of rectangle
         * @param upperTop  upper top angle or rectangle
         * @param tag       word that is represented by rectangle
         */
        public Rectangle(Point2D lowerLeft, Point2D upperTop, String tag) {
            this.lowerLeft = lowerLeft;
            this.upperTop = upperTop;
            this.tag = tag;
        }

        public String getTag() {
            return tag;
        }

        @Override
        public String toString() {
            return lowerLeft.toString() + "-" + upperTop.toString();
        }

        /**
         * Methods returns 4 pairs of points. Each pair represents an edge or rectangle
         *
         * @return returns Point2D[4][2] array
         */
        public Point2D[][] getEdges() {
            Point2D[][] edges = new Point2D[4][2];
            edges[0][0] = lowerLeft;
            edges[0][1] = new Point2D(lowerLeft.getX(), upperTop.getY());
            edges[1][0] = edges[0][1];
            edges[1][1] = upperTop;
            edges[2][0] = upperTop;
            edges[2][1] = new Point2D(upperTop.getX(), lowerLeft.getY());
            edges[3][0] = edges[2][1];
            edges[3][1] = lowerLeft;
            return edges;
        }
    }

    public static class MyList<E> implements Iterable<E> {
        private Node<E> head = null;
        private Node<E> tail = null;

        private int size = 0;

        MyList() {
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public E first() {
            return head != null ? head.getElement() : null;
        }

        public E last() {
            return tail != null ? tail.getElement() : null;
        }

        public void add(int index, E element) throws IndexOutOfBoundsException {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index is out of bound: " + index);
            }

            if (index == 0) {
                head = new Node<E>(element, head);
                if (tail == null) {
                    tail = head;
                }
            } else {
                Node<E> currentNode = head;
                for (int currentIndex = 0; currentIndex < index - 1; currentIndex++) {
                    currentNode = currentNode.getNext();
                }

                Node<E> newNode = new Node<E>(element, currentNode.getNext());
                currentNode.setNext(newNode);

                if (index == size) {
                    tail = newNode;
                }
            }

            size++;
        }

        public void addFirst(E element) {
            add(0, element);
        }

        public void addLast(E element) {
            add(size, element);
        }

        public E delete(int index) throws IndexOutOfBoundsException {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index is out of bound: " + index);
            }

            E element = null;

            if (index == 0) {
                element = head.getElement();
                head = head.getNext();
                if (head == null) {
                    tail = null;
                }
            } else {
                Node<E> currentNode = head;
                for (int currentIndex = 0; currentIndex < index - 1; currentIndex++) {
                    currentNode = currentNode.getNext();
                }

                Node<E> targetNode = currentNode.getNext();
                element = targetNode.getElement();
                currentNode.setNext(targetNode.getNext());

                if (index == size - 1) {
                    tail = currentNode;
                }
            }

            size--;

            return element;
        }

        public E deleteFirst() {
            if (isEmpty()) {
                return null;
            }
            return delete(0);
        }

        public E deleteLast() {
            if (isEmpty()) {
                return null;
            }
            return delete(size - 1);
        }

        public E get(int index) throws IndexOutOfBoundsException {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index is out of bound: " + index);
            }

            Node<E> currentNode = head;
            for (int currentIndex = 0; currentIndex < index; currentIndex++) {
                currentNode = currentNode.getNext();
            }

            return currentNode.getElement();
        }

        public void update(int index, E element) throws IndexOutOfBoundsException {
            if (index < 0 || index > size) {
                throw new IndexOutOfBoundsException("Index is out of bound: " + index);
            }

            Node<E> currentNode = head;
            for (int currentIndex = 0; currentIndex < index; currentIndex++) {
                currentNode = currentNode.getNext();
            }

            currentNode.setElement(element);
        }

        @Override
        public Iterator<E> iterator() {
            return new MyIterator();
        }

        private class MyIterator implements Iterator<E> {
            private Node<E> lastNode;
            private Node<E> nextNode;

            MyIterator() {
                lastNode = null;
                nextNode = head;
            }

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public E next() {
                lastNode = nextNode;
                nextNode = nextNode.getNext();
                return lastNode.getElement();
            }
        }

        private class Node<E> {
            private E element;
            private Node<E> next;

            public Node(E element, Node<E> next) {
                this.element = element;
                this.next = next;
            }

            public E getElement() {
                return element;
            }

            public void setElement(E element) {
                this.element = element;
            }

            public Node<E> getNext() {
                return next;
            }

            public void setNext(Node<E> next) {
                this.next = next;
            }
        }
    }

    public static class MyStack<E> extends MyList {
        public void push(E o) {
            addFirst(o);
        }

        public Object pop() {
            return deleteFirst();
        }
    }

}
/**
 * guid 5bd29cec-2e7a-4c93-9112-7f0ad68865c4
 */