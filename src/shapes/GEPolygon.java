package shapes;
import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public class GEPolygon extends GEShape {

	private static final long serialVersionUID = 1L;
	
	private Polygon polygon;
	
	public GEPolygon() {
		super(new Polygon());
		this.polygon = (Polygon) this.getShape();
	}

	@Override
	public void setPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	@Override
	public void addPoint(int x, int y) {
		this.polygon.addPoint(x, y);
	}

	@Override
	public void movePoint(int x, int y) {
		this.polygon.xpoints[this.polygon.npoints-1] = x;
		this.polygon.ypoints[this.polygon.npoints-1] = y;
	}

	@Override
	public void moveShape(int x, int y) {
		this.polygon.translate(x, y);
	}

	@Override
	public void resizeShape(int dw, int dh) {
		switch(eSelectedAnchor) {
		case EE : this.polygon.xpoints[0] = this.polygon.xpoints[0] + dw; break;
		case SS : this.polygon.xpoints[0] = this.polygon.xpoints[0] + dh; break;
		case SE : this.polygon.xpoints[0] = this.polygon.xpoints[0] + dw;
				  this.polygon.xpoints[0]= this.polygon.xpoints[0] + dh; break;
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
