package Project0;

public class Body {
  public double xxPos;
  public double yyPos;
  public double xxVel;
  public double yyVel;
  public double mass;
  public String imgFileName;
  // Java supports scientific notation
  // G = 6.67⋅10^-11
  public static final double G = 6.67e-11;

  /**
   * The first constructor takes several parameters to build a body object.
   * @param xP x position
   * @param yP y position
   * @param xV x velocity
   * @param yV y velocity
   * @param m mass
   * @param img image
   */
  public Body(double xP, double yP, double xV,
              double yV, double m, String img) {
    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  /**
   * The second constructor takes in a Body object
   * and initialize an identical Body object.
   * @param b a body object
   */
  public Body(Body b) {
    xxPos = b.xxPos;
    yyPos = b.yyPos;
    xxVel = b.xxVel;
    yyVel = b.yyVel;
    mass = b.mass;
    imgFileName = b.imgFileName;
  }

  /**
   * This method calculates the distance between two {@code Body}s.
   * @param b a passed-in object Body
   * @return the distance between two {@code Body}s.
   */
  public double calcDistance(Body b) {
    double dx = Math.abs(this.xxPos - b.xxPos);
    double dy = Math.abs(this.yyPos - b.yyPos);
//    return Math.sqrt(dx * dx + dy * dy);
    return Math.hypot(dx, dy);
  }

  /**
   * This method calculates the force exerted on a body by the given body.
   * @param b a passed-in object Body
   * @return the force exerted by the given body
   */
  public double calcForceExertedBy(Body b) {
    double distance = calcDistance(b);
    double squaredDistance = distance * distance;
    return G * this.mass * b.mass / squaredDistance;
  }

  public double calcForceExertedByX(Body b) {
    double force = calcForceExertedBy(b);
    double distance = calcDistance(b);
    double dx = this.xxPos - b.xxPos;
    double fx = force * Math.abs(dx) / distance;

    // if dx > 0, it means this Body is at the right side of the given body
    // so this body will be pulled left and the force should be negative
    return dx > 0 ? fx * -1 : fx;
  }

  public double calcForceExertedByY(Body b) {
    double force = calcForceExertedBy(b);
    double distance = calcDistance(b);
    double dy = this.yyPos - b.yyPos;
    double fy = force * Math.abs(dy) / distance;

    // if dy > 0, it means this Body is at the top of the given body
    // so this body will be pulled down and the force should be negative
    return dy > 0 ? fy * -1 : fy;
  }

  public double calcNetForceExertedByX(Body[] bodies) {
    double fNetX = 0.0;
    for (Body body: bodies) {
      if (body.equals(this)) {
        continue;
      }
      fNetX += this.calcForceExertedByX(body);
    }
    return fNetX;
  }

  public double calcNetForceExertedByY(Body[] bodies) {
    double fNetY = 0.0;
    for (Body body: bodies) {
      if (body.equals(this)) {
        continue;
      }
      fNetY += this.calcForceExertedByY(body);
    }
    return fNetY;
  }

  public void update (double dt, double fNetX, double fNetY) {
    double aX = fNetX / this.mass;
    double aY = fNetY / this.mass;
    this.xxVel += aX * dt;
    this.yyVel += aY * dt;
    this.xxPos += this.xxVel * dt;
    this.yyPos += this.yyVel * dt;
  }

  public void draw() {
    StdDraw.picture(
            this.xxPos,
            this.yyPos,
            "Project0/images/" + this.imgFileName);
  }
}
