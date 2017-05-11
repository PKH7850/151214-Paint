package frame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import menu.GEMenu;
import constants.GEConstant.EMenu;


public class GEMenuBar extends JMenuBar {

	private static final long serialVersionUID = 1L;
	
	public GEMenuBar() {
		super();
		for (EMenu eMenu: EMenu.values()) {
			JMenu menu = eMenu.newMenu();
			menu.setText(eMenu.getName());
			this.add(menu);
		}
	}
	
	public void init(GEPanel drawingPanel) {
		for (int i=0; i<this.getMenuCount(); i++) {
			GEMenu menu = (GEMenu) this.getMenu(i);
			menu.init(drawingPanel);
		}			
	}
}