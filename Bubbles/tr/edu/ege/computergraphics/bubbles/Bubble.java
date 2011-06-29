package tr.edu.ege.computergraphics.bubbles;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;

import tr.edu.ege.computergraphics.gui.BubblesGame;

/**
 * Soyut balon sinifi.
 * 
 */
public abstract class Bubble {

	protected double R;

	protected int acceleration;

	protected Color bubbleColor;

	private int x;

	private int y;

	private Ellipse2D ellipse2D;

	public boolean boom(Point clickedPoint) {
		double clickedX = clickedPoint.getX();
		double clickedY = clickedPoint.getY();
		return this.getEllipse().intersects(clickedX - 0.05, clickedY - 0.05,
				0.1, 0.1);
	}

	public void drawBubble(Graphics2D g2D) {
		GradientPaint bubblePaint = new GradientPaint(50, 50,
				getGradientColor(), 100, 100, this.getBubbleColor(), true);
		g2D.setPaint(bubblePaint);
		g2D.fill(this.getEllipse());
	}

	public int getAcceleration() {
		return acceleration;
	}

	public Color getBubbleColor() {
		return bubbleColor;
	}

	public Ellipse2D getEllipse() {
		ellipse2D = new Ellipse2D.Double(this.getX(), this.getY(), this.getR(),
				this.getR());
		return ellipse2D;
	}

	public abstract Color getGradientColor();

	public abstract int getPoint();

	public double getR() {
		return R;
	}

	public int getX() {
		if (x == 0) {
			int width = (int) (BubblesGame.getInstance().getGamePanel()
					.getSize().width - getR());
			x = (int) ((Math.random() * width));
		}
		return x;
	}

	public int getY() {
		return y;
	}

	public void move() {
		y = y + getAcceleration();
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	public void setBubbleColor(Color bubbleColor) {
		this.bubbleColor = bubbleColor;
	}

	public void setEllipse2D(Ellipse2D ellipse2D) {
		this.ellipse2D = ellipse2D;
	}

	public void setR(double r) {
		R = r;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
