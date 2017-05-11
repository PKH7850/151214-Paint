package menu;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JColorChooser;
import javax.swing.JMenuItem;

import constants.GEConstant.EColorMenuItem;

public class GEColorMenu extends GEMenu {

	private static final long serialVersionUID = 1L;
	private Vector<JMenuItem> menuItems;
	private ActionListener actionListener;
	
	public GEColorMenu(){
		super();
		this.menuItems = new Vector<JMenuItem>();
		actionListener = new ActionHandler();
		for (EColorMenuItem eMenuItem: EColorMenuItem.values()){
			JMenuItem menuItem = new JMenuItem();
			menuItem.setText(eMenuItem.getName());
			menuItem.addActionListener(actionListener);
			menuItem.setActionCommand(menuItem.getName());
			this.menuItems.add(menuItem);
			this.add(menuItem);		
		}
	}
	
	private void setLineColor() {
		Color line_color = JColorChooser.showDialog(null, "line_color", null);
		if(line_color != null) {
			this.drawingPanel.setLineColor(line_color);
		}
	}

	private void setFillColor() {
		Color fill_color = JColorChooser.showDialog(null, "fill_color", null);
		if(fill_color != null) {
			this.drawingPanel.setFillColor(fill_color);
		}
	}

	private void setDefaultColor() {
		this.drawingPanel.setLineColor(Color.BLACK);
		this.drawingPanel.setFillColor(null);
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			switch(EColorMenuItem.valueOf(e.getActionCommand()))	{
			case line_color : setLineColor(); break;
			case fill_color : setFillColor(); break;
			case default_color : setDefaultColor(); break;
			default:
				break;
			}
		}
	}
}
