import java.awt.Graphics;

import javax.swing.JPanel;

public class Rectangle implements DrawInterface {

	Graphics g;
	
	Rectangle(JPanel panel) {
		g = panel.getGraphics();
	}
	
	@Override
	public void draw(int x, int y) {
		g.drawRect(x-35, y-25, 70, 50);
	}

}
