import java.awt.Graphics;

import javax.swing.JPanel;

public class Rectangle implements DrawRectangleInterface {

	Graphics g;
	
	Rectangle(JPanel panel) {
		g = panel.getGraphics();
	}
	
	@Override
	public void draw(int x, int y, String name) {
		g.drawRect(x-35, y-25, 70, 50);
		g.drawString(name, x-25, y);
	}

}
