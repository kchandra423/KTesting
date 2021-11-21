package examples.complexTest.kchandra423.shapes;

import processing.core.PApplet;

import java.awt.*;
// More functionality will definitely be added later but for now im just using doing what my group decided on
//(not to mention because im lazy)
/**
 * Represents a shape using processing
 * @author Kumar Chandra
 * @version 10/12/20
 */
public abstract class Shape {
private double x,y;
private Color fill;
private Color stroke;
private boolean filled;
private int strokeWeight;

/**
 * creates a new Shape with the specified x and y values. The default fill and stroke are white and black respectively
 * with fill being false and strokeWeight being 1
 * @param x 'x' value of the shape
 * @param y 'y' value of the shape
 */
public Shape(double x, double y) {
	this.x=x;
	this.y=y;
	filled=false;
	strokeWeight=1;
	fill=Color.white;
	stroke=Color.black;

	
}
	
/**
 * draws the shape with the specified fill and stroke
 * 	@pre any of the parameters about the papplet thats passed in will still
 *  be valid (alpha, etc) except for fill and stroke
 *  @post the given PApplet will now have the specified fill, strokeweight, and stroke of this shape
 * @param p the specified Papplet
 */
public void draw(PApplet p) {
	p.fill(fill.getRGB());
	p.stroke(stroke.getRGB());
	if(!filled) {
		p.noFill();
	}
	p.strokeWeight(strokeWeight);

}
/**
 * checks if this shape intersects with the other shape, but returns false for shapes overlapping
 * Shape must be a rectangle circle or line
 * @pre shapes must be a rectangle, circle, or line
 * @param other the specified shape
 * @return true if edges intersect, false otherwise
 * @throws Exception throws an exception if you try to check if a 'custom' (non rectangle, line or circle) shape 
 * intersects with another shape
 */
public abstract boolean intersects(Shape other) throws Exception;
	/**
	 * shifts the shape by x and y units
	 * @param x x units right
	 * @param y  y units down
	 */
	public abstract void shift(double x, double y);
	/**
	 * rotates the shape around a specified point by the specified number of radians
	 * @param x xpoint of rotation
	 * @param y ypoint of rotation
	 * @param theta magnitude of rotation in radians
	 */
	public abstract void rotate(double x, double y, double theta);
	public abstract void reflectOver(double x);
	/**
	 * gives perimeter of shape (for lines just gives the length)
	 * @return perimeter in pixels
	 */
	public abstract double getPerimeter() ;
	/**
	 * gives area of shape(for lines just gives the length)
	 * @return area in pixels squared
	 */
	public abstract double getArea() ;
	/**
	 * moves the shape to a certain location
	 * @param x xcoord of location
	 * @param y ycoord of location
	 */
	public abstract void moveTo(double x, double y) ;
	/**
	 * sets the fill of the shape (inside color)
	 *  important to note that lines dont have fill, only stroke
	 *  @post sets filled to true
	 * @param c Color of the shape
	 */
	public void setFill(Color c) {
		fill=c;
		filled=true;
	}
/**
 * sets the fill of the shape (inside color) 
 * important to note that lines dont have fill, only stroke
 * @post sets filled to true
 * @param r red value
 * @param g green value
 * @param b blue value
 */
	public void setFill(int r, int g, int b) {
		Color c= new Color(r,g,b);
		filled=true;
		fill=c;
	}
	/**
	 * sets the stroke of the shape (line color) 
	 * important to note that lines dont have fill, only stroke
	 * @param stroke color of the stroke
	 */
	public  void setStroke(Color stroke) {
	this.stroke=stroke;
	}
	/**
	 * sets the stroke color of the shape (line color)
	 *  important to note that lines dont have fill, only stroke
	 * @param r red value
	 * @param g green value
	 * @param b blue value
	 */
	public void setStroke(int r, int g, int b) {
		Color c= new Color(r,g,b);
		stroke=c;
	}
	/**
	 * says whether or not a point is inside the borders of a shape
	 * @param x xcoord of point
	 * @param y ycoord of point
	 * @return true if inside, false otherwise
	 */
	public abstract boolean isPointInside(double x, double y) ;
	/**
	 * Gives a rectangle that this shape will best fit into 
	 * @return rectangle that would cover shape best
	 */
	public abstract Rectangle getBoundingRectangle();
	 // this varies from shape to shape and i might just get rid of this idea, since it doesn't really make sense
	 // I would rather just have shape be an interface, or at least just not have an 'x' and a 'y'
	/**
	 * gives the 'x' value of the shape
	 * @return 'x' coord of the shape
	 */
	public double getX() {
		return x;
	}
	//	 i already explained why i might get rid of this method in the getX() method
	//   so look at that if youre interested
	/**
	 * gives the 'y' value of the shape
	 * @return 'y' coord of the shape
	 */
	public double getY() {
		return y;
	}
	/**
	 * sets the 'x' value of the shape. similar to the move to method.
	 * @param x 'x' value you want to move the shape to
	 */
	public void setX(double x) {
		this.x=x;
	}
	/**
	 * sets the 'y' value of the shape. similar to the move to method
	 * @param y 'y' value of place you want to move the shape to
	 */
	public void setY(double y) {
		this.y=y;
	}
	/**
	 * gives the fill of the shape as a color
	 * @return color of the fill of the shape
	 */
	public Color getFill() {
		return fill;
	}
	/**
	 * gives the stroke of the shape as a color
	 * @return color of the stroke of the shape
	 */
	public  Color getStroke() {
		return stroke;
	}
	/**
	 * says whether or not this shape has fill
	 * @return true if filled, false if not filld
	 */
	public  boolean isFilled() {
		return filled;
	}
	/**
	 * sets whether or not this shape has fill
	 * @param isFilled whether or not the shape has fill
	 */
	public  void setFilled(boolean isFilled) {
		filled=isFilled;
	}
	/**
	 * gives the current stroke weight in pixels
	 * @return stroke weight of this shape
	 */
	public int getStrokeWeight() {
		return strokeWeight;
		
	}
	/**
	 * sets the current stroke weight
	 * @pre if the specified weight is less than or equal to 0, the weight will be set to 1
	 * @param weight the specifed stroke weight
	 */
	public void setStrokeWeight(int weight) {
		strokeWeight=weight;
		if(weight<=0) {
			strokeWeight=1;
		}
	}

}
