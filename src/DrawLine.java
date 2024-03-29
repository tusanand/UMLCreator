import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * This class draws a line.
 */
public class DrawLine implements ConnectClassInterface {
	private Graphics g;

	public DrawLine(JPanel panel) {
		g = panel.getGraphics();
	}

	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		g.drawLine(x1, y1, x2, y2);
	}

}
