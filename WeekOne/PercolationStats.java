package WeekOne;

/**
 * Created by JiashuoWang on 6/20/14.
 */
public class PercolationStats {
    private int times;
    private double[] results;

    // perform T independent computational experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new java.lang.IllegalArgumentException("Illegal Argument!");
        }
        times = T;
        results = new double[T];
        for (int n = 0; n < T; n++) {
            int num = 0;
            Percolation pc = new Percolation(N);
            while (true) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                if (pc.isOpen(i, j)) continue;
                num++;
                pc.open(i, j);
                if (pc.percolates()) break;
            }
            results[n] = (double) num / N / N;
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        /*double sum = 0;
        for(double r : results) {
            sum += r;
        }
        return sum/times;*/
        return StdStats.mean(results);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        /*double sum = 0;
        for(double r : results) {
            sum += (r - mean()) * (r - mean());
        }
        sum /= (times - 1);
        return Math.sqrt(sum);*/
        return StdStats.stddev(results);
    }

    // returns lower bound of the 95% confidence interval
    public double confidenceLo() {
        return mean() - 1.96 * stddev() / Math.sqrt(times);
    }

    // returns upper bound of the 95% confidence interval
    public double confidenceHi() {
        return mean() + 1.96 * stddev() / Math.sqrt(times);
    }

    // test client, described below
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(
                Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        //PercolationStats ps = new PercolationStats(6, 1);
        System.out.println("mean = " + ps.mean());
        System.out.println("stddev = " + ps.stddev());
        System.out.println("95% confidence interval = "
                + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}
