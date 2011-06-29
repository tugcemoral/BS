package tr.edu.ege.computergraphics.bubbles;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import tr.edu.ege.computergraphics.gui.BubblesGame;
import tr.edu.ege.computergraphics.image.ImageIconManager;

/**
 * Patlatinca kayaya donusen balon sinifi.
 */
public class RockBubble extends NormalBubble {

	private static final Color BROWN = new Color(139, 69, 19);

	private Rectangle2D rectangle2D;

	// basta balon sonradan kaya olacak...
	private boolean isRock = false;

	@Override
	public void drawBubble(Graphics2D g2D) {
		if (!isRock()) {
			super.drawBubble(g2D);
		} else {
			g2D.drawImage(ImageIconManager.ROCK_BUBBLE_IMAGE, getX(), getY(),
					BubblesGame.getInstance().getGamePanel());
		}
	}

	@Override
	public Color getGradientColor() {
		return BROWN;
	}

	@Override
	public int getPoint() {
		return 0;
	}

	public boolean isRock() {
		return isRock;
	}

	public void setRock(boolean isRock) {
		this.isRock = isRock;
	}

	@Override
	public double getR() {
		if (isRock()) {
			return 50;
		}
		return super.getR();
	}

	public Rectangle2D getRectangle() {
		if (rectangle2D == null) {
			rectangle2D = new Rectangle2D.Double(getX(), getY(), getR(), getR());
		}
		return rectangle2D;
	}
}
