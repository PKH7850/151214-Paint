package frame;

import java.util.Vector;

import constants.GEConstant;
import shapes.GEShape;


public class GEUndoStack {
	private Vector<Vector<GEShape>> stack, redoStack, tempStack;
	
	public GEUndoStack() {
		this.stack = new Vector<Vector<GEShape>>();
		this.redoStack = new Vector<Vector<GEShape>>();
		this.tempStack = new Vector<Vector<GEShape>>();
	}
	
	@SuppressWarnings("unchecked")
	public void push(Vector<GEShape> shapes) {
		this.stack.add((Vector<GEShape>) shapes.clone());
		this.tempStack.addAll(this.stack);
		this.redoStack.addAll(this.stack);
		if (this.stack.size() > GEConstant.MAXSIZE) {
			this.stack.remove(0);
		} 
	}
	
	public Vector<GEShape> pop() {
		if (this.stack.size() > 0) {
			return this.stack.remove(this.stack.size()-1);
		}
		return this.tempStack.firstElement();
	}
	
	public Vector<GEShape> redo() {
		this.stack.addAll(this.redoStack);
		return this.redoStack.elementAt(redoStack.size()-1);
	}
}
