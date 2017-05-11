package frame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JPanel;

import shapes.GEShape;
import shapes.GEShape.EAnchors;
import transformer.GEDrawer;
import transformer.GEMover;
import transformer.GEResizer;
import transformer.GERotator;
import transformer.GETransformer;
import constants.GEConstant;
import constants.GEConstant.EState;
import entity.GEModel;


public class GEPanel extends JPanel {
	// attributes
	private static final long serialVersionUID = 1L;
	private boolean bUpdated;
	public boolean isUpdated() { return this.bUpdated; }

	// components				외부에 노출 시키면 안됨.
	private MouseHandler mouseHandler;
	private Vector<GEShape> shapes;
	public Vector<GEShape> getShapes() { return this.shapes; }
	public void setShapes(Vector<GEShape> shapes) { this.shapes = shapes; }
	
	// association variables	외부와 자신을 연결해 주는 값
	private GEShape currentTool, tempTool, defaultTool;
	private boolean first = true;

	public void setCurrentTool(GEShape currentTool) { 
		this.currentTool = currentTool; this.tempTool = currentTool; 
		if (first) {
			this.defaultTool = currentTool;
			first = false;
		}
	}
	
	// working variables
	private GETransformer currentTransformer;
	private GEShape selectedShape;
	private void setSelectedShape(GEShape selectedShape) { 
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(false);
		}
		this.selectedShape = selectedShape; 
		if (this.selectedShape != null) {
			this.selectedShape.setSelected(true);
		}
	}
	
	private GEShape getSelectedShape() { return this.selectedShape; }
	
	private Color line_color;
	private Color fill_color;
	private GEUndoStack undoStack;
	
	public GEPanel() {
		// class attribute
		this.bUpdated = false;
		// create components
		this.shapes = new Vector<GEShape>();
		
		mouseHandler = new MouseHandler();
		this.addMouseListener(mouseHandler);
		this.addMouseMotionListener(mouseHandler);
		
		this.undoStack = new GEUndoStack();
	}

	public void init() {}
	
	public void newShapes() {
		this.shapes.removeAllElements();
		this.repaint();
	}
	
	public void redo() {
		if (this.shapes!=null) {
			if ((Vector<GEShape>) this.undoStack.redo() != null) {
				this.shapes = (Vector<GEShape>) this.undoStack.redo();
			}
		}
		repaint();
	}
	
	public void undo() {
		if (this.shapes != null) {
			this.shapes = (Vector<GEShape>) this.undoStack.pop();
		}
		repaint();
	}
	
	public void setLineColor(Color line_color) {
		this.line_color = line_color;
	}	

	public void setFillColor(Color fill_color) {
		this.fill_color = fill_color;
	}

	public Vector<GEShape> copy() {
		Vector<GEShape> returnList = new Vector<GEShape>();
		for (GEShape shape: shapes) {
			if (shape.isSelected()) {
				returnList.add(shape.deepCopy());
			}
		}
		return returnList;
	}
	

	public void paste(Vector<GEShape> shapeList) {
		for (GEShape shape: shapeList) {
			shapes.add(shape.deepCopy());
		}
		repaint();
	}
	
	public Vector<GEShape> cut() {
		Vector<GEShape> returnList = new Vector<GEShape>();
		for (int i = shapes.size(); i > 0; i--) {
			GEShape shape = shapes.get(i-1);
			if (shape.isSelected()) {
				returnList.add(0, shape.deepCopy());
				shapes.remove(shape);
			}
		}
		repaint();
		return returnList;
	}

	public void delete() {
		for (int i = shapes.size(); i > 0; i--) {
			GEShape shape = shapes.get(i-1);
			if (shape.isSelected()) {
				shapes.remove(shape);
			}
		}
		repaint();
	}
	
	public void clearSelectedShapes() {
		for (GEShape shape : this.shapes) {
			shape.setSelected(false);
		}
	}
	
	@SuppressWarnings("unchecked")
	public void readShapes(String fileName) {
		try {
			this.shapes = (Vector<GEShape>) GEModel.read(fileName);
			this.bUpdated = false;	
			this.repaint();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

	public void saveShapes(String fileName) {
		try {
			GEModel.save(fileName, this.shapes);
			this.bUpdated = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for (GEShape shape: this.getShapes()) {
			shape.draw(g);
		}
	}
	
	// check whether the mouse pointer is on a shape or not
	private GEShape onShape(int x, int y) {
		for (GEShape shape: this.getShapes()) {
			if (shape.onShape(x, y)) {
				return shape;
			}
		}
		return null;
	}
	
	private void initTransforming(int x, int y) {
		this.currentTransformer.initTransforming((Graphics2D)this.getGraphics(),x,y);
	}
	
	private void keepTransforming(int x, int y) {
		this.currentTransformer.keepTransforming((Graphics2D)this.getGraphics(),x,y);
	}
	
	private void continueTransforming(int x,int y) {
		this.currentTransformer.continueTransforming((Graphics2D)this.getGraphics(),x,y);
	}
	
	private void finishTransforming(int x, int y) {
		this.currentTransformer.finishTransforming((Graphics2D)this.getGraphics(),x,y);
		this.currentTool = tempTool;
		this.bUpdated = true;
		this.undoStack.push(this.shapes);

		repaint();
	}
	
	private void setCursor(int x, int y) {
		GEShape shape = onShape(x, y);
		if (shape == null) {
			this.setCursor(GEConstant.DEFAULT_CURSOR);
		} else {
			switch (shape.getSelectedAnchor()) {
			case EE: setCursor(GEConstant.EE_RESIZE_CURSOR); break;
			case WW: setCursor(GEConstant.WW_RESIZE_CURSOR); break;
			case SS: setCursor(GEConstant.SS_RESIZE_CURSOR); break;
			case NN: setCursor(GEConstant.NN_RESIZE_CURSOR); break;
			case NE: setCursor(GEConstant.NE_RESIZE_CURSOR); break;
			case NW: setCursor(GEConstant.NW_RESIZE_CURSOR); break;
			case SE: setCursor(GEConstant.SE_RESIZE_CURSOR); break;
			case SW: setCursor(GEConstant.SW_RESIZE_CURSOR); break;
			case RR: setCursor(GEConstant.R_CURSOR); break;
			case MM: setCursor(GEConstant.MOVE_CURSOR);break;
			default:
			}
		}
	}
	
	private class MouseHandler implements MouseListener, MouseMotionListener {

		private EState eDrawingState = EState.idle;
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if (e.getClickCount() == 1) {	
				mouse1Clicked(e);
			} else if (e.getClickCount() == 2) {
				mouse2Clicked(e);				
			}
		}
		
		private void mouse1Clicked(MouseEvent e) {
			if (eDrawingState == EState.idle) {
				if (currentTool.getClass().getSimpleName().equals("GEPolygon")){
					try {
						setSelectedShape(currentTool.getClass().newInstance());
					} catch (InstantiationException | IllegalAccessException e1) {
						e1.printStackTrace();
					}
					currentTransformer = new GEDrawer(getSelectedShape());
					initTransforming(e.getX(), e.getY());
					eDrawingState = EState.drawingNP;
				}
			} else if (eDrawingState == EState.drawingNP) {
				continueTransforming(e.getX(), e.getY());
			}
		}
		
		private void mouse2Clicked(MouseEvent e) {
			if (eDrawingState == EState.drawingNP) {
				finishTransforming(e.getX(), e.getY());
				getSelectedShape().setLineColor(line_color);
				getSelectedShape().setFillColor(fill_color);
				getShapes().add(getSelectedShape());
				eDrawingState = EState.idle;
			}
		}
		
		@Override
		public void mouseMoved(MouseEvent e) {
			if (eDrawingState == EState.idle){
				setCursor(e.getX(), e.getY());
			} else if (eDrawingState == EState.drawingNP) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mousePressed(MouseEvent e) {
			try {
				if (eDrawingState == EState.idle){ // 마우스 밑에 그림 있는지 확인
					setSelectedShape(onShape(e.getX(), e.getY()));
					if (getSelectedShape() == null) { // 그림이 없으면
						clearSelectedShapes();
						setSelectedShape(currentTool.getClass().newInstance());
						currentTransformer = new GEDrawer(getSelectedShape());
						if (!currentTool.getClass().getSimpleName().equals("GEPolygon") && !currentTool.getClass().getSimpleName().equals("GEDefault")) { // 폴리곤이 아니면
							initTransforming(e.getX(), e.getY()); // 그림 그리기
							eDrawingState = EState.drawingTP;
						} else if (currentTool.getClass().getSimpleName().equals("GEDefault")) {
							eDrawingState = EState.idle;
						}
					} else { // 그림이 밑에 있으면 
						clearSelectedShapes();
						getSelectedShape().setSelected(true);
						if (getSelectedShape().getSelectedAnchor()==EAnchors.MM) {
							currentTransformer = new GEMover(getSelectedShape());
						} else if (getSelectedShape().getSelectedAnchor()==EAnchors.RR) {
							currentTransformer = new GERotator(getSelectedShape());
						} else {
							currentTransformer = new GEResizer(getSelectedShape());
						}
						currentTool = getSelectedShape();
						initTransforming(e.getX(), e.getY()); // 움직임	
						eDrawingState = EState.moving;
					}
				}
			} catch (InstantiationException | IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		
		@Override
		public void mouseDragged(MouseEvent e) {
			if (eDrawingState == EState.drawingTP) {
				keepTransforming(e.getX(), e.getY());
			} else if (eDrawingState == EState.moving) {
				keepTransforming(e.getX(), e.getY());
			}
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
			if (eDrawingState == EState.drawingTP) {
				finishTransforming(e.getX(), e.getY());
				getShapes().add(getSelectedShape());
				getSelectedShape().setSelected(true);
				getSelectedShape().setLineColor(line_color);
				getSelectedShape().setFillColor(fill_color);
//				GETransformer currentTransformer = getSelectedShape();
//				if (currentTransformer.isValidShape()) {
//					getShapes().add(getSelectedShape());
//				}
				eDrawingState = EState.idle;
			} else if (eDrawingState == EState.moving) {
				finishTransforming(e.getX(), e.getY());
				eDrawingState = EState.idle;
			}
			if (!currentTool.getClass().getSimpleName().equals("GEPolygon")) {
				currentTool = defaultTool;
			}
		}
		
		@Override
		public void mouseEntered(MouseEvent e) {}

		@Override
		public void mouseExited(MouseEvent e) {}
	}
}
