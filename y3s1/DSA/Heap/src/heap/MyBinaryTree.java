package heap;

import java.util.function.Consumer;

public class MyBinaryTree<E> extends MyAbstractTree<E> implements MyTree<E> {
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
        this.root = (Node<E>)node;
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
