package WeekTwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by JiashuoWang on 7/22/14.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        Node() {
            this.item = null;
            this.next = null;
        }
        Node(Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
        }
    }
    private Node<Item> head;
    private Node<Item> tail;
    private int num;
    // construct an empty randomized queue
    public RandomizedQueue() {
        head = new Node<Item>();
        tail = head;
        num = 0;
    }
    // is the queue empty?
    public boolean isEmpty() {
        return num == 0;
    }
    // return the number of items on the queue
    public int size() {
        return num;
    }
    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("Null Pointer!");
        }
        tail.next = new Node<Item>(item, null);
        tail = tail.next;
        num++;
    }
    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item to remove!");
        }
        int rand = StdRandom.uniform(num) + 1; // 1-num
        Node<Item> node = head;
        for (int i = 0; i < rand-1; i++) { // To the prev of target
            node = node.next;
        }
        Item item = node.next.item;
        node.next = node.next.next;
        num--;
        return item;
    }
    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException("No item to sample!");
        }
        int rand = StdRandom.uniform(num) + 1; // 1-num
        Node<Item> node = head;
        for (int i = 0; i < rand; i++) { // To the target
            node = node.next;
        }
        return node.item;
    }
    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            private Node<Item> node = head;
            @Override
            public boolean hasNext() {
                return node != tail;
            }

            @Override
            public Item next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No next() to next!");
                }
                node = node.next;
                return node.item;
            }
            public void remove() {
                throw new UnsupportedOperationException("remove() is not allowed!");
            }
        };
    }
    // unit testing
    public static void main(String[] args) {
    }
}
