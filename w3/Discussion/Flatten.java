package Discussion;

public class Flatten {
  /**
   * Write a method flatten that takes in a 2-D array x and returns a 1-D array that
   * contains all of the arrays in x concatenated together.
   * For example, flatten({{1, 2, 3}, {}, {7, 8}}) should return {1, 2, 3, 7, 8}.
   * @param x a 2D array
   * @return a 1D array
   */
  public static int[] flatten(int[][] x) {
    int totalLength = 0;
    for (int i = 0; i < x.length; i++) {
      totalLength += x[i].length;
    }

    int[] a = new int[totalLength];
    int aIndex = 0;
    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < x[i].length; j++) {
        a[aIndex++] = x[i][j];
      }
    }

    return a;
  }

  public static void print(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    int[][] x = {{1, 2, 3}, {}, {7, 8}};
    int[] a = flatten(x);

    print(a);
  }
}
