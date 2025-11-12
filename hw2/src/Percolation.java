import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import net.sf.saxon.value.SequenceExtent;


public class Percolation {
    // TODO: Add any necessary instance variables.
    private WeightedQuickUnionUF percol;
    private WeightedQuickUnionUF isFullcheck;
    private int LENGTH;
    private int[] status;
    private int OFF = -1; //
    private int OPEN = 1;
    private int openSitesNumber;
    private int TOP;
    private int BOTTOM;

    public Percolation(int N) {
        // TODO: Fill in this constructor.
        percol = new WeightedQuickUnionUF(N*N + 2);
        isFullcheck = new WeightedQuickUnionUF(N*N +1);
        status = new int[N * N];
        TOP = N * N;
        BOTTOM = N * N + 1;
        for (int i = 0; i < N * N; i++) {
            status[i] = OFF;
        }
        LENGTH = N;
        openSitesNumber = 0;
    }

    public void open(int row, int col) {
        // TODO: Fill in this method.
        validateRC(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int index = index(row, col);
        status[index] = OPEN;
        openSitesNumber ++;
        connectOpen(row, col);
    }

    public boolean isOpen(int row, int col) {
        // TODO: Fill in this method.
        validateRC(row, col);
        return status[index(row, col)] == OPEN;
    }

    public boolean isFull(int row, int col) {
        // TODO: Fill in this method.
        validateRC(row, col);
        return isFullcheck.connected(TOP, index(row, col));
    }

    public int numberOfOpenSites() {
        // TODO: Fill in this method.
        return openSitesNumber;
    }

    public boolean percolates() {
        // TODO: Fill in this method
        return percol.connected(TOP, BOTTOM);
    }

    // TODO: Add any useful helper methods (we highly recommend this!).
    // TODO: Remove all TODO comments before submitting.
    public int index(int row, int col) { // return 1D index
        return row * LENGTH + col;
    }

    public void connectOpen(int row, int col) {
        if (row - 1 >=0 && isOpen(row - 1, col)){   // up
            percol.union(index(row, col), index(row - 1, col));
            isFullcheck.union(index(row, col), index(row - 1, col));
        }
        if (row + 1 < LENGTH && isOpen(row + 1, col)) { // down
            percol.union(index(row, col), index(row + 1, col));
            isFullcheck.union(index(row, col), index(row + 1, col));
        }
        if (col - 1 >= 0 && isOpen(row, col - 1)) { // left
            percol.union(index(row, col), index(row, col - 1));
            isFullcheck.union(index(row, col), index(row, col - 1));
        }
        if (col + 1 < LENGTH && isOpen(row, col + 1)) {  // right
            percol.union(index(row, col), index(row, col + 1));
            isFullcheck.union(index(row, col), index(row, col + 1));
        }
        if (row == LENGTH - 1) { // close to bottom open link to bottom
            percol.union(BOTTOM, index(row, col));
        }
        if (row == 0) { // first row open link to top
            percol.union(TOP, index(row, col));
            isFullcheck.union(TOP, index(row, col));
        }
    }
    public void validateRC(int row, int col) {
        if (row < 0 || row >= LENGTH || col < 0 || col >= LENGTH) {
            throw new IndexOutOfBoundsException();
        }
    }
}
