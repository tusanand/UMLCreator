import javax.swing.JPanel;
import java.awt.Graphics;

public class DrawAssociation extends LineDecorator {
	private Graphics g;
	private int lineStartX;
	private int lineStartY;
	private int lineEndX;
	private int lineEndY;

	DrawAssociation(JPanel panel) {
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

		super.draw(lineStartX, lineStartY, lineEndX, lineEndY);
		drawArrowHead();
	}

	private void drawArrowHead() {
		int arrowStartX = lineEndX;
		int arrowStartY = lineEndY;

		double slope = (lineEndY - lineStartY) / (double) (lineEndX - lineStartX);
		double theta = Math.atan(slope);

		double thetaLeft;
		if (lineStartX < lineEndX) {
			thetaLeft = theta + (Math.PI + (Math.PI / 4));
		} else {
			thetaLeft = theta + (Math.PI / 4);
		}
		double slopeLeft = Math.tan(thetaLeft);

		double thetaRight;
		if (lineStartX < lineEndX) {
			thetaRight = theta - (Math.PI + (Math.PI / 4));
		} else {
			thetaRight = theta - (Math.PI / 4);
		}
		double slopeRight = Math.tan(thetaRight);

		int arrow1EndX = (int) (Config.ASSOCIATION_ARROW_SIZE * Math.cos(thetaLeft) +
				arrowStartX);
		int arrow1EndY = (int) (slopeLeft * (arrow1EndX - arrowStartX) +
				arrowStartY);

		int arrow2EndX = (int) (Config.ASSOCIATION_ARROW_SIZE * Math.cos(thetaRight)
				+ arrowStartX);
		int arrow2EndY = (int) (slopeRight * (arrow2EndX - arrowStartX) +
				arrowStartY);

		g.drawLine(arrowStartX, arrowStartY, arrow1EndX, arrow1EndY);
		g.drawLine(arrowStartX, arrowStartY, arrow2EndX, arrow2EndY);
	}
}
