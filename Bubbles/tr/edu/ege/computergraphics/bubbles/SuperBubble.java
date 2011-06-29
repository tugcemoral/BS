package tr.edu.ege.computergraphics.bubbles;

import java.awt.Color;
import java.awt.Graphics2D;

import tr.edu.ege.computergraphics.gui.BubblesGame;
import tr.edu.ege.computergraphics.image.ImageIconManager;

/**
 * Patlayinca hepsini patlatan balon.
 * 
 */
public class SuperBubble extends Bubble {

	@Override
	public double getR() {
		return 50;
	}

	@Override
	public int getPoint() {
		return 0;
	}

	@Override
	public Color getGradientColor() {
		return null;
	}

	@Override
	public void drawBubble(Graphics2D g2D) {
		g2D.drawImage(ImageIconManager.SUPER_BUBBLE_IMAGE, getX(), getY(), BubblesGame
				.getInstance().getGamePanel());
	}

	@Override
	public int getAcceleration() {
		return 4;
	}
}
