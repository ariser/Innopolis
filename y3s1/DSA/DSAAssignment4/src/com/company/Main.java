package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {
    public interface MyTree<E> {
        Node<E> getRoot();

        void setRoot(Node<E> node);

        void addRoot(E value);

        void traversalPreorder(Consumer<Node<E>> callback);

        void traversalPostorder(Consumer<Node<E>> callback);

        interface Node<T> {
            void setValue(T value);

            T getValue();
        }
    }

    abstract static class MyAbstractTree<E> implements MyTree<E> {

        protected int size = 0;

        public int getSize() {
            return size;
        }

        public boolean isEmpty() {
            return getSize() == 0;
        }

        abstract class Node<T> implements MyTree.Node<T> {
            T value;

            public T getValue() {
                return value;
            }

            public void setValue(T value) {
                this.value = value;
            }
        }
    }

    public static class MyBinaryTree<E> extends MyAbstractTree<E> implements MyTree<E> {
        protected Node<E> root = null;

        MyBinaryTree() {
            this.size = 0;
        }

        MyBinaryTree(E rootValue) {
            this.root = new Node<>(null, rootValue);
            this.size = 1;
        }

        @Override
        public Node<E> getRoot() {
            return root;
        }

        @Override
        public void setRoot(MyTree.Node<E> node) {
            this.root = (Node<E>) node;
        }

        @Override
        public void addRoot(E value) {
            this.root = new Node<>(null, value);
        }

        public Node<E> addLeft(Node<E> node, E value) {
            Node<E> newNode = new Node<>(node, value);
            Node<E> prevNode = node.setLeftChild(newNode);
            if (prevNode == null) {
                size++;
            }
            return prevNode;
        }

        public Node<E> addRight(Node<E> node, E value) {
            Node<E> newNode = new Node<>(node, value);
            Node<E> prevNode = node.setRightChild(newNode);
            if (prevNode == null) {
                size++;
            }
            return prevNode;
        }

        public int height() {
            return height(getRoot());
        }

        public int height(Node<E> node) {
            if (node == null) return -1;
            return Math.max(height(node.getLeftChild()), height(node.getRightChild())) + 1;
        }

        public void set(Node<E> node, E value) {
            node.setValue(value);
        }

        public void remove(Node<E> node) throws IllegalArgumentException {
            if (node.getLeftChild() != null && node.getRightChild() != null) {
                throw new IllegalArgumentException("The node has 2 children");
            }
            Node<E> child = node.getLeftChild() != null ? node.getLeftChild() : node.getRightChild();
            if (child != null) {
                child.setParent(node.getParent());
            }
            if (node == root) {
                root = child;
            } else {
                Node<E> parent = node.getParent();
                if (node == parent.getLeftChild()) {
                    parent.setLeftChild(child);
                } else {
                    parent.setRightChild(child);
                }
            }
        }

        // region traversals
        @Override
        public void traversalPreorder(Consumer<MyTree.Node<E>> callback) {
            performTraversalPreorder(getRoot(), callback);
        }

        protected void performTraversalPreorder(Node<E> node, Consumer<MyTree.Node<E>> callback) {
            if (node != null) {
                callback.accept(node);
                performTraversalPreorder(node.getLeftChild(), callback);
                performTraversalPreorder(node.getRightChild(), callback);
            }
        }

        @Override
        public void traversalPostorder(Consumer<MyTree.Node<E>> callback) {
            performTraversalPostorder(getRoot(), callback);
        }

        protected void performTraversalPostorder(Node<E> node, Consumer<MyTree.Node<E>> callback) {
            if (node != null) {
                performTraversalPostorder(node.getLeftChild(), callback);
                performTraversalPostorder(node.getRightChild(), callback);
                callback.accept(node);
            }
        }

        public void traversalInorder(Consumer<MyTree.Node<E>> callback) {
            performTraversalInorder(getRoot(), callback);
        }

        protected void performTraversalInorder(Node<E> node, Consumer<MyTree.Node<E>> callback) {
            if (node != null) {
                performTraversalPostorder(node.getLeftChild(), callback);
                callback.accept(node);
                performTraversalPostorder(node.getRightChild(), callback);
            }
        }
        // endregion

        public class Node<T> extends MyAbstractTree<T>.Node<T> implements MyTree.Node<T> {
            protected MyBinaryTree<T> tree;
            protected Node<T> leftChild = null;
            protected Node<T> rightChild = null;
            protected Node<T> parent = null;

            Node(Node<T> parent, T value) {
                setValue(value);
                setParent(parent);
            }

            public Node<T> getLeftChild() {
                return leftChild;
            }

            public Node<T> getRightChild() {
                return rightChild;
            }

            public Node<T> getParent() {
                return parent;
            }

            protected void setParent(Node<T> node) {
                this.parent = node;
            }

            protected Node<T> setLeftChild(Node<T> child) throws IllegalArgumentException {
                for (Node<T> ancestor = this; ancestor != null; ancestor = ancestor.getParent()) {
                    if (ancestor == child) {
                        throw new IllegalArgumentException("Cycle in the tree");
                    }
                }

                Node<T> prevChild = getLeftChild();
                this.leftChild = child;
                if (child != null) {
                    child.setParent(this);
                }
                return prevChild;
            }

            protected Node<T> setRightChild(Node<T> child) throws IllegalArgumentException {
                for (Node<T> ancestor = this; ancestor != null; ancestor = ancestor.getParent()) {
                    if (ancestor == child) {
                        throw new IllegalArgumentException("Cycle in the tree");
                    }
                }

                Node<T> prevChild = getRightChild();
                this.rightChild = child;
                if (child != null) {
                    child.setParent(this);
                }
                return prevChild;
            }

            public void detachFromParent() {
                if (parent != null) {
                    if (parent.getLeftChild() == this) {
                        parent.leftChild = null;
                    }
                    if (parent.getRightChild() == this) {
                        parent.rightChild = null;
                    }
                    this.parent = null;
                }
            }
        }
    }

    public static class MyBinarySearchTree<E extends Comparable<E>> {
        protected MyBinaryTree<E> binTree;

        MyBinarySearchTree() {
            binTree = new MyBinaryTree<>();
        }

        MyBinarySearchTree(E rootValue) {
            binTree = new MyBinaryTree<>(rootValue);
        }

        public MyBinaryTree<E>.Node<E> find(E value) {
            return doSearch(binTree.getRoot(), value);
        }

        protected MyBinaryTree<E>.Node<E> doSearch(MyBinaryTree<E>.Node<E> node, E value) {
            if (node == null) {
                return null;
            }

            E nodeValue = node.getValue();

            if (value.compareTo(nodeValue) == 0) {
                return node;
            } else if (value.compareTo(nodeValue) < 0) {
                return doSearch(node.getLeftChild(), value);
            } else {
                return doSearch(node.getRightChild(), value);
            }
        }

        public E min() {
            MyBinaryTree<E>.Node<E> node = binTree.getRoot();
            while (node.getLeftChild() != null) {
                node = node.getLeftChild();
            }
            return node == null ? null : node.getValue();
        }

        public E max() {
            MyBinaryTree<E>.Node<E> node = binTree.getRoot();
            while (node.getRightChild() != null) {
                node = node.getRightChild();
            }
            return node == null ? null : node.getValue();
        }

        public void insert(E value) {
            MyBinaryTree<E>.Node<E> root = binTree.getRoot();
            if (root == null) {
                binTree.addRoot(value);
            } else {
                insertRecursive(value, root);
            }
        }

        public E predecessor(E value) {
            MyBinaryTree<E>.Node<E> predecessor = find(value);
            while (predecessor.getRightChild() != null || predecessor.getLeftChild() != null) {
                if (predecessor.getRightChild() != null) {
                    predecessor = predecessor.getRightChild();
                } else {
                    predecessor = predecessor.getLeftChild();
                }
            }
            return predecessor.getValue().compareTo(value) == 0 ? predecessor.getParent().getValue() : predecessor.getValue();
        }

        public E successor(E value) {
            MyBinaryTree<E>.Node<E> successor = find(value);
            while (successor.getRightChild() != null || successor.getLeftChild() != null) {
                if (successor.getLeftChild() != null) {
                    successor = successor.getLeftChild();
                } else {
                    successor = successor.getRightChild();
                }
            }
            return successor.getValue().compareTo(value) == 0 ? successor.getParent().getValue() : successor.getValue();
        }

        protected void insertRecursive(E value, MyBinaryTree<E>.Node<E> node) {
            if (node == null) {
                return;
            }

            E nodeValue = node.getValue();

            if (value.compareTo(nodeValue) < 0) {
                if (node.getLeftChild() == null) {
                    binTree.addLeft(node, value);
                } else {
                    insertRecursive(value, node.getLeftChild());
                }
            } else if (value.compareTo(nodeValue) > 0) {
                if (node.getRightChild() == null) {
                    binTree.addRight(node, value);
                } else {
                    insertRecursive(value, node.getRightChild());
                }
            }
        }

        public void delete(E value) {
            MyBinaryTree<E>.Node<E> node = find(value);

            if (node == null) {
                return;
            }

            if (node.getLeftChild() == null && node.getRightChild() == null) {
                // node has no children
                binTree.remove(node);
            } else if (node.getLeftChild() == null) {
                // node has only right child
                if (node.getParent().getLeftChild() == node) {
                    // node is the left child of its parent
                    node.getParent().setLeftChild(node.getRightChild());
                } else {
                    // node is the right child of its parent
                    node.getParent().setRightChild(node.getRightChild());
                }
            } else if (node.getRightChild() == null) {
                // node has only left child
                if (node.getParent().getLeftChild() == node) {
                    // node is the left child of its parent
                    node.getParent().setLeftChild(node.getLeftChild());
                } else {
                    // node is the right child of its parent
                    node.getParent().setRightChild(node.getLeftChild());
                }
            } else {
                // node has two children
                MyBinaryTree<E>.Node<E> predecessor = node.getLeftChild();
                while (predecessor.getRightChild() != null || predecessor.getLeftChild() != null) {
                    if (predecessor.getRightChild() != null) {
                        predecessor = predecessor.getRightChild();
                    } else {
                        predecessor = predecessor.getLeftChild();
                    }
                }
                node.setValue(predecessor.getValue());
                binTree.remove(predecessor);
            }
        }

        public int size() {
            return binTree.getSize();
        }
    }

    public static class MyRBTree<E extends Comparable<E>> extends MyBinarySearchTree<E> {
        // very questionable solution, as long as we have to search the same key in the colors map as well,
        // which violates the RBTree purpose itself
        // but right now I can't extend the Node class with a color field, which would be the best solution.
        // This is a problem of initial architecture. Refactoring is needed.
        Map<E, Color> colors = new HashMap<>();

        @Override
        public void insert(E value) {
            super.insert(value);
            MyBinaryTree<E>.Node<E> newNode = find(value);
            setColor(newNode, newNode == binTree.getRoot() ? Color.Black : Color.Red);
            balanceInsertion(newNode);
        }

        @Override
        public void delete(E value) {
            deleteRecursive(binTree.getRoot(), value);
        }

        private void deleteRecursive(MyBinaryTree<E>.Node<E> node, E value) {
            if (node == null) {
                return;
            }

            if (value.compareTo(node.getValue()) < 0) {
                deleteRecursive(node.getLeftChild(), value);
            } else if (value.compareTo(node.getValue()) > 0) {
                deleteRecursive(node.getRightChild(), value);
            } else {
                if (node.getLeftChild() == null && node.getRightChild() == null) {
                    // node has no children
                    binTree.remove(node);
                } else if (node.getLeftChild() == null) {
                    // node has only right child
                    if (node.getParent().getLeftChild() == node) {
                        // node is the left child of its parent
                        node.getParent().setLeftChild(node.getRightChild());
                    } else {
                        // node is the right child of its parent
                        node.getParent().setRightChild(node.getRightChild());
                    }
                } else if (node.getRightChild() == null) {
                    // node has only left child
                    if (node.getParent().getLeftChild() == node) {
                        // node is the left child of its parent
                        node.getParent().setLeftChild(node.getLeftChild());
                    } else {
                        // node is the right child of its parent
                        node.getParent().setRightChild(node.getLeftChild());
                    }
                } else {
                    // node has two children
                    MyBinaryTree<E>.Node<E> predecessor = node.getLeftChild();
                    while (predecessor.getRightChild() != null || predecessor.getLeftChild() != null) {
                        if (predecessor.getRightChild() != null) {
                            predecessor = predecessor.getRightChild();
                        } else {
                            predecessor = predecessor.getLeftChild();
                        }
                    }
                    node.setValue(predecessor.getValue());
                    deleteRecursive(predecessor, predecessor.getValue());
                }
            }

            balanceDeletion(node);
        }

        private void leftRotate(MyBinaryTree<E>.Node<E> node) {
            MyBinaryTree<E>.Node<E> rightChild = node.getRightChild();
            MyBinaryTree<E>.Node<E> rightChildLeftChild = rightChild.getLeftChild();

            if (binTree.getRoot() == node) {
                binTree.setRoot(rightChild);
                rightChild.setParent(null);
            } else {
                if (node.getParent().getLeftChild() == node) {
                    node.getParent().setLeftChild(rightChild);
                } else if (node.getParent().getRightChild() == node) {
                    node.getParent().setRightChild(rightChild);
                }
            }
            rightChild.setLeftChild(node);
            node.setRightChild(rightChildLeftChild);
        }

        private void rightRotate(MyBinaryTree<E>.Node<E> node) {
            MyBinaryTree<E>.Node<E> leftChild = node.getLeftChild();
            MyBinaryTree<E>.Node<E> leftChildRightChild = leftChild.getRightChild();

            if (binTree.getRoot() == node) {
                binTree.setRoot(leftChild);
                leftChild.setParent(null);
            } else {
                if (node.getParent().getLeftChild() == node) {
                    node.getParent().setLeftChild(leftChild);
                } else if (node.getParent().getRightChild() == node) {
                    node.getParent().setRightChild(leftChild);
                }
            }
            leftChild.setRightChild(node);
            node.setLeftChild(leftChildRightChild);
        }

        private void balanceInsertion(MyBinaryTree<E>.Node<E> newNode) {
            if (newNode == null) {
                return;
            }

            if (getColor(newNode.getParent()) == Color.Black) {
                return;
            }

            MyBinaryTree<E>.Node<E> parent = newNode.getParent();
            MyBinaryTree<E>.Node<E> grandpa = parent.getParent();
            MyBinaryTree<E>.Node<E> uncle = grandpa.getLeftChild() == parent ? grandpa.getRightChild() : grandpa.getLeftChild();

            if (getColor(parent) == Color.Red && getColor(uncle) == Color.Red) {
                setColor(parent, Color.Black);
                setColor(uncle, Color.Black);
                setColor(grandpa, Color.Red);

                if (grandpa == binTree.getRoot()) {
                    setColor(grandpa, Color.Black);
                } else {
                    if (getColor(grandpa.getParent()) == Color.Red) {
                        balanceInsertion(grandpa);
                    }
                }
            } else if (getColor(uncle) == Color.Black) {
                if (newNode == parent.getLeftChild()) {
                    if (parent == grandpa.getRightChild()) {
                        rightRotate(parent);
                        balanceInsertion(newNode);
                    } else {
                        rightRotate(grandpa);
                        setColor(grandpa, Color.Red);
                        setColor(parent, Color.Black);
                    }
                } else {
                    if (parent == grandpa.getLeftChild()) {
                        leftRotate(parent);
                        balanceInsertion(newNode);
                    } else {
                        leftRotate(grandpa);
                        setColor(grandpa, Color.Red);
                        setColor(parent, Color.Black);
                    }
                }
            }
        }

        private void balanceDeletion(MyBinaryTree<E>.Node<E> node) {
        }

        private void setColor(MyBinaryTree<E>.Node<E> node, Color color) {
            colors.put(node.getValue(), color);
        }

        private Color getColor(MyBinaryTree<E>.Node<E> node) {
            if (node == null) return Color.Black;
            return colors.get(node.getValue());
        }

        private enum Color {
            Red, Black, DoubleBlack
        }
    }

    private static final int MILLISECONDS_IN_SECOND = 1000;
    private static final int SECONDS_IN_MINUTE = 60;
    private static final int MILLISECONDS_IN_MINUTE = MILLISECONDS_IN_SECOND * SECONDS_IN_MINUTE;

    public static void main(String[] args) {
        List<String> result = new ArrayList<>();
        MyRBTree<Point> mins = new MyRBTree<>();
        Point last = null;
        try {
            Scanner in = new Scanner(new File("data.in"), "utf-8");

            int readRecords = 0;
            while (in.hasNext()) {
                double x = in.nextDouble();
                double y = in.nextDouble();
                last = new Point(x, y);
                mins.insert(last);
                readRecords++;
                if (readRecords == MILLISECONDS_IN_MINUTE) {
                    result.add(evaluate(mins));
                    readRecords = 0;
                }
            }
            if (readRecords != 0) {
                result.add(evaluate(mins));
            }

            result.add(findClosestToLast(mins, last));

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            return;
        }

        writeResult(String.join(" ", result));
    }

    private static String evaluate(MyRBTree<Point> records) {
        return records.min() + " " + records.max();
    }

    private static String findClosestToLast(MyRBTree<Point> records, Point last) {
        Point predecessor = records.predecessor(last);
        int preDist = predecessor == null ? Integer.MAX_VALUE : predecessor.distance();
        Point successor = records.successor(last);
        int sucDist = successor == null ? Integer.MAX_VALUE : successor.distance();
        return String.valueOf(Math.min(preDist, sucDist));
    }

    private static void writeResult(String result) {
        File out = new File("data.out");
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
            bw.write(result);
            bw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class Point implements Comparable<Point> {
        double x;
        double y;

        Point(double x, double y) {
            this.x = x;
            this.y = y;
        }

        public int distance() {
            return (int) Math.round(Math.sqrt(x * x + y * y));
        }

        @Override
        public String toString() {
            return String.valueOf(distance());
        }

        @Override
        public int compareTo(Point o) {
            return distance() - o.distance();
        }
    }
}
