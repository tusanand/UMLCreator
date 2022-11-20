import java.awt.Graphics;

import javax.swing.JPanel;

public class Rectangle implements DrawRectangleInterface {

	private Graphics g;

	Rectangle(JPanel panel) {
		g = panel.getGraphics();
	}

	@Override
	public void draw(int x, int y, String name) {
		g.drawRect(x - Config.BOX_WIDTH / 2, y - Config.BOX_HEIGHT / 2, Config.BOX_WIDTH, Config.BOX_HEIGHT);
		g.drawString(name, x - Config.BOX_WIDTH / 2 + Config.BOX_TEXT_OFFSET, y);
	}

}
