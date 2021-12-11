package examples.complexTest.kchandra423.shapes;

import processing.core.PApplet;

/**
 * Represents a line segment using processing
 *
 * @author Kumar Chandra
 * @version 10/12/20
 */
public class Line extends Shape {
    //your line class should be identical so I don't feel the need to comment this. I didn't add anything special here,
    //except for a few self explanatory method method
    private double x2, y2;

    /**
     * creates a new line with points (0,0) and (1,1)
     */
    public Line() {
        super(0, 0);

        x2 = 1;

        y2 = 1;
    }

    /**
     * creates a new line
     *
     * @param x1 x coord of first point
     * @param y1 y coord of first point
     * @param x2 x coord of second point
     * @param y2 y coord of second point
     */
    public Line(double x1, double y1, double x2, double y2) {
        super(x1, y1);

        this.x2 = x2;

        this.y2 = y2;
    }

    /**
     * draws the line
     *
     * @param pad PApplet used to draw stuff
     * @pre Line will be drawn with the current stroke of the papplet if it is set
     */
    public void draw(PApplet pad) {
        pad.pushStyle();
        super.draw(pad);
        pad.beginShape();
        pad.vertex((float) getX(), (float) getY());
        pad.vertex((float) getx2(), (float) gety2());
        pad.endShape();
        pad.popStyle();
    }

    /**
     * returns the intersection point between this line and a given line
     *
     * @param other the given line
     * @return xcoord of intersection point
     * @pre parrallel and overlapping lines return values that dont make sense
     */
    public double getIntersectionX(Line other) {
        double answer = 0;
        double x3 = other.getX(), x4 = other.getx2(), y3 = other.getY(), y4 = other.gety2();
        answer = ((getX() * y2 - getY() * x2) * (x3 - x4) - (getX() - x2) * (x3 * y4 - y3 * x4)) / ((getX() - x2) * (y3 - y4) - (getY() - y2) * (x3 - x4));
        return answer;
    }

    /**
     * returns the intersection point between this line and a given line
     *
     * @param other the given line
     * @return ycoord of intersection point
     * @pre parrallel and overlapping lines return values that dont make sense
     */
    public double getIntersectionY(Line other) {
        double answer = 0;
        double x3 = other.getX(), x4 = other.getx2(), y3 = other.getY(), y4 = other.gety2();
        answer = ((getX() * y2 - getY() * x2) * (y3 - y4) - (getY() - y2) * (x3 * y4 - y3 * x4)) / ((getX() - x2) * (y3 - y4) - (getY() - y2) * (x3 - x4));
        return answer;
    }

    /**
     * tells whether or not this line and a given line intersect along its domain
     *
     * @param other the given line
     * @return true if they intersect along the domain, false if they dont
     */
    public boolean intersectsEdge(Line other) {
        boolean answer = false;
        double x = getIntersectionX(other);
        double y = getIntersectionY(other);
        double otherlesserX, othergreaterX, otherlesserY, othergreaterY, greaterX, lesserX, lesserY, greaterY;

        if (getX() > x2) {
            greaterX = getX();
            lesserX = x2;
        } else {
            greaterX = x2;
            lesserX = getX();
        }

        if (getY() > y2) {
            greaterY = getY();
            lesserY = y2;
        } else {
            greaterY = y2;
            lesserY = getY();
        }

        lesserX = Math.min(getX(), getx2());
        greaterX = Math.max(getX(), getx2());
        lesserY = Math.min(getY(), gety2());
        greaterY = Math.max(getY(), gety2());
        double x3 = other.getX(), x4 = other.getx2(), y3 = other.getY(), y4 = other.gety2();

        otherlesserX = Math.min(x3, x4);
        othergreaterX = Math.max(x3, x4);
        otherlesserY = Math.min(y3, y4);
        othergreaterY = Math.max(y3, y4);
//			if(other.getX()>other.getx2()) {
//				othergreaterX=other.getX();
//				otherlesserX=other.getx2();
//			}
//			else {
//				othergreaterX=other.getx2();
//				otherlesserX=other.getX();
//			}
//			if(other.getY()>other.gety2()) {
//				othergreaterY=other.getY();
//				otherlesserY=other.gety2();
//			}
//			else {
//				othergreaterY=other.gety2();
//				otherlesserY=other.getY();
//			}
        if (
                (
                        (x + 0.0001 >= lesserX && x - 0.0001 <= greaterX)
                                &&
                                (x + 0.0001 >= otherlesserX && x - 0.0001 <= othergreaterX)
                )
                        && (
                        (y + 0.0001 >= lesserY && y - 0.0001 <= greaterY)
                                &&
                                (y + 0.0001 >= otherlesserY && y - 0.0001 <= othergreaterY)
                )

        ) {

            answer = true;
        }
//			if(((getX()==other.getX()||(x2==other.getX()||x2==other.getx2()))
//					&&
//					((getY()==other.getY()||getY()==other.gety2())||(y2==other.getY()||y2==other.gety2()))
//					) {
//				answer=true;
//			}
//			if(getX()==other.getx2())) {
//				
//			}

        if ((getX() == x2) && (getY() == y2)) {
            if ((getX() == other.getX() || x == other.getx2()) || (getY() == other.getY() || y == other.gety2())) {
                answer = true;
            }
        }
        if ((other.getx2() == other.getX()) && (other.gety2() == other.getY())) {
            if ((getX() == other.getX() || x == other.getx2()) || (getY() == other.getY() || y == other.gety2())) {
                answer = true;
            }
        }
        return answer;

    }

