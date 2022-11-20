import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawLine implements ConnectClassInterface {
	private Graphics g;

	DrawLine(JPanel panel) {
		g = panel.getGraphics();
	}

	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		// int lineStartX = x1;
		// int lineStartY = y1;
		// int lineEndX = x2;
		// int lineEndY = y2;

		// if (x1 < x2) {
		// lineStartX += Config.BOX_WIDTH / 2;
		// lineEndX -= Config.BOX_WIDTH / 2;
		// } else {
		// lineEndX += Config.BOX_WIDTH / 2;
		// lineStartX -= Config.BOX_WIDTH / 2;
		// }

		g.drawLine(x1, y1, x2, y2);
	}

}
