import javax.print.DocFlavor;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>{
    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        BST locate = tree;
        if (tree != null) {  // first map
            locate = helper(key, locate);
            if (key.compareTo(locate.key) < 0) {
                locate.left = new BST(key, value, null, null);
                size ++;
            } else if (key.compareTo(locate.key) > 0) {
                locate.right = new BST(key, value, null, null);
                size ++;
            } else {
                locate.value = value;
            }
        }
        if (tree == null) {
            tree = new BST(key, value, null, null);
            size ++;
        }
    }

    private BST helper(K key, BST locate) {
        if (key.compareTo(locate.key) == 0) {
            return locate;
        }
        if (key.compareTo(locate.key) < 0) {
            if (locate.left == null) {
                return locate;
            }
            return helper(key, locate.left);
        }
        if (key.compareTo(locate.key) > 0) {
            if (locate.right == null) {
                return locate;
            }
            return helper(key, locate.right);
        }
        return helper(key, locate);
    }
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (tree != null) {
            BST locate = tree;
            locate = helper(key, locate);
            if (key.compareTo(locate.key) == 0) {
                return locate.value;
            }
        }
        return null;
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (tree != null) {
            BST locate = tree;
            locate = helper(key, locate);
            return key.compareTo(locate.key) == 0;
        }
        return false;
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        tree = null;
        size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private int size = 0;
    private BST tree;

    private class BST {

        K key;
        V value;
        BST left;
        BST right;
        BST(K k, V v, BST l, BST r) {
            key = k;
            value = v;
            left = l;
            right = r;
        }
    }

}