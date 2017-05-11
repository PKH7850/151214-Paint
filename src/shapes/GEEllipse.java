package shapes;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

public class GEEllipse extends GEShape {

	private static final long serialVersionUID = 1L;
	
	private Ellipse2D ellipse;
	private double sizeX, sizeY;
	
	public GEEllipse(){
		super(new Ellipse2D.Double());
		this.ellipse = (Ellipse2D) this.getShape();
	}
	
	
	@Override
	public void setPoint(int x, int y) {
		this.ellipse.setFrame(x, y, 0, 0);
	}


	@Override
	public void addPoint(int x, int y) {
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x-this.ellipse.getX(), y-this.ellipse.getY());
	}


	@Override
	public void movePoint(int x, int y) {
		this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), x-this.ellipse.getX(), y-this.ellipse.getY());
		sizeX = x-ellipse.getX();
		sizeY = y-ellipse.getY();
	}


	@Override
	public void moveShape(int x, int y) {
		this.ellipse.setFrame(this.ellipse.getX()+x, this.ellipse.getY()+y, sizeX, sizeY);
	}


	@Override
	public void resizeShape(int dw, int dh) {
		switch(eSelectedAnchor) {
		case EE : 
			sizeX = sizeX+dw;
			this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), sizeX, sizeY); break;
		case SS : 
			sizeY = sizeY+dh;
			this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), sizeX, sizeY); break;
		case SE : 
			sizeX = sizeX+dw; sizeY = sizeY+dh;
			this.ellipse.setFrame(this.ellipse.getX(), this.ellipse.getY(), sizeX, sizeY); break;
		default:
			break;
		}
	}


	@Override
	public void rotateShape(int dw, int dh) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public GEShape deepCopy() {
		AffineTransform affineTransform = new AffineTransform();
		Shape newShape = affineTransform.createTransformedShape(myShape);
		GEEllipse shape = new GEEllipse();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		
		return shape;
	}
}
