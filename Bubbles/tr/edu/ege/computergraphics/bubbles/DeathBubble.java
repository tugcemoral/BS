package tr.edu.ege.computergraphics.bubbles;

import java.awt.Color;
import java.awt.Graphics2D;

import tr.edu.ege.computergraphics.gui.BubblesGame;
import tr.edu.ege.computergraphics.image.ImageIconManager;

/**
 * Patlatinca oyuncuyu olduren, oyunu bitiren balon. Gelebilecek en buyuk balon
 * boyutunda.
 * 
 */
public class DeathBubble extends Bubble {

	public int getAcceleration() {
		return 1;
	}

	public int getPoint() {
		return 0;
	}

	public double getR() {
		return 60;
	}

	@Override
	public void drawBubble(Graphics2D g2D) {
		g2D.drawImage(ImageIconManager.DEATH_BUBBLE_IMAGE, getX(), getY(), BubblesGame
				.getInstance().getGamePanel());
	}

	@Override
	public Color getGradientColor() {
		return null;
	}

}
