package Project0;

public class NBody {

  public static final String BACKGROUND = "Project0/images/starfield.jpg";

  public static void main(String[] args) {
    double T = Double.valueOf(args[0]);
    double dt = Double.valueOf(args[1]);
    String filename = args[2];
    Body[] bodies = readBodies(filename);
    double uniRadius = readRadius(filename);

    // Draw the background
    StdDraw.setScale(-uniRadius, uniRadius);
    StdDraw.clear();
    StdDraw.picture(0, 0, BACKGROUND);

    // Draw planets
    for (Body body: bodies) {
      body.draw();
    }

    // Animation
    StdDraw.enableDoubleBuffering();
    double time = 0;
    int num = bodies.length;
    while (time <= T) {
      double[] xForces = new double[num];
      double[] yForces = new double[num];
      for (int i = 0; i < num; i++) {
        xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
        yForces[i] = bodies[i].calcNetForceExertedByY(bodies);
      }
      for (int i = 0; i < 5; i++) {
        bodies[i].update(dt, xForces[i], yForces[i]);
      }

      // drawing background
      StdDraw.picture(0, 0, BACKGROUND);

      // drawing bodies on the background
      for (Body body: bodies) {
        body.draw();
      }

      StdDraw.show();
      StdDraw.pause(10);

      time += dt;
    }

    /**
     * Printing the universe
     */
    StdOut.printf("%d\n", bodies.length);
    StdOut.printf("%.2e\n", uniRadius);
    for (int i = 0; i < bodies.length; i++) {
      StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
              bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
              bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
    }

  }

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

  /**
   5
   2.50e+11
   1.4960e+11  0.0000e+00  0.0000e+00  2.9800e+04  5.9740e+24    earth.gif
   2.2790e+11  0.0000e+00  0.0000e+00  2.4100e+04  6.4190e+23     mars.gif
   5.7900e+10  0.0000e+00  0.0000e+00  4.7900e+04  3.3020e+23  mercury.gif
   0.0000e+00  0.0000e+00  0.0000e+00  0.0000e+00  1.9890e+30      sun.gif
   1.0820e+11  0.0000e+00  0.0000e+00  3.5000e+04  4.8690e+24    venus.gif
   */

}
