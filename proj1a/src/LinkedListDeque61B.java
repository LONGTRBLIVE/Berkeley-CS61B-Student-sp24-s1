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
        return false;
    }

    @Override
    public int size() {
        return 0;
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
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }

}
