package tr.edu.ege.computergraphics.bubbles;

import java.awt.Color;

/**
 * Degisik boyutlarda olan patlatilinca puan kazandiran baloncuklarin sinifi.
 * 
 */
public class NormalBubble extends Bubble {

	protected int point;

	public NormalBubble() {
	}

	public int getAcceleration() {
		if (acceleration == 0) {
			acceleration = (int) (Math.random() * 2) + 1;
		}
		return acceleration;
	}

	public Color getBubbleColor() {
		if (bubbleColor == null) {
			int red = (int) (Math.random() * 255);
			int blue = (int) (Math.random() * 255);
			int green = (int) (Math.random() * 255);
			// setting the color of the ball...
			bubbleColor = new Color(red, blue, green);
		}
		return bubbleColor;
	}

	public Color getGradientColor() {
		return Color.WHITE;
	}

	public int getPoint() {
		if (point == 0) {
			double coefficient = (double) (getAcceleration() / getR());
			point = (int) (coefficient * 1000);
		}
		return point;
	}

	public double getR() {
		if (R == 0) {
			R = (20 + (Math.random() * 40));
		}
		return R;
	}
}