    /**
     * checks if a point intersects this line along the lines domain
     *
     * @param x xcoord of point
     * @param y ycoord of point
     * @return true if they intersect along the lines domain, false otherwise
     */
    public boolean intersectsEdge(double x, double y) {
        boolean lineIntersects = false;
        boolean inXRange = false;
        boolean inYRange = false;
        boolean answer = false;
        if (getSlope() * x + getYInterecept() - 0.0001 <= y && y <= getSlope() * x + getYInterecept() + 0.0001) {
            lineIntersects = true;
        }
        if (getMinX() - 0.00001 <= x && x <= getMaxX() + 0.00001) {
            inXRange = true;
        }
        if (getMinY() - 0.00001 <= y && y <= getMaxY() + 0.00001) {
            inYRange = true;
        }
        if (lineIntersects && inXRange && inYRange) {
            answer = true;
        }
        return answer;
    }

    /**
     * gives you the 'a'if you wrote this line in general form
     * keep in mind that this is the a val of the line, not the line segment
     * also keep in mind that general form is ax+by+c=0
     *
     * @return the 'a'
     */
    public double getAInGeneralForm() {
        return getY() - y2;
    }

    /**
     * gives you the 'b'if you wrote this line in general form
     * keep in mind that this is the a val of the line, not the line segment
     * also keep in mind that general form is ax+by+c=0
     *
     * @return the 'b'
     */
    public double getBInGeneralForm() {
        return x2 - getX();
    }

    /**
     * gives you the 'c'if you wrote this line in general form
     * keep in mind that this is the a val of the line, not the line segment
     * also keep in mind that general form is ax+by+c=0
     *
     * @return the 'c'
     */
    public double getCInGeneralForm() {
        return (getX() - x2) * getY() + getX() * (y2 - getY());
    }

    /**
     * returns maximum x coord (x coord of right most point)
     *
     * @return xcoord of right most point
     */
    public double getMaxX() {
        return Math.max(getX(), x2);
    }

    /**
     * returns min x coord (x coord of left most point)
     *
     * @return x coord of left most point
     */
    public double getMinX() {
        return Math.min(getX(), x2);
    }

    /**
     * returns maximum ycoord (y coord of bottom most point)
     *
     * @return ycoord of bottom most point
     */
    public double getMaxY() {
        return Math.max(getY(), y2);
    }

    /**
     * returns  minimum y coord (y coord of top most point)
     *
     * @return ycoord of top most point
     */
    public double getMinY() {
        return Math.min(getY(), y2);
    }

    /**
     * returns slope of the line (the "m" y=mx+b)
     *
     * @return slope
     */
    public double getSlope() {
        double deltaY = getY() - y2;
        double deltaX = getX() - x2;
        return deltaY / deltaX;
    }

    /**
     * returns y intercept of the line (the b in y= mx+b)
     *
     * @return y intercept
     */
    public double getYInterecept() {
        return getY() - getSlope() * getX();
    }

