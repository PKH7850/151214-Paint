package frame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import constants.GEConstant.EToolbar;


public class GEToolBar extends JToolBar {
	// attributes
	private static final long serialVersionUID = 1L;
	
	// components
	private ButtonGroup buttonGroup;
	private ActionListener actionListener;
	
	// associations
	private GEPanel drawingPanel;

	public GEToolBar() {
		super();
		
		buttonGroup = new ButtonGroup();
		actionListener = new ActionHandler();
		
		for(EToolbar tool : EToolbar.values()) {
			JRadioButton buttons = new JRadioButton();
			buttons.setIcon(new ImageIcon("rsc/" + tool.getTool()));
			buttons.setSelectedIcon(new ImageIcon("rsc/" + tool.getSelectedToolbar()));
			buttonGroup.add(buttons);
			this.add(buttons);
			buttons.addActionListener(actionListener);
			buttons.setActionCommand(tool.name());
		}
	}
	
	public void init(GEPanel panel) {
		// association
		this.drawingPanel = panel;		// drawingPanel이 연결된 후에 ActionHandler가 호출 되어야 함.
		// association attribute
		((JRadioButton)this.getComponent(EToolbar.default_tool.ordinal())).doClick();	// this 는 JToolBar
		
	}
	
	private class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e){
			drawingPanel.setCurrentTool(EToolbar.valueOf(e.getActionCommand()).getShape());
		}
	}
}
