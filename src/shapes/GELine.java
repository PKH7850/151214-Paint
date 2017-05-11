package shapes;

import java.awt.geom.Line2D;

public class GELine extends GEShape {

	private static final long serialVersionUID = 1L;
	
	private Line2D line;
	
	public GELine(){
		super(new Line2D.Double());
		this.line = (Line2D) this.getShape();
	}

	@Override
	public void setPoint(int x, int y) {
		this.line.setLine(x, y, x, y);
	}


	@Override
	public void addPoint(int x, int y) {
		this.line.setLine(x, y, line.getX2(), line.getY2());
	}


	@Override
	public void movePoint(int x, int y) {
		this.line.setLine(x, y, line.getX2(), line.getY2());
	}


	@Override
	public void moveShape(int x, int y) {
		this.line.setLine(x, y, line.getX2(), line.getY2());
	}

	@Override
	public void resizeShape(int dw, int dh) {
		switch(eSelectedAnchor) {
		case EE : this.line.setLine(line.getX1()+dw, line.getY1(), line.getX2(), line.getY2()); break;
		case SS : this.line.setLine(line.getX1(), line.getY1()+dh, line.getX2(), line.getY2()); break;
		case SE : this.line.setLine(line.getX1()+dw, line.getY1()+dh, line.getX2(), line.getY2()); break;
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
		// TODO 자동 생성된 메소드 스텁
		return null;
	}
}
