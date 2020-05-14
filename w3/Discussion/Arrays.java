package Discussion;

public class Arrays {

  private int[] array;
  private static int DEFAULT_LEN = 100;
  private int size;
  public Arrays() {
    array = new int[DEFAULT_LEN];
    size = 0;
  }

  /*
  Arrays have a fixed size, so to add an element,
  you need to create a new array.
   */
  public static int[] insert (int[] array, int item, int position) {
    int[] res = new int[array.length + 1];
    position = Math.min(array.length, position);
    for (int i = 0; i < position; i++) {
      res[i] = array[i];
    }
    res[position] = item;
    for (int i = position; i < array.length; i++) {
      res[i + 1] = array[i];
    }

    return res;
  }

  // a destructive way: to mutate the original object
  public static void reverse(int[] arr) {
    for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
      int temp = arr[i];
      arr[i] = arr[j];
      arr[j] = temp;
    }
  }

  // a non-destructive way
  public static int[] replicate(int[] arr) {
    int count = 0;
    for (int i = 0; i < arr.length; i++) {
      count += arr[i];
    }
    int[] res = new int[count];
    int index = 0;
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i]; j++) {
        res[index++] = arr[i];
      }
    }

    return res;
  }

  public static void print(int[] array) {
    for (int i = 0; i < array.length; i++) {
      System.out.print(array[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
//    int[] a = new int[20];
//    for (int i = 0; i < 10; i++) {
//      a[i] = i;
//    }
//    print(a);
//    reverse(a);
//    print(a);
//    a = insert(a,10, 1);
//    print(a);

    int[] a = new int[]{3,2,1,5};
    int[] res = replicate(a);
    print(res);
  }
}
