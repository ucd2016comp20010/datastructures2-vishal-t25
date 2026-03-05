package project20280.list;

import project20280.interfaces.List;

import java.util.Iterator;

public class CircularlyLinkedList<E> implements List<E> {

    private class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T e, Node<T> n) {
            data = e;
            next = n;
        }

        public T getData() {
            return data;
        }

        public void setNext(Node<T> n) {
            next = n;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    // default constructor
    public CircularlyLinkedList() {
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E get(int i) {
        if (i < 0 || i >= size) return null;
        Node<E> curr = tail.next; // head
        for (int j = 0; j < i; j++) {
            curr = curr.next;
        }
        return curr.getData();
    }

    /**
     * Inserts the given element at the specified index of the list, shifting all
     * subsequent elements in the list one position further to make room.
     *
     * @param i the index at which the new element should be stored
     * @param e the new element to be stored
     */
    @Override
    public void add(int i, E e) {
        if (i < 0 || i > size) throw new IndexOutOfBoundsException("position out of bounds");
        if (i == 0) {
            addFirst(e);
            return;
        }
        if (i == size) {
            addLast(e);
            return;
        }
        Node<E> prev = tail.next; // head
        for (int j = 0; j < i - 1; j++) {
            prev = prev.next;
        }
        Node<E> newNode = new Node<>(e, prev.next);
        prev.next = newNode;
        size++;
    }

    @Override
    public E remove(int i) {
        if (i < 0 || i >= size) return null;
        if (i == 0) return removeFirst();
        if (i == size - 1) return removeLast();
        Node<E> prev = tail.next; // head
        for (int j = 0; j < i - 1; j++) {
            prev = prev.next;
        }
        Node<E> removed = prev.next;
        prev.next = removed.next;
        size--;
        return removed.getData();
    }

    public void rotate() {
        if (tail != null) tail = tail.next;
    }

    private class CircularlyLinkedListIterator implements Iterator<E> {
        private Node<E> curr;
        private int remaining;

        public CircularlyLinkedListIterator() {
            this.curr = (tail == null) ? null : tail.next;
            this.remaining = size;
        }

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public E next() {
            E res = curr.getData();
            curr = curr.next;
            remaining--;
            return res;
        }

    }

    @Override
    public Iterator<E> iterator() {
        return new CircularlyLinkedListIterator();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E removeFirst() {
        if (tail == null) return null;
        Node<E> head = tail.next;
        E val = head.getData();
        if (head == tail) { // single element
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        return val;
    }

    @Override
    public E removeLast() {
        if (tail == null) return null;
        if (tail.next == tail) { // single element
            E val = tail.getData();
            tail = null;
            size--;
            return val;
        }
        Node<E> curr = tail.next; // head
        while (curr.next != tail) {
            curr = curr.next;
        }
        Node<E> prev = curr;
        E val = tail.getData();
        prev.next = tail.next;
        tail = prev;
        size--;
        return val;
    }

    @Override
    public void addFirst(E e) {
        if (tail == null) {
            Node<E> newNode = new Node<>(e, null);
            newNode.next = newNode;
            tail = newNode;
        } else {
            Node<E> newNode = new Node<>(e, tail.next);
            tail.next = newNode;
        }
        size++;
    }

    @Override
    public void addLast(E e) {
        addFirst(e);
        tail = tail.next; // new node becomes tail
    }


    public String toString() {
        if (tail == null) return "[]";
        StringBuilder sb = new StringBuilder("[");
        Node<E> curr = tail.next; // head
        for (int i = 0; i < size; i++) {
            sb.append(curr.getData());
            if (i < size - 1) sb.append(", ");
            curr = curr.next;
        }
        sb.append("]");
        return sb.toString();
    }


    public static void main(String[] args) {
        CircularlyLinkedList<Integer> ll = new CircularlyLinkedList<Integer>();
        for (int i = 10; i < 20; ++i) {
            ll.addLast(i);
        }

        System.out.println(ll);

        ll.removeFirst();
        System.out.println(ll);

        ll.removeLast();
        System.out.println(ll);

        ll.rotate();
        System.out.println(ll);

        ll.removeFirst();
        ll.rotate();
        System.out.println(ll);

        ll.removeLast();
        ll.rotate();
        System.out.println(ll);

        for (Integer e : ll) {
            System.out.println("value: " + e);
        }

    }
}