    /**
     * set the coordinate of the second point
     *
     * @param x2 new xcoord of second point
     * @param y2 new ycoord of second point
     */
    public void setPoint2(double x2, double y2) {
        this.x2 = x2;
        this.y2 = y2;

    }
    //this is the method that I added cuz I didn't wanna make a new lane every single time

    /**
     * sets both coordinates to specified values
     *
     * @param x1 new xcoord of first point
     * @param y1 new ycoord of first point
     * @param x2 new xcoord of second point
     * @param y2 new ycoord of second point
     */
    public void setPoints(double x1, double y1, double x2, double y2) {
        setX(x1);
        this.x2 = x2;
        setY(y1);
        this.y2 = y2;
    }

    /**
     * moves both points by specified values
     *
     * @param xamount x translation
     * @param yamount y translation
     */
    public void shift(double xamount, double yamount) {
        setX(getX() + xamount);
//			x1+=xamount;
        x2 += xamount;
        setY(getY() + yamount);
//			y1+=yamount;
        y2 += yamount;
    }
//		/**
//		 * returns xcoord of first point
//		 * @return first xcoord
//		 */
//		public double getx1() {
//			//why did i put this.
//			return this.x1;
//		}

    /**
     * returns xcoord of second point
     *
     * @return second xcoord
     */
    public double getx2() {
        return this.x2;
    }
//		/**
//		 * returns y coord of first point
//		 * @return ycoord of first point
//		 */
//		public double gety1() {
//			return this.y1;
//		}

    /**
     * returns y coord of second point
     *
     * @return y coord of second point
     */
    public double gety2() {
        return this.y2;
    }

    /**
     * returns the length of the line
     *
     * @return length
     */
    public double getLength() {
        return PApplet.dist((float) getX(), (float) getY(), (float) x2, (float) y2);
    }

    //		@Override
//		public double getX() {
//			return x1;
//			
//		}
//		@Override
//		public double getY() {
//			return y1;
//			
//		}
//		@Override
//		public void setX(double x) {
//			this.x1=x;
//		}
//		@Override
//		public void setY(double y) {
//			this.y1=y;
//		}
    @Override
    public boolean intersects(Shape other) throws Exception {
        // TODO Auto-generated method stub
        boolean answer = false;
        if (other instanceof Line) {
            answer = intersectsEdge(((Line) other));
        } else if (other instanceof Circle) {
            answer = ((Circle) other).intersectsEdge(this);
        } else if (other instanceof Rectangle) {
            answer = ((Rectangle) other).intersectsEdge(this);
        } else {
            throw new Exception("Tried to check intersection with unknown shape");
        }
        return answer;

    }

    @Override
    public void rotate(double x, double y, double theta) {
        // TODO Auto-generated method stub
        double pX = x;
        double midY = y;

        double s = Math.sin(theta);
        double c = Math.cos(theta);

        // translate point back to origin:
        setX(getX() - pX);
//			 x1 -= pX;
        setY(getY() - midY);
//			  y1 -= midY;

        // rotate point
        double xnew = getX() * c - getY() * s;
        double ynew = getX() * s + getY() * c;

        // translate point back:
        setX(xnew + pX);
        setY(ynew + midY);

        x2 -= pX;
        y2 -= midY;

        // rotate point
        xnew = x2 * c - y2 * s;
        ynew = x2 * s + y2 * c;

        // translate point back:
        x2 = xnew + pX;
        y2 = ynew + midY;
    }

    @Override
    public double getPerimeter() {
        // TODO Auto-generated method stub
        return getLength();
    }

    @Override
    public double getArea() {
        // TODO Auto-generated method stub
        return getLength();
    }

    @Override
    public void moveTo(double x, double y) {
        // TODO Auto-generated method stub
        double xdist = getX() - x2;
        double ydist = getY() - y2;
        setX(x);
        setY(y);
        y2 = getY() + ydist;
        x2 = getX() + xdist;

    }

    @Override
    public boolean isPointInside(double x, double y) {
        // TODO Auto-generated method stub
        return intersectsEdge(x, y);
    }

    @Override
    public Rectangle getBoundingRectangle() {
        // TODO Auto-generated method stub
        return new Rectangle(getX(), getY(), x2 - getX(), y2 - getY());
    }

    @Override
    public void reflectOver(double x) {
        double difx1 = getX() - x, difx2 = x2 - x;
        setX(x - difx1);
        x2 = x - difx2;

    }


}
