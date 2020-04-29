package hw0;

public class MaxFinder {
  /** Returns the maximum value from m. */
  public static int max(int[] m) {
    int max = 0;
    for (int element: m) {
      if (element > max) {
        max = element;
      }
    }
    return max;
  }
  public static void main(String[] args) {
    int[] numbers = new int[]{9, 2, 15, 2, 22, 10, 6};
    System.out.println(max(numbers));
  }
}
