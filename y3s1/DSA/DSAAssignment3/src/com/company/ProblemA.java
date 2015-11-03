package com.company;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;


public class ProblemA {
    public interface MyTree<E> {
        Node<E> getRoot();

        void setRoot(E value);

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
        public void setRoot(E value) {
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
        private MyBinaryTree<E> binTree;

        MyBinarySearchTree() {
            binTree = new MyBinaryTree<>();
        }

        MyBinarySearchTree(E rootValue) {
            binTree = new MyBinaryTree<>(rootValue);
        }

        public MyBinaryTree<E>.Node<E> find(E value) {
            return doSearch(binTree.getRoot(), value);
        }

        private MyBinaryTree<E>.Node<E> doSearch(MyBinaryTree<E>.Node<E> node, E value) {
            if (node == null) {
                return null;
            }

            E nodeValue = node.getValue();

            if (value.equals(nodeValue)) {
                return node;
            } else if (value.compareTo(nodeValue) < 0) {
                return doSearch(node.getLeftChild(), value);
            } else {
                return doSearch(node.getRightChild(), value);
            }
        }

        public void insert(E value) {
            MyBinaryTree<E>.Node<E> root = binTree.getRoot();
            if (root == null) {
                binTree.setRoot(value);
            } else {
                insertRecursive(value, root);
            }
        }

        private void insertRecursive(E value, MyBinaryTree<E>.Node<E> node) {
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
            } else {
                if (node.getRightChild() == null) {
                    binTree.addRight(node, value);
                } else {
                    insertRecursive(value, node.getRightChild());
                }
            }
        }

        public boolean delete(final E value) {
            MyBinaryTree<E>.Node<E> node = find(value);

            if (node == null) {
                return false;
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

            return true;
        }

        public int size() {
            return binTree.getSize();
        }
    }

    public static void main(String[] args) {
        MyBinarySearchTree<Integer> tree = new MyBinarySearchTree<>();
        List<String> result = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("bst.in"), "utf-8");
            for (String num : in.nextLine().split("\\s+")) {
                tree.insert(Integer.parseInt(num));
            }

            for (String num : in.nextLine().split("\\s+")) {
                tree.delete(Integer.parseInt(num));
            }

            for (String num : in.nextLine().split("\\s+")) {
                MyBinaryTree<Integer>.Node<Integer> node = tree.find(Integer.parseInt(num));
                if (node != null) {
                    result.add(node.getRightChild() != null ? node.getRightChild().getValue().toString() : "null");
                } else {
                    result.add("null");
                }
            }

            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found.");
            return;
        }

        writeResult(String.join(" ", result));
    }

    private static void writeResult(String result) {
        File out = new File("bst.out");
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

}
