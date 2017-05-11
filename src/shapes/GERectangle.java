package shapes;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GERectangle extends GEShape {

	private static final long serialVersionUID = 1L;
	
	protected Rectangle rectangle;
	
	public GERectangle(){
		super(new Rectangle());
		this.rectangle = (Rectangle) this.getShape();
	}
	
	@Override
	public void setPoint(int x, int y) {
		this.rectangle.setLocation(x, y);
	}

	@Override
	public void addPoint(int x, int y) {
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
	}

	@Override
	public void movePoint(int x, int y) {
		this.rectangle.setSize(x-this.rectangle.x, y-this.rectangle.y);
	}

	@Override
	public void moveShape(int x, int y) {
		this.rectangle.setLocation(this.rectangle.x+x, this.rectangle.y+y);
	}
	
	public void resizeShape(int dw, int dh) {
		switch(eSelectedAnchor) {
		case EE : this.rectangle.width = this.rectangle.width + dw; break;
		case SS : this.rectangle.height = this.rectangle.height + dh; break;
		case SE : this.rectangle.width = this.rectangle.width + dw;
				  this.rectangle.height = this.rectangle.height + dh; break;
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
		GERectangle shape = new GERectangle();
		shape.setShape(newShape);
		shape.setGraphicsAttributes(this);
		
		return shape;
	}

}
