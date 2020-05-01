package Project0;

public class NBody {
  /**
   5
   2.50e+11
   1.4960e+11  0.0000e+00  0.0000e+00  2.9800e+04  5.9740e+24    earth.gif
   2.2790e+11  0.0000e+00  0.0000e+00  2.4100e+04  6.4190e+23     mars.gif
   5.7900e+10  0.0000e+00  0.0000e+00  4.7900e+04  3.3020e+23  mercury.gif
   0.0000e+00  0.0000e+00  0.0000e+00  0.0000e+00  1.9890e+30      sun.gif
   1.0820e+11  0.0000e+00  0.0000e+00  3.5000e+04  4.8690e+24    venus.gif
   */

  /**
   * Given a file name as a String, it should return a double corresponding
   * to the radius of the universe in that file
   * @param path
   * @return
   */
  public static double readRadius(String path) {
    In in = new In(path); //./data/planets.txt
    in.readInt(); // to read the first number but not to use it

    return in.readDouble(); // get the second number: universe radius
  }

  /**
   * Given a file name, it should return an array of Body(s) corresponding to the bodies in the file
   * @param path
   * @return
   */
  public static Body[] readBodies(String path) {
    Body[] bodies = new Body[5];
    In in = new In(path);

    // to read the first two numbers but not to use them
    in.readInt();
    in.readDouble();

    for (int i = 0; i < 5; i++) {
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String img = in.readString();
      bodies[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, img);
    }

    return bodies;
  }

  public static void main(String[] args) {
    if (args.length != 3) {
      System.out.println("You should have 3 arguments: "
              + "[time] [dt] [filename] to proceed.");
    }
    double T = Double.valueOf(args[0]);
    double dt = Double.valueOf(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Body[] bodies = readBodies(filename);


  }

}
