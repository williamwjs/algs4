package WeekTwo;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by JiashuoWang on 7/22/14.
 */
public class Deque<Item> implements Iterable<Item> {
    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
        Node(Item item, Node<Item> prev, Node<Item> next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }
    private Node<Item> head;
    private Node<Item> tail;
    private int num;
    // construct an empty deque
    public Deque() {
        head = new Node<Item>(null, null, tail);
        tail = new Node<Item>(null, head, null);
        num = 0;
    }
    // is the deque empty?
    public boolean isEmpty() {
        return num == 0;
    }
    // return the number of items on the deque
    public int size() {
        return num;
    }
    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException("Null First Pointer!");
        }
        Node<Item> node = new Node<Item>(item, head, head.next);
        head.next = node;
        node.next.prev = node;
        num++;
    }
    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException("Null Last Pointer!");
        }
        Node<Item> node = new Node<Item>(item, tail.prev, tail);
        node.prev.next = node;
        tail.prev = node;
        num++;
    }
    // delete and return the item at the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("No first to remove!");
        }
        Item item = head.next.item;
        head.next = head.next.next;
        head.next.prev = head;
        num--;
        return item;
    }
    // delete and return the item at the end
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("No last to remove!");
        }
        Item item = tail.prev.item;
        tail.prev = tail.prev.prev;
        tail.prev.next = tail;
        num--;
        return item;
    }
    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            Node<Item> node = head;
            @Override
            public boolean hasNext() {
                return node.next != tail;
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
        //Deque<Integer> dq = new Deque<Integer>();
    }
}
