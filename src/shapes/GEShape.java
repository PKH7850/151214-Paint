package shapes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.Serializable;

abstract public class GEShape implements Cloneable, Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public static enum EAnchors { NN, SS, EE, WW, NW, NE, SE, SW, RR, MM };
	private boolean selected;
	protected EAnchors eSelectedAnchor;
	private GEAnchors anchors;
	protected GEAnchors getAnchors() { return this.anchors; }
	
	public EAnchors getSelectedAnchor() { return eSelectedAnchor; }
	
	
	public boolean isSelected() { return selected; }
	protected Color line_color;
	protected Color fill_color;

	public void setSelected(boolean selected) {
		this.selected = selected;
		if (this.selected) {
			this.anchors = new GEAnchors();
		} else {
			this.anchors = null;
		}
	}

	protected Shape myShape;
	public Shape getShape() { return this.myShape; };
	
	public GEShape(Shape shape) {
		this.myShape = shape;
		this.eSelectedAnchor = null;
	}

	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		g2D.setXORMode(g2D.getBackground());
		if(this.line_color != null) {
			g2D.setColor(this.line_color);
		}
		if(this.fill_color != null) {
			g2D.setPaint(fill_color);
			g2D.fill(myShape);
			if(this.line_color != null) {
				g2D.setColor(this.line_color);
			}
		}
		g2D.draw(myShape);
		if (this.selected) {
			Rectangle boundingRect = myShape.getBounds();
			this.anchors.setAchorGeo(boundingRect.x, boundingRect.y, boundingRect.width, boundingRect.height);
			this.anchors.draw(g2D);
		}
	}

	public boolean onShape(int x, int y) {
		if (this.selected) {
			this.eSelectedAnchor = this.anchors.onAnchor(x, y);
			if (eSelectedAnchor != null) {
				return true;
			}
		}
		if (this.myShape.contains(x, y)) {
			this.eSelectedAnchor = EAnchors.MM;
			return true;
		}
		return false;
	}
	
	protected void setShape(Shape shape) {
		this.myShape = shape;
	}
	
	public void setLineColor(Color line_color) {
		this.line_color = line_color;
	}
	
	public Color getLineColor() {
		return line_color;
	}
	
	public void setFillColor(Color fill_color) {
		this.fill_color = fill_color;
	}
	
	public Color getFillColor() {
		return fill_color;
	}

	public void setGraphicsAttributes(GEShape shape) {
		setLineColor(shape.getLineColor());
		setFillColor(shape.getFillColor());
		setSelected(shape.isSelected());
	}

	abstract public void setPoint(int x, int y);
	abstract public void addPoint(int x, int y);
	abstract public void movePoint(int x, int y);
	abstract public void moveShape(int x, int y);
	abstract public void resizeShape(int dw, int dh);
	abstract public void rotateShape(int dw, int dh);
	abstract public GEShape deepCopy();

}
