package project20280.stacksqueues;

import project20280.interfaces.Queue;


/**
 * Realization of a circular FIFO queue as an adaptation of a
 * CircularlyLinkedList. This provides one additional method not part of the
 * general Queue interface. A call to rotate() is a more efficient simulation of
 * the combination enqueue(dequeue()). All operations are performed in constant
 * time.
 */

public class LinkedCircularQueue<E> implements Queue<E> {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E e, Node<E> n) {
            element = e;
            next = n;
        }
    }

    private Node<E> tail = null;
    private int size = 0;

    public LinkedCircularQueue() {
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public void enqueue(E e) {
        // TODO Auto-generated method stub
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) {
            newest.next = newest; // link to itself circularly
            tail = newest;
        } else {
            newest.next = tail.next; // new node points to head
            tail.next = newest; // old tail points to new node
            tail = newest; // new node becomes the tail
        }
        size++;
    }

    @Override
    public E first() {
        if (isEmpty()) return null;
        return tail.next.element;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) return null;
        Node<E> head = tail.next;
        if (head == tail) { // only one element
            tail = null;
        } else {
            tail.next = head.next; // bypass the old head
        }
        size--;
        return head.element;
    }

    public void rotate() {
        if (tail != null) {
            tail = tail.next; // old head becomes new tail
        }
    }

}
