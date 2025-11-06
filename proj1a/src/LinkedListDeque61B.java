import java.util.List;
import java.util.ArrayList;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
            if (p != null) {
                p.next = this;
            }
            if (n != null) {
                n.prev = this;
            }
        }
    }

    private Node sentinel;
    private int size;


    public LinkedListDeque61B() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        Node n = new Node(sentinel, x, sentinel.next);
        sentinel.next = n;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        Node n = new Node(sentinel.prev, x, sentinel);
        sentinel.prev = n;
        size += 1;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        Node t = sentinel.next;
        while(t != sentinel) {
            returnList.add(t.item);
            t = t.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.next == sentinel;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        int i = 0;
        Node n = sentinel.next;
        while (i != index){
            n = n.next;
            i += 1;
        }
        return n.item;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        Node n = sentinel.next;
        int i = 0;
        return helper(n, i, index);
    }
    private T helper(Node n, int i, int index) {
        if (i == index) {
            return n.item;
        }
        return helper(n.next, i + 1, index);
    }

}
