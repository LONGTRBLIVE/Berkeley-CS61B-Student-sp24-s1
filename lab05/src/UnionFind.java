import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Set;

import static java.lang.Math.abs;

public class UnionFind {
    // TODO: Instance variables
    private int size;
    private int[] unionArray;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        unionArray = new int[N];
        Arrays.fill(unionArray, -1);
        size = N;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int root = find(v);
        return -unionArray[root];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return unionArray[v];
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int rootV1 = find(v1);
        int rootV2 = find(v2);
        return rootV1 == rootV2;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= size || v < 0) {
            throw new IllegalArgumentException("Outsize!");
        }
        if (unionArray[v] < 0) {
            return v;
        }
        return find(unionArray[v]);
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (connected(v1, v2)) {
            return;
        }
        if (sizeOf(v1) < sizeOf(v2)) {
            int v2Root = find(v2);
            int v1Root = find(v1);
            unionArray[v1Root] = unionArray[v1Root] + unionArray[v2Root];
            unionArray[v2Root] = v1Root;
        } else {
       //  if (sizeOf(v1) < sizeOf(v2)) {
            int v2Root = find(v2);
            int v1Root = find(v1);
            unionArray[v2Root] = unionArray[v1Root] + unionArray[v2Root];
            unionArray[v1Root] = v2Root;
        }
    }

}
