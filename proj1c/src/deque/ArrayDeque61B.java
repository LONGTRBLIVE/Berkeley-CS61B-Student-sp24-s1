package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.Math;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque61B() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    @Override
    public void addFirst(T x) {
        updateSize();
        nextFirst = Math.floorMod(nextFirst, items.length);
        items[nextFirst] = x;
        size ++;
        nextFirst --;
    }

    @Override
    public void addLast(T x) {
        updateSize();
        nextLast = Math.floorMod(nextLast, items.length);
        items[nextLast] = x;
        size ++;
        nextLast ++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        int count = 1;
        while (count != size() + 1) {
            returnList.add(items[Math.floorMod(count + nextFirst, items.length)]);
            count ++;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        nextFirst = Math.floorMod(nextFirst + 1, items.length);
        T first = items[nextFirst];
        items[nextFirst] = null;
        size --;
        updateSize();
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        nextLast = Math.floorMod(nextLast - 1, items.length);
        T last = items[nextLast];
        items[nextLast] = null;
        size --;
        updateSize();
        return last;
    }

    @Override
    public T get(int index) {
        if (index > size() - 1 || index < 0) {
            return null;
        }
        return items[Math.floorMod(nextFirst + index + 1, items.length)];
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    public void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int count = 0;
        while (count != size()) {
            a[count] = get(count);
            count ++;
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size();
    }

    public void updateSize() {
        if (size() == items.length) {
            resize(items.length * 2);
        }
        if (size() < items.length * 0.25 && items.length >= 15) {
            resize(items.length / 2);
        }

    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDeque61BIterator() {
            wizPos = Math.floorMod(nextFirst + 1, items.length);
        }

        public boolean hasNext() {
            return wizPos != Math.floorMod(nextLast, items.length);
        }

        public T next() {
            T returnItem = items[wizPos];
            wizPos = Math.floorMod(wizPos + 1, items.length);
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof ArrayDeque61B<?> other)) {
            return  false;
        }
        if (other.size() != this.size()) {
            return false;
        }
        int index = 0;
        while (index < size) {
            if (!this.get(index).equals(other.get(index))) {
                return false;
            }
            index ++;
        }
        return true;
    }

    @Override
    public String toString() {
        return toList().toString();
    }
}

