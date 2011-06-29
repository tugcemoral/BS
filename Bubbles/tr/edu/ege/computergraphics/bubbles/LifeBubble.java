package tr.edu.ege.computergraphics.bubbles;

import java.awt.Color;
import java.awt.Graphics2D;

import tr.edu.ege.computergraphics.gui.BubblesGame;
import tr.edu.ege.computergraphics.image.ImageIconManager;

/**
 * Patlatinca hak kazandiracak balon. Gelebilecek en kucuk balon boyutunda.
 * 
 */
public class LifeBubble extends Bubble {

	public int getAcceleration() {
		return 4;
	}

	public int getPoint() {
		return 0;
	}

	public double getR() {
		return 20;
	}

	@Override
	public void drawBubble(Graphics2D g2D) {
		g2D.drawImage(ImageIconManager.LIFE_BUBBLE_IMAGE, getX(), getY(), BubblesGame
				.getInstance().getGamePanel());
	}

	@Override
	public Color getGradientColor() {
		return null;
	}
}
