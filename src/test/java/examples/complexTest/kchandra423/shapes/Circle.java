package examples.complexTest.kchandra423.shapes;

import processing.core.PApplet;

/**
 * Class that represents a circle using processing
 *
 * @author Kumar Chandra
 * @version 10/12/20
 */
public class Circle extends Shape {
    // private double x, y;//x and y of the center
    private final double radius;

    /**
     * Creates a circle at 0,0 with a diameter of 10
     */
    public Circle() {
// 	 x=centerX;
// 	 y=centerY;
        super(0, 0);
        radius = 10;
    }

    /**
     * Creates a circle
     *
     * @param centerX  x coord of center of the circle
     * @param centerY  y coord of center of the circle
     * @param diameter diameter of circle (2*radius)
     */
    public Circle(double centerX, double centerY, double diameter) {
//	 x=centerX;
//	 y=centerY;
        super(centerX, centerY);
        this.radius = diameter;
    }

    public void draw(PApplet pad) {
        pad.pushStyle();
        super.draw(pad);
        pad.ellipse((float) getX(), (float) getY(), (float) radius, (float) radius);
        pad.popStyle();
    }

    /**
     * checks whether or not this circle intersects a rectangle by
     * checking if it intersects any of the rectangle's 4 lines
     *
     * @param other rectangle that you check
     * @return true if intersects edge, false if doesn't or inside
     */
    public boolean intersectsEdge(Rectangle other) {
        boolean answer = false;
        Line[] lines = other.getLines();
        for (int i = 0; i < lines.length; i++) {
            if (intersectsEdge(lines[i])) {
                answer = true;
            }
        }

        return answer;
    }

    /**
     * Checks if this circle intersects a line
     * (As of 10/12 fixed bug where horizantal and parrallel lines would be weird with this method)
     *
     * @param other the line youre checking intersection with
     * @return returns true if the line touches the edge of the circle.
     * returns false otherwise, even if the line is inside the circle
     */
    public boolean intersectsEdge(Line other) {
        //at this point, I yearn for death
        double ax = other.getX();
        double ay = other.getY();
        double bx = other.getx2();
        double by = other.gety2();
        double cx = getX();
        double cy = getY();
        double r = getRadius();
        ax -= cx;
        ay -= cy;
        bx -= cx;
        by -= cy;
        double a = Math.pow((bx - ax), 2) + Math.pow((by - ay), 2);
        double b = 2 * (ax * (bx - ax) + ay * (by - ay));
        double c = Math.pow(ax, 2) + Math.pow(ay, 2) - Math.pow(r, 2);
        double disc = Math.pow(b, 2) - 4 * a * c;
        if (disc <= 0) {
            return false;
        }
        double sqrtdisc = Math.sqrt(disc);
        double t1 = (-b + sqrtdisc) / (2 * a);
        double t2 = (-b - sqrtdisc) / (2 * a);
        return (0 < t1 && t1 < 1) || (0 < t2 && t2 < 1);

    }

    /**
     * says whether or not this circle and another circle intersect
     *
     * @param other the other circle
     * @return true if touchs other circle, or inside the circle, false otherwise
     */
    public boolean intersectsEdge(Circle other) {
        boolean answer;


        answer = !(PApplet.dist((float) getX(), (float) getY(), (float) other.getX(), (float) other.getY()) > other.getRadius() + getRadius());


        return answer;
    }
    // **********THIS METHOD DOESN'T WORK FOR VERTICAL AND HORIZANTAL LINES******************8
    // the calculations become bad because the slope of the line or of the tangent line
    // will be 1/0, and it messes up the calculations

    /**
     * checks if a line is tangent to the circle. has a relatively high margin of error, so as to be useful
     * Does not work for horizontal and vertical lines!
     *
     * @param other line that you check tangency with
     * @return true if tangent to circle. false if not tangent or wouldn't be tangent if line extended forever
     * @pre other line can't be vertical or horizontal
     */
    public boolean isTangent(Line other) {

        boolean answer = false;
        double slopeNorm = -(1 / other.getSlope());
        double interceptNorm = getY() - slopeNorm * getX();

        double xValueOfIntersection = (interceptNorm - other.getYInterecept()) / (other.getSlope() - slopeNorm);
        double yValueOfIntersection = slopeNorm * xValueOfIntersection + interceptNorm;

        if (!other.intersectsEdge(xValueOfIntersection, yValueOfIntersection)) {

            return false;
        }
        double dist = PApplet.dist((float) getX(), (float) getY(), (float) xValueOfIntersection, (float) yValueOfIntersection);
        //really high margin of error on the tangent line, just to make it so its a practical method
        // if it calculated things exactly, you would basically never get the tangent line
        if (getRadius() - 1 <= dist && dist <= getRadius() + 1) {

            answer = true;
        }


        return answer;
    }

    /**
     * checks if points is inside circle
     *
     * @param x x coord of point
     * @param y y coord of point
     * @return true if in or on circle
     */
    public boolean isPointInside(double x, double y) {
        boolean answer = PApplet.dist((float) this.getX(), (float) this.getY(), (float) x, (float) y) <= getRadius();

        return answer;
    }

    /**
     * gives perimeter (2*pi*radius)
     *
     * @return perimeter
     */
    public double getPerimeter() {
        return Math.abs(Math.PI * getDiameter());
    }

    /**
     * S
     * gives area (pi*r^2)
     *
     * @return area
     */
    public double getArea() {
        //pi*r^2
        return Math.PI * Math.pow(getRadius(), 2);
    }

    /**
     * gives diameter of circle
     *
     * @return diameter
     */
    public double getDiameter() {
        return radius;
    }

    /**
     * gives radius (half of diameter)
     *
     * @return radius
     */
    public double getRadius() {
        return radius / 2;
    }

    @Override
    public boolean intersects(Shape other) throws Exception {
        // TODO Auto-generated method stub
        if (other instanceof Circle) {
            return intersectsEdge(((Circle) other));
        } else if (other instanceof Line) {
            return intersectsEdge((Line) other);
        } else if (other instanceof Rectangle) {
            return intersectsEdge((Rectangle) other);
        } else {
            throw new Exception("Tried to check intersection with unknown shape");
        }
    }

    @Override
    public void shift(double x, double y) {
        // TODO Auto-generated method stub
        setX(getX() + x);
        setY(getY() + y);
    }

    @Override
    public void rotate(double x, double y, double theta) {
        // TODO Auto-generated method stub
        double pX = x;
        double midY = y;


        double s = Math.sin(theta);
        double c = Math.cos(theta);
        double x1 = getX();
        double y1 = getY();

        // translate point back to origin:
        x1 -= pX;
        y1 -= midY;

        // rotate point
        double xnew = x1 * c - y1 * s;
        double ynew = x1 * s + y1 * c;

        // translate point back:
        x1 = xnew + pX;
        y1 = ynew + midY;
        setX(x1);
        setY(y1);
    }

    @Override
    public void moveTo(double x, double y) {
        // TODO Auto-generated method stub
        setX(x);
        setY(y);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        // TODO Auto-generated method stub
        return new Rectangle(getX() - getRadius(), getY() - getRadius(), radius, radius);
    }

    @Override
    public void reflectOver(double x) {
        double difx1 = getX() - x;
        setX(x - difx1);
    }

}
