package deque;

import java.util.Comparator;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private Comparator<T> comparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            if (comparator.compare(maxItem, get(i)) < 0) {
                maxItem = get(i);
            }
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = get(0);
        for (int i = 1; i < size(); i++) {
            if(c.compare(maxItem, get(i)) < 0) {
                maxItem = get(i);
            }
        }
        return maxItem;
    }
}
