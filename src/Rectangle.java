import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JPanel;

/**
 * This class creates the rectangle to represent classes.
 */
public class Rectangle implements DrawRectangleInterface {

	private Graphics g;

	public Rectangle(JPanel panel) {
		g = panel.getGraphics();
	}

	@Override
	public void draw(int x, int y, String name) {
		g.setColor(Color.YELLOW);
		g.fillRect(x - Config.BOX_WIDTH / 2, y - Config.BOX_HEIGHT / 2, Config.BOX_WIDTH, Config.BOX_HEIGHT);
		g.setColor(Color.BLACK);
		g.drawRect(x - Config.BOX_WIDTH / 2, y - Config.BOX_HEIGHT / 2, Config.BOX_WIDTH, Config.BOX_HEIGHT);
		g.drawString(name, x - Config.BOX_WIDTH / 2 + Config.BOX_TEXT_OFFSET, y);
	}

}
