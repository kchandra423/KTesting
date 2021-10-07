package complexTest.kchandra423.shapes;
import processing.core.PApplet;
/**
 * Represents a rectangle composed of 4 lines
 * @author Kumar Chandra
 * @version 10/12/20
 */
public class Rectangle extends Shape {
	// things i Plan to do:
	// add basic shifting methods
	// add a method that checks if you overlap with an object (intersects or inside)
	// things i might do : 
	// rotations
	// scaling
	// if i do end up doing these, i probably wont let the user scale from a given point
	// ill scale from whatever point makes things most convenient (top left and top right)
//	private double x1,x2,x3,x4,y1,y2,y3,y4;
	private Line[] lines;
	private Line top, left, bottom, right;
	//line [] format should be 0-top, 1-left, 2-bottom, 3-right
//	i was debating between having a 4 lines or 4 points,
//	and I'm still not sure what choice is best but im going with this for now
	/**
	 * Creates a new rectangle with with the corner at 0,0, a width of 10, and height of 5
	 */
	public Rectangle() {
		super(0,0);
		double x=0;
		double y=0;
		double width=10;
		double height=5;
		lines=new Line[4];
		top=new Line(x,y,x+width,y);
		left= new Line(x,y,x,y+height);
		bottom= new Line(x,y+height, x+width, y+height);
		right= new Line(x+width, y, x+width, y+height);
		
		lines[0]=top;
		lines[1]=left;
		lines[2]=bottom;
		lines[3]=right;
	}
	/**
	 * Creates a new rectangle
	 * @param x x value of top left corner
	 * @param y y value of top left corner
	 * @param width width (x length)
	 * @param height height (y length)
	 */
	public Rectangle(double x, double y, double width, double height) {
		super(x,y);
		lines=new Line[4];
		top=new Line(x,y,x+width,y);
		left= new Line(x,y,x,y+height);
		bottom= new Line(x,y+height, x+width, y+height);
		right= new Line(x+width, y, x+width, y+height);
		
		lines[0]=top;
		lines[1]=left;
		lines[2]=bottom;
		lines[3]=right;
	}
	/**
	 * draws the rectangle
	 * @param pad the Papplet used
	 * @pre if stroke is set, the lines will be drawn with the specified stroke.
	 */
	public void draw(PApplet pad) {
//		for(int i=0;i<lines.length;i++) {
//			lines[i].setFill(getFill().getRed(), getFill().getGreen(), getFill().getBlue());
//			lines[i].setStroke(getStroke().getRed(), getStroke().getGreen(), getStroke().getBlue());
//			lines[i].draw(pad);
//		}
		pad.pushStyle();
		 super.draw(pad);
		 double tlx=top.getX();
			double tly= top.getY();
			double blx=bottom.getx2();
			double bly=bottom.gety2();
			 double trx=top.getx2();
				double tryButImagineThatsNotAKeyword= top.gety2();
				 double brx=bottom.getX();
					double bry= bottom.getY();
		
			pad.quad((float)tlx, (float)tly,(float)trx,(float)tryButImagineThatsNotAKeyword, (float) blx,(float) bly,(float) brx,(float) bry);
		pad.popStyle();
		
	}
	/**
	 * checks if a point is inside rectangle
	 * @param x x coord of point
	 * @param y y coord of point
	 * @return true if point is inside shape, false otherwise
	 */
	public boolean isPointInside(double x, double y ) {
		boolean answer=false;
		double smallX=Math.min(top.getX(), top.getx2());
		double bigX=Math.max(top.getX(), top.getx2());
		double smallY=Math.min(left.getY(), left.gety2());
		double bigY=Math.max(left.getY(), left.gety2());
		if(smallX<=x&&x<=bigX&&smallY<=y&&y<=bigY) {
			answer=true;
		}
		
		return answer;
	}
	/**
	 * returns the area (length*height)
	 * @return area
	 */
	public double getArea() {
		return Math.abs(top.getLength()*left.getLength());
		
	}
	/**
	 * returns perimeter (2*length + 2*height)
	 * @return perimeter
	 */
	public double getPerimeter() {
		return Math.abs(top.getLength())*2+Math.abs(left.getLength())*2;
	}
	/**
	 * moves the rectangle by x units units to the right (or left if negative)
	 *  and the same for the y direction
	 * @param xAmount x units for translating
	 * @param yAmount y units for translating
	 */
	public void shift(double xAmount, double yAmount) {
		for(int i=0; i<lines.length;i++) {
			lines[i].shift(xAmount, yAmount);
		}
		setX(getX()+xAmount);
		setY(getY()+yAmount);
	}
	/**
	 * checks if any of the edges intersect with the given live
	 * @param other the given line
	 * @return true if the line touches any of edges, false
	 * if inside but not touching an edge or otherwise
	 */
	public boolean intersectsEdge(Line other) {
		boolean answer=false;
		for (int i=0;i<lines.length;i++) {
			try {
				if(lines[i].intersects(other)) {
					answer=true;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return answer;
		
	}
	/**
	 * checks if any of this rectangles edges intersect with any of the other
	 * rectangles edges. If this rectangle returns true, the other rectangle should also return true, 
	 * and vice versa. being inside a rectangle does not count as intersecting.
	 * @param other the given rectangle
	 * @return true if any lines intersect with any of the other rectangles lines
	 * and false otherwise.
	 */
	public boolean intersectsEdge(Rectangle other) {
		boolean answer=false;
		for (int i=0;i<lines.length;i++) {
			for(int j=0;j<other.getLines().length;j++) {
				try {
					if(lines[i].intersects(other.getLines()[j])) {
					answer=true;
					lines[i].intersects(other.getLines()[j]);
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return answer;
		
	}
	/**
	 * gives the top line (may not always look like the "top" line)
	 * @return top line
	 */
	public Line getTop() {
		return top;
	}
	/**
	 * gives the left line (may not always look like the "left" line)
	 * @return left line
	 */
	public Line getLeft() {
		return left;
	}
	/**
	 * gives the bottom line (may not always look like the "bottom" line)
	 * @return bottom line
	 */
	public Line getBottom() {
		return bottom;
	}
	/**
	 * gives the right line (may not always look like the "right" line)
	 * @return right line
	 */
	public Line getRight() {
		return right;
	}
	/**
	 * gives all lines in a array, in the order of (top,left,bottom,right)
	 * @return all lines as an array
	 */
	public Line[] getLines() {
		return lines;
	}
	@Override
	public boolean intersects(Shape other) throws Exception {
		// TODO Auto-generated method stub
		if(other instanceof Line) {
			return intersectsEdge((Line)other);
		}
		else if (other instanceof Circle) {
			return ((Circle)other).intersectsEdge(this);
		}
		else if (other instanceof Rectangle) {
			
			return intersectsEdge((Rectangle)other);
		}
		else {
			throw new Exception ("Tried to check intersection with unknown shape");
		}
	}
	@Override
	public void rotate(double x, double y, double theta) {
		// TODO Auto-generated method stub
		for(int i=0;i<lines.length;i++) {
			lines[i].rotate(x,y,theta);
		}
		double pX=x;
		double midY= y;
		
		
		 double s = Math.sin(theta);
		 double c = Math.cos(theta);
		double x1=getX();
		double y1=getY();

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
	/**
	 * gives the x value of the midpoint of this rectangle
	 * @return xval of midpoint
	 */
	public double getCenterX() {
		return (top.getX()+top.getx2()+bottom.getX()+bottom.getx2())/4;
	}
	/**
	 * gives the y value of the midpoint of this rectangle
	 * @return yval of midpoint
	 */
	public double getCenterY() {
		return (left.getY()+left.gety2()+right.getY()+right.gety2())/4;
	}
	@Override
	public void moveTo(double x, double y) {
		// TODO Auto-generated method stub
		double width=top.getLength();
		double height=left.getLength();
		top=new Line(x,y,x+width,y);
		left= new Line(x,y,x,y+height);
		bottom= new Line(x,y+height, x+width, y+height);
		right= new Line(x+width, y, x+width, y+height);
				lines[0]=top;
		lines[1]=left;
		lines[2]=bottom;
		lines[3]=right;
	}

	@Override
	public Rectangle getBoundingRectangle() {
		// TODO Auto-generated method stub
		return this;
	}
	@Override
	public void reflectOver(double x) {
		for(Line l:lines) {
			l.reflectOver(x);
		}
		
	}

}
