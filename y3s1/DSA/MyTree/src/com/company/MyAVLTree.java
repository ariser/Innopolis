package com.company;

public class MyAVLTree<E extends Comparable<E>> extends MyBinarySearchTree<E> {

    @Override
    public void add(E value) {
        super.add(value);
        balanceInsertion(find(value));
    }

    @Override
    public void delete(E value) {
        deleteRecursive(binTree.getRoot(), value);
    }

    private void deleteRecursive(MyBinaryTree.Node<E> node, E value) {
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
                MyBinaryTree.Node<E> predecessor = node.getLeftChild();
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

    private boolean isBalanced(MyBinaryTree.Node<E> node) {
        return Math.abs(binTree.height(node.getLeftChild()) - binTree.height(node.getRightChild())) <= 1;
    }

    /**
     * < -1 if the left subtree is higher
     * > 1 if the right subtree is higher
     */
    private int getBalance(MyBinaryTree.Node<E> node) {
        return binTree.height(node.getLeftChild()) - binTree.height(node.getRightChild());
    }

    private void leftRotate(MyBinaryTree.Node<E> node) {
        MyBinaryTree.Node<E> rightChild = node.getRightChild();
        MyBinaryTree.Node<E> rightChildLeftChild = rightChild.getLeftChild();

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

    private void rightRotate(MyBinaryTree.Node<E> node) {
        MyBinaryTree.Node<E> leftChild = node.getLeftChild();
        MyBinaryTree.Node<E> leftChildRightChild = leftChild.getRightChild();

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

    private MyBinaryTree.Node<E> getUnbalancedAncestor(MyBinaryTree.Node<E> node) {
        while (node != null) {
            if (!isBalanced(node)) return node;
            node = node.getParent();
        }
        return null;
    }

    private void balanceInsertion(MyBinaryTree.Node<E> newNode) {
        MyBinaryTree.Node<E> unbalancedNode = getUnbalancedAncestor(newNode);

        if (unbalancedNode == null) return;

        int balance = getBalance(unbalancedNode);

        if (balance > 1) {
            if (newNode.getValue().compareTo(unbalancedNode.getLeftChild().getValue()) < 0) {
                rightRotate(unbalancedNode);
            } else if (newNode.getValue().compareTo(unbalancedNode.getLeftChild().getValue()) > 0) {
                leftRotate(unbalancedNode.getLeftChild());
                rightRotate(unbalancedNode);
            }
        } else if (balance < -1) {
            if (newNode.getValue().compareTo(unbalancedNode.getRightChild().getValue()) < 0) {
                rightRotate(unbalancedNode.getRightChild());
                leftRotate(unbalancedNode);
            } else if (newNode.getValue().compareTo(unbalancedNode.getRightChild().getValue()) > 0) {
                leftRotate(unbalancedNode);
            }
        }
    }

    private void balanceDeletion(MyBinaryTree.Node<E> node) {
        if (node == null) {
            return;
        }

        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.getLeftChild()) >= 0) {
                rightRotate(node);
            } else {
                leftRotate(node.getLeftChild());
                rightRotate(node);
            }
        } else if (balance < -1) {
            if (getBalance(node.getRightChild()) <= 0) {
                leftRotate(node);
            } else {
                rightRotate(node.getRightChild());
                leftRotate(node);
            }
        }
    }
}
