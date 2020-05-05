package Exercises;

/** A rather contrived exercise to test your understanding of when
 nested classes may be made static. This is NOT an example of good
 class design, even after you fix the bug.

 The challenge with this file is to delete the keyword static the
 minimum number of times so that the code compiles.

 Guess before TRYING to compile, otherwise the compiler will spoil
 the problem.*/
public class Government {
  private int treasury = 5;

  private void spend() {
    treasury -= 1;
  }

  private void tax() {
    treasury += 1;
  }

  public void report() {
    System.out.println(treasury);
  }

  public static Government greaterTreasury(Government a, Government b) {
    if (a.treasury > b.treasury) {
      return a;
    }
    return b;
  }

  /**
   * The class Peasant never use a variable or a method of
   * the Government class, so class Peasant should have static keyword.
   */
  public static class Peasant {
    public void doStuff() {
      System.out.println("hello");
    }
  }

  /**
   * The class King uses the method {@code spend} of
   * the Government class, so class King should not have static keyword.
   */
  public class King {
    public void doStuff() {
      spend();
    }
  }

  /**
   * The class Mayor uses the method {@code tax} of
   * the Government class, so class Mayor should not have static keyword.
   */
  public class Mayor {
    public void doStuff() {
      tax();
    }
  }

  /**
   * The class Accountant uses the method {@code report} of
   * the Government class, so class Accountant should not have static keyword.
   */
  public class Accountant {
    public void doStuff() {
      report();
    }
  }

  /**
   * The class Thief uses the variable {@code treasury} of
   * the Government class, so class Thief should not have static keyword.
   */
  public class Thief {
    public void doStuff() {
      treasury = 0;
    }
  }

  /**
   * The class Explorer never uses a variable or a method of
   * the Government class DIRECTLY, so class Explorer can have the static keyword.
   */
  public static class Explorer {
    public void doStuff(Government a, Government b) {
      Government favorite = Government.greaterTreasury(a, b);
      System.out.println("The best government has treasury " + favorite.treasury);
    }
  }
}
