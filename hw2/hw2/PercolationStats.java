package hw2;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
  // perform T independent experiments on an N-by-N grid
  private List<Double> percolationFraction;
  private Percolation percolation;
  private int size;
  private int totalSites;
  private int experimentTimes;
  public PercolationStats(int N, int T, PercolationFactory pf) {
    if (N <= 0 || T <= 0) {
      throw new IllegalArgumentException("Invalid argument.");
    }

    percolationFraction = new ArrayList<>();
    size = N;
    totalSites = N * N;
    experimentTimes = T;

    for (int i = 0; i < T; i++) {
      percolation = pf.make(N);
      onPercolate();
    }
  }

  private void onPercolate() {
    while(!percolation.percolates()) {
      int row = StdRandom.uniform(size);
      int col = StdRandom.uniform(size);
      percolation.open(row, col);
    }
    percolationFraction.add((double)percolation.numberOfOpenSites() / totalSites);
  }

  // sample mean of percolation threshold
  public double mean() {
    double sum = percolationFraction.stream().reduce(0.0, (a, b) -> a + b);
    return sum / experimentTimes;
  }

  // sample standard deviation of percolation threshold
  public double stddev() {
    double mean = mean();
    double sum = 0.0;
    for (Double fraction: percolationFraction) {
      sum += Math.pow((fraction - mean), 2);
    }

    return sum / (experimentTimes - 1);
  }

  // low endpoint of 95% confidence interval
  public double confidenceLow() {
    return mean() - confidenceCommon();
  }

  private double confidenceCommon() {
    return 1.96 * Math.sqrt(stddev()) / Math.sqrt(experimentTimes);
  }

  // high endpoint of 95% confidence interval
  public double confidenceHigh() {
    return mean() + confidenceCommon();
  }

  public static void main(String[] args) {
    PercolationStats stats = new PercolationStats(20, 50, new PercolationFactory());
    System.out.println(stats.mean());
    System.out.println(stats.stddev());
    System.out.println(stats.confidenceLow());
  }
}
