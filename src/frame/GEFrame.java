package frame;
import java.awt.BorderLayout;

import javax.swing.JFrame;

import constants.GEConstant;

public class GEFrame extends JFrame {
	// attributes
	
	private static final long serialVersionUID = 1L;
	// �� Ŭ������ ���� ID�� ����.
	
	// components
	private GEMenuBar menuBar;
	private GEPanel panel;
	private GEToolBar toolbar;

	public GEFrame() {
		super();
		// attributes initialization (�Ӽ� �ʱ�ȭ)
		this.setSize(GEConstant.FRAME_W, GEConstant.FRAME_H);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// components life cycle management 
		// �޴��ٿ� ���ٸ� drawing panel�� �����Ŵ
		
		menuBar = new GEMenuBar();
		this.setJMenuBar(menuBar);
		
		panel = new GEPanel();
		this.add(panel, BorderLayout.CENTER);
		
		toolbar = new GEToolBar();
		this.add(toolbar, BorderLayout.NORTH);
	}
	
	public void init() {
		menuBar.init(panel);
		toolbar.init(panel);		
		panel.init();
		
		this.setVisible(true);
	}
}