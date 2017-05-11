package menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JMenuItem;

import shapes.GEShape;
import constants.GEConstant.EEditMenuItem;

public class GEEditMenu extends GEMenu {
	
	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	private Vector<GEShape> copyList;
	
	public GEEditMenu(){
		super();
		actionListener = new ActionHandler();
		this.menuItems = new Vector<JMenuItem>();
		
		for (EEditMenuItem eMenuItem: EEditMenuItem.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(eMenuItem.getName());
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.menuItems.add(menuItem);
			this.add(menuItem);		
		}
		copyList = new Vector<GEShape>();
	}
	
	private void redo() {
		this.drawingPanel.redo();
	}
	
	private void undo() {
		this.drawingPanel.undo();
	}
	
	private void copy() {
		copyList.clear();
		copyList.addAll(drawingPanel.copy());
	}
	
	public void paste() {
		drawingPanel.paste(copyList);
	}
	

	private void cut() {
		copyList.clear();
		copyList.addAll(drawingPanel.cut());
	}
	
	private void delete() {
		drawingPanel.delete();
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(EEditMenuItem.valueOf(e.getActionCommand()))	{
			case copy : copy(); break;
			case cut : cut(); break;
			case paste : paste(); break;
			case delete : delete(); break;
			case redo : redo(); break;
			case undo : undo(); break;
			case group : group(); break;
			case ungroup : ungroup(); break;
			case select_all : select_all(); break;
			default:
				break;
			}
		}

		private void group() {
			// TODO Auto-generated method stub
			
		}

		private void ungroup() {
			// TODO Auto-generated method stub
			
		}

		private void select_all() {
			// TODO Auto-generated method stub
			
		}
	}
}
