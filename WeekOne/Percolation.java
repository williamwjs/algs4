package WeekOne;

/**
 * Created by JiashuoWang on 6/20/14.
 */
public class Percolation {
    private WeightedQuickUnionUF sites;
    private int[] openSite;
    private int size;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        sites = new WeightedQuickUnionUF(N*N);
        openSite = new int[N*N]; //0-Blocked
        size = N;
    }

    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
        if (i > size || j > size || i < 1 || j < 1) {
            throw new java.lang.IndexOutOfBoundsException("Index Out Of Bounds!");
        }
        int num = (i-1)*size+j-1;
        if (openSite[num] != 0) return;
        if (num >= (size-1)*size) {
            openSite[num] = 2; //2-Percolating
            if ((num-size) >= 0 && openSite[num-size] != 0) {
                sites.union(num, num-size);
                openSite[sites.find(num)] = 2;
            }
            if ((num-1) >= (i-1)*size && openSite[num-1] != 0) {
                sites.union(num, num-1);
                openSite[sites.find(num)] = 2;
            }
            if ((num+1) < i*size && openSite[num+1] != 0) {
                sites.union(num, num+1);
                openSite[sites.find(num)] = 2;
            }
        }
        else {
            openSite[num] = 1; //1-Open
            if ((num-size) >= 0) {
                if (openSite[num - size] == 2
                        || openSite[sites.find(num - size)] == 2) {
                    sites.union(num, num-size);
                    openSite[sites.find(num)] = 2;
                } else if (openSite[num - size] == 1)
                    sites.union(num, num-size);
            }
            if ((num+size) < size*size) {
                if (openSite[num + size] == 2
                        || openSite[sites.find(num + size)] == 2) {
                    sites.union(num, num + size);
                    openSite[sites.find(num)] = 2;
                } else if (openSite[num + size] == 1)
                    sites.union(num, num + size);
            }
            if ((num-1) >= (i-1)*size) {
                if (openSite[num - 1] == 2
                        || openSite[sites.find(num - 1)] == 2) {
                    sites.union(num, num - 1);
                    openSite[sites.find(num)] = 2;
                } else if (openSite[num - 1] == 1)
                    sites.union(num, num - 1);
            }
            if ((num+1) < i*size) {
                if (openSite[num + 1] == 2
                        || openSite[sites.find(num + 1)] == 2) {
                    sites.union(num, num + 1);
                    openSite[sites.find(num)] = 2;
                } else if (openSite[num + 1] == 1)
                    sites.union(num, num + 1);
            }
        }
    }

    // is site (row i, column j) open?
    public boolean isOpen(int i, int j) {
        if (i > size || j > size || i < 1 || j < 1) {
            throw new java.lang.IndexOutOfBoundsException("Index Out Of Bounds!");
        }
        return openSite[(i-1)*size+j-1] != 0;
    }

    // is site (row i, column j) full?
    public boolean isFull(int i, int j) {
        if (i > size || j > size || i < 1 || j < 1) {
            throw new java.lang.IndexOutOfBoundsException("Index Out Of Bounds!");
        }
        for (int m = 0; m < size; m++) {
            if (openSite[m] != 0) {
                if (sites.connected(m, (i-1)*size+j-1)) return true;
            }
        }
        return false;
    }

    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < size; i++) {
            if (openSite[i] == 2 || openSite[sites.find(i)] == 2)
                return true;
        }
        return false;
    }
}
