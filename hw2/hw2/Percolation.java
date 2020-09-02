package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
  private boolean[][] grid;
  private int rowNum;
  private int colNum;
  private int openedSites;
  private static int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
  private WeightedQuickUnionUF uf;
  // create N-by-N grid, with all sites initially blocked
  public Percolation(int N) {
    if (N <= 0) {
      throw new IllegalArgumentException("N should be larger than 0.");
    }
    grid = new boolean[N][N];
    rowNum = colNum = N - 1;
    uf = new WeightedQuickUnionUF(N * N + 1);
  }

  // open the site (row, col) if it is not open already
  public void open(int row, int col) {
    validate(row, col);
    grid[row][col] = true;
    openedSites++;
    onPercolate(row, col);
  }

  private void onPercolate(int row, int col) {
    if (row == 0) {
      uf.union(convertTo1DIndex(row, col), 0);
    }

    for (int[] d: dirs) {
      int adjRow = row + d[0];
      int adjCol = col + d[1];
      if (!isValidCoordinate(adjRow, adjCol)) {
        continue;
      }
      if (isFull(adjRow, adjCol)) {
        uf.union(convertTo1DIndex(row, col), 0);
      }
    }
    if (isFull(row, col)) {
      for(int[] dir: dirs) {
        int newRow = row + dir[0];
        int newCol = col + dir[1];
        if (!isValidCoordinate(newRow, newCol)) {
          continue;
        }
        if (!isFull(newRow, newCol)) {
          uf.union(convertTo1DIndex(newRow, newCol), 0);
        }
      }
    }
  }

  private boolean isValidCoordinate(int row, int col) {
    if (row < 0 || row > rowNum
            || col < 0 || col > colNum
            || !grid[row][col]) {
      return false;
    }
    return true;
  }

  private int convertTo1DIndex(int row, int col) {
    return row * col + col + 1;
  }

  private void validate (int row, int col) {
    if (row < 0 || row > rowNum || col < 0 || col > colNum) {
      throw new IndexOutOfBoundsException("Index should reside within the grid.");
    }
  }

  // is the site (row, col) open?
  public boolean isOpen(int row, int col) {
    validate(row, col);
    return grid[row][col];
  }

  // is the site (row, col) full?
  public boolean isFull(int row, int col) {
    validate(row, col);
    return isOpen(row, col) && uf.connected(convertTo1DIndex(row, col), 0);
  }

  // number of open sites
  public int numberOfOpenSites() {
    return openedSites;
  }

  // does the system percolate?
  public boolean percolates() {
    for (int i = 0; i <= colNum; i++) {
      if (grid[rowNum][i]) {
        return uf.connected(convertTo1DIndex(rowNum, i), 0);
      }
    }

    return false;
  }

  // use for unit testing (not required, but keep this here for the autograder)
  public static void main(String[] args) {
    try {
      Percolation test = new Percolation(-5);
    } catch (IllegalArgumentException e) {
      System.out.println("Illegal argument -5 caught.");
    }
    try {
      Percolation test = new Percolation(0);
    } catch (IllegalArgumentException e) {
      System.out.println("Illegal argument 0 caught.");
    }

    Percolation p = new Percolation(5);
    try {
      p.validate(-1, 3);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Validate row number works.");
    }
    try {
      p.validate(5, 3);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Validate row number works.");
    }

    try {
      p.validate(3, -1);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Validate col number works.");
    }
    try {
      p.validate(3, 5);
    } catch (IndexOutOfBoundsException e) {
      System.out.println("Validate col number works.");
    }

    p.open(2,2);
    // expected false
    System.out.println(p.isFull(2,2));
    p.open(0, 1);
    //expected true
    System.out.println(p.isFull(0, 1));
    p.open(1,1);
    //expected true
    System.out.println(p.isFull(1,1));

    p.open(2,1);
    System.out.println(p.isFull(2,1));
    System.out.println(p.isFull(2,2));

    p.open(3,2);
    p.open(4,2);
    System.out.println("Does the system percolate: " + p.percolates());
  }
}
