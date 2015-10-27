package com.company;

import java.util.function.Consumer;

public interface MyTree<E> {
    Node<E> getRoot();

    void traversalPreorder(Consumer<Node<E>> callback);

    void traversalPostorder(Consumer<Node<E>> callback);

    interface Node<T> {
        void setValue(T value);
        T getValue();
    }
}
