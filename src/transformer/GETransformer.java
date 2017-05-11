package transformer;

import java.awt.BasicStroke;
import java.awt.Graphics;

import constants.GEConstant;
import shapes.GEShape;

public abstract class GETransformer {
	// drawing, moving, resizing, rotating
	private GEShape shape;
	protected BasicStroke dashedLineStroke;
	
	public GETransformer(GEShape shape) {
		this.setShape(shape);
		float dashes[] = {GEConstant.DEFAULT_DASH_OFFSET};
		dashedLineStroke = new BasicStroke(
				GEConstant.DEFAULT_DASHEDLINE_WIDTH,
				BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_ROUND, 10, dashes, 0);
	}

	public GEShape getShape() {	return shape; }

	public void setShape(GEShape shape) { this.shape = shape; }
	
	abstract public void initTransforming(Graphics g, int x, int y);
	abstract public void keepTransforming(Graphics g, int x, int y);
	abstract public void continueTransforming(Graphics g, int x, int y);
	abstract public void finishTransforming(Graphics g, int x, int y);
}
