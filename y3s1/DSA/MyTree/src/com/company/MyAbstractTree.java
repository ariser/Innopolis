package com.company;

abstract class MyAbstractTree<E> implements MyTree<E> {

    protected int size = 0;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return getSize() == 0;
    }

    abstract static class Node<T> implements MyTree.Node<T> {
        T value;

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }
}
