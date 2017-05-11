package constants;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JMenu;

import menu.GEColorMenu;
import menu.GEEditMenu;
import menu.GEFileMenu;
import shapes.GEDefault;
import shapes.GEEllipse;
import shapes.GEGroup;
import shapes.GELine;
import shapes.GEPolygon;
import shapes.GERectangle;
import shapes.GEShape;

public class GEConstant {
	public static final int FRAME_W = 1000;
	public static final int FRAME_H = 1000;
	
	public static final int TOOLBAR_WIDTH = 400;
	public static final int TOOLBAR_HEIGHT = 100;
	
	public static final Color ANCHOR_FILLCOLOR = Color.WHITE;
	public static final Color ANCHOR_LINECOLOR = Color.BLACK;
	
	public static Cursor DEFAULT_CURSOR = new Cursor(Cursor.DEFAULT_CURSOR);
	public static Cursor DRAW_CURSOR = new Cursor(Cursor.MOVE_CURSOR);
	public static Cursor MOVE_CURSOR = new Cursor(Cursor.MOVE_CURSOR);
	public static Cursor HAND_CURSOR = new Cursor(Cursor.WAIT_CURSOR);
	public static Cursor EE_RESIZE_CURSOR = new Cursor(Cursor.E_RESIZE_CURSOR);
	public static Cursor WW_RESIZE_CURSOR = new Cursor(Cursor.W_RESIZE_CURSOR);
	public static Cursor SS_RESIZE_CURSOR = new Cursor(Cursor.S_RESIZE_CURSOR);
	public static Cursor NN_RESIZE_CURSOR = new Cursor(Cursor.N_RESIZE_CURSOR);
	public static Cursor NE_RESIZE_CURSOR = new Cursor(Cursor.NE_RESIZE_CURSOR);
	public static Cursor NW_RESIZE_CURSOR = new Cursor(Cursor.NW_RESIZE_CURSOR);
	public static Cursor SE_RESIZE_CURSOR = new Cursor(Cursor.SE_RESIZE_CURSOR);
	public static Cursor SW_RESIZE_CURSOR = new Cursor(Cursor.SW_RESIZE_CURSOR);
	public static Cursor R_CURSOR = new Cursor(Cursor.WAIT_CURSOR);
	
	
	public static enum EState {	idle, drawingTP, drawingNP, moving };
	
	public static final String RSC = "rsc/";
	public static final int MAXSIZE = 10;
	
	public static enum EMenu {
		file("file", new GEFileMenu()),
		edit("edit", new GEEditMenu() ),
		color("color", new GEColorMenu());
		
		private String name;
		private JMenu menu;
	
		private EMenu(String name, JMenu menu){			
			this.name = name;
			this.menu = menu;
		}
		
		public String getName(){
			return name;
		}
		
		public JMenu newMenu(){
			return menu;
		}
	}
	
	public enum EFileMenuItem {
		newFile("newFile"), 
		open("open"), 
		save("save"),
		saveAs("saveAs"),
		close("close"),
		print("print"),
		exit("exit");

		private String fname;
		
		private EFileMenuItem(String fname){
			this.fname = fname;
		}
			
		public String getName() {
			return fname;
		}
	}
	
	public static String SFILECONFIG = "config\\file.config";
	public static String SWORKSPACE = "data\\";
	public static String SSAVEORNOT = "변경된 사항을 저장하시겠습니까?";
	public static String SFILEKIND = "Graphics Editor";
	public static String SFILEEXTENSION = "gps";
	public static String SSAVESURE = "정말 저장하시겠습니까?";
	
	public enum EEditMenuItem {
		copy("copy"),
		cut("cut"),
		paste("paste"),
		delete("delete"),
		redo("redo"),
		undo("undo"),
		group("group"),
		ungroup("ungroup"),
		select_all("select_all");
		
		private String ename;
		
		private EEditMenuItem(String ename){
			this.ename = ename;
		}
		
		public String getName() {
			return ename;
		}
	}
	
	public enum EColorMenuItem {
		line_color("line_color"),
		fill_color("fill_color"),
		default_color("default_color");
		
		private String cname;
		
		private EColorMenuItem(String cname){
			this.cname = cname;
		}

		public String getName() {
			return cname;
		}
	}
	
	public final static int DEFAULT_DASH_OFFSET = 4;
	public final static int DEFAULT_DASHEDLINE_WIDTH = 1;
	
	public static enum EToolbar {
		ellipse("ellipse_b.png", "ellipse_w.png", new GEEllipse()),
		rectangle("rectangle_b.png", "rectangle_w.png", new GERectangle()),
		line("line_b.png", "line_w.png", new GELine()),
		polygon("polygon_b.png", "polygon_w.png", new GEPolygon()),
		heart("heart_b.png", "heart_w.png", new GERectangle()),
		group("text_b.png", "text_w.png", new GEGroup()),
		default_tool("default.png", "default.png", new GEDefault());
		
		
		private String tool;
		private String selectedTool;
		private GEShape newShape;
		
		private EToolbar(String tool, String selectedTool, GEShape newShape){			
			this.tool = tool;
			this.selectedTool = selectedTool;
			this.newShape = newShape;
		}
		
		public String getTool(){
			return tool;
		}
		
		public String getSelectedToolbar(){
			return selectedTool;
		}
		public GEShape getShape() {
			return newShape;
		}
	}
}
