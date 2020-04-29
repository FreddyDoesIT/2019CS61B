package hw0;

import java.util.Arrays;

public class BreakContinue {
  public static void windowPosSum(int[] a, int n) {
    // no mutating passed-in parameter
    int counter = n;
    for (int i = 0; i < a.length; i++) {

      // Do nothing to negative value.
      if (a[i] < 0) {
        counter++;
        continue;
      }

      if (counter <= a.length) {
        for (int j = i + 1; j <= counter; j++) {
          if (j == a.length) {
            break;
          }
          a[i] += a[j];
        }
      } else {
        break;
      }
      counter++;
    }


  }

  public static void main(String[] args) {
    int[] a = {1, 2, -3, 4, 5, 4};
    int n = 3;

//    int[] a = {1, -1, -1, 10, 5, -1};
//    int n = 2;
    windowPosSum(a, n);

    // Should print 4, 8, -3, 13, 9, 4
    System.out.println(Arrays.toString(a));
  }
}
