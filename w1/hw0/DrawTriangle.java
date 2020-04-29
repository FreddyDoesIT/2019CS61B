package hw0;

public class DrawTriangle {
  private static void drawTriangle (int n) {
    int x = 1;
    while (x <= n) {
      int y = 0;
      while (y < x) {
        System.out.print("*");
        y++;
      }
      System.out.println();
      x++;
    }
  }

  public static void main(String[] args) {
    drawTriangle(10);
  }
}
