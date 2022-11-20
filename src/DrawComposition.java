import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.Color;
import javax.swing.JPanel;

public class DrawComposition extends LineDecorator {
	private Graphics g;

	private int lineStartX;
	private int lineStartY;
	private int lineEndX;
	private int lineEndY;

	private int diamondStartX;
	private int diamondStartY;
	private int diamondEndX;
	private int diamondEndY;

	DrawComposition(JPanel panel) {
		g = panel.getGraphics();
	}

	@Override
	public void draw(int x1, int y1, int x2, int y2) {
		lineStartX = x1;
		lineStartY = y1;
		lineEndX = x2;
		lineEndY = y2;

		if (x1 < x2) {
			lineStartX += Config.BOX_WIDTH / 2;
			lineEndX -= Config.BOX_WIDTH / 2;
		} else {
			lineEndX += Config.BOX_WIDTH / 2;
			lineStartX -= Config.BOX_WIDTH / 2;
		}

		if (y1 < y2) {
			lineStartY += Config.BOX_HEIGHT / 2;
			lineEndY -= Config.BOX_HEIGHT / 2;
		} else {
			lineStartY -= Config.BOX_HEIGHT / 2;
			lineEndY += Config.BOX_HEIGHT / 2;
		}

		diamondStartX = lineStartX;
		diamondStartY = lineStartY;

		if (x1 < x2) {
			lineStartX += Config.COMPOSITION_DIAMOND_WIDTH;
		} else {
			lineStartX -= Config.COMPOSITION_DIAMOND_WIDTH;
		}

		super.draw(lineStartX, lineStartY, lineEndX, lineEndY);
		drawDiamond(x1, x2);
	}

	private void drawDiamond(int x1, int x2) {
		diamondEndY = lineStartY;

		if (x1 < x2) {
			diamondEndX = diamondStartX + Config.COMPOSITION_DIAMOND_WIDTH;
		} else {
			diamondEndX = diamondStartX - Config.COMPOSITION_DIAMOND_WIDTH;
		}

		int diamondTopX = (diamondStartX + diamondEndX) / 2;
		int diamondTopY = lineStartY + Config.COMPOSITION_DIAMOND_HEIGHT / 2;

		int diamondBottomX = (diamondStartX + diamondEndX) / 2;
		int diamondBottomY = lineStartY - Config.COMPOSITION_DIAMOND_HEIGHT / 2;

		int[] xPoints = { diamondStartX, diamondTopX, diamondEndX, diamondBottomX };
		int[] yPoints = { diamondStartY, diamondTopY, diamondEndY, diamondBottomY };
		int n = 4;

		Polygon p = new Polygon(xPoints, yPoints, n);

		g.setColor(Color.BLACK);
		g.fillPolygon(p);
	}
}
