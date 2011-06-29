package tr.edu.ege.computergraphics.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import tr.edu.ege.computergraphics.bubbles.Bubble;
import tr.edu.ege.computergraphics.bubbles.DeathBubble;
import tr.edu.ege.computergraphics.bubbles.LifeBubble;
import tr.edu.ege.computergraphics.bubbles.NormalBubble;
import tr.edu.ege.computergraphics.bubbles.RockBubble;
import tr.edu.ege.computergraphics.bubbles.StrongBubble;
import tr.edu.ege.computergraphics.bubbles.SuperBubble;
import tr.edu.ege.computergraphics.image.ImageIconManager;
import tr.edu.ege.computergraphics.throwers.DeathBubbleThrower;
import tr.edu.ege.computergraphics.throwers.LifeBubbleThrower;
import tr.edu.ege.computergraphics.throwers.NormalBubbleThrower;
import tr.edu.ege.computergraphics.throwers.RockBubbleThrower;
import tr.edu.ege.computergraphics.throwers.StrongBubbleThrower;
import tr.edu.ege.computergraphics.throwers.SuperBubbleThrower;
import tr.edu.ege.computergraphics.throwers.Thrower;

public class GamePanel extends JPanel implements Runnable {

	private static final long serialVersionUID = 2412262298669724563L;

	private BufferedImage bufferedImage;

	private Thread thread;

	private List<Thrower> throwers;

	private boolean gameOver = false;

	public GamePanel() {
		super();
		this.addMouseListener(new BubbleBoomListener());
		this.setBorder(new LineBorder(Color.BLACK));
		this.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
		// starting throwers...
		addThrowers();
	}

	public Graphics2D createGraphics2D(int w, int h) {
		Graphics2D g2D = null;
		if (bufferedImage == null || bufferedImage.getWidth() != w
				|| bufferedImage.getHeight() != h) {
			bufferedImage = (BufferedImage) createImage(w, h);
		}
		g2D = bufferedImage.createGraphics();
		g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2D.clearRect(0, 0, w, h);
		return g2D;
	}

	public BufferedImage getBufferedImage() {
		return bufferedImage;
	}

	public List<Thrower> getThrowers() {
		if (throwers == null) {
			throwers = new Vector<Thrower>();
		}
		return throwers;
	}

	public void paint(Graphics g) {
		Dimension size = this.getSize();
		Graphics2D g2D = createGraphics2D(size.width, size.height);
		g2D.setStroke(new BasicStroke(5.0f));

		g2D.drawImage(ImageIconManager.BACKGROUND_IMAGE, -20, -20,
				getSize().width + 40, getSize().height + 40, this);
		if (!isGameOver()) {
			// drawing bubbles to screen...
			drawElements(g2D);
		} else {
			sendGameOverMessage(g2D);
		}

		g2D.dispose();
		g.drawImage(bufferedImage, 0, 0, this);
	}

	private void sendGameOverMessage(Graphics2D g2D) {
		// setting color as dark red...
		g2D.setColor(new Color(150, 0, 0));
		// setting font properties...
		g2D.setFont(new Font("Tahoma", Font.LAYOUT_LEFT_TO_RIGHT, 40));
		String message = "GAME OVER!";
		// getting message width...
		int messageWidth = g2D.getFontMetrics().stringWidth(message);
		// writing message to screen...
		int x = (getSize().width - messageWidth) / 2;
		g2D.drawString(message, x, getSize().height / 2);
	}

	public void run() {
		Thread me = Thread.currentThread();
		while (thread == me) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				break;
			}
			repaint();
		}
		thread = null;
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.setPriority(Thread.MIN_PRIORITY);
			thread.start();
			// start throwers...
			for (Thrower thrower : this.getThrowers()) {
				thrower.start();
			}
		}
	}

	public synchronized void stop() {
		for (Thrower thrower : this.getThrowers()) {
			thrower.stop();
		}
		thread = null;
		setGameOver(true);
		repaint();
	}

	private void addThrower(Thrower thrower) {
		this.getThrowers().add(thrower);
	}

	private void addThrowers() {
		this.addThrower(new NormalBubbleThrower());
		this.addThrower(new LifeBubbleThrower());
		this.addThrower(new DeathBubbleThrower());
		this.addThrower(new SuperBubbleThrower());
		this.addThrower(new StrongBubbleThrower());
		this.addThrower(new RockBubbleThrower());
	}

	private void boomBubble(Bubble bubble) {
		BubblesGame.getInstance().updateScore(bubble.getPoint());
		getBubbleList().remove(bubble);
		if (bubble instanceof LifeBubble) {
			BubblesGame.getInstance().increaseLife();
		}
		if (bubble instanceof DeathBubble) {
			BubblesGame.getInstance().decreaseLife();
		}
		if (bubble instanceof SuperBubble) {
			BubblesGame.getInstance().boomAll();
		}
		if (bubble instanceof StrongBubble) {
			((StrongBubble) bubble).belittle();
		}
		if (bubble instanceof RockBubble) {
			RockBubble rockBubble = (RockBubble) bubble;
			rockBubble.setRock(true);
			RockBubbleThrower.getRockBubbleList().add(rockBubble);
		}
	}

	private void checkBubblesToBoom(MouseEvent mouseEvent) {
		// check that point is on a bubble...
		for (int i = 0; i < getBubbleList().size(); i++) {
			Bubble bubble = getBubbleList().get(i);
			if (bubble.boom(mouseEvent.getPoint())) {
				boomBubble(bubble);
			}
		}
	}

	private void drawBubbles(Graphics2D g2D) {
		for (int i = 0; i < getBubbleList().size(); i++) {
			Bubble bubble = getBubbleList().get(i);
			if (isOnFloor(bubble) || isIntersectWithRock(bubble)) {
				removeBubble(bubble);
			} else {
				bubble.move();
				bubble.drawBubble(g2D);
			}
		}
	}

	private void drawElements(Graphics2D g2D) {
		drawRocks(g2D);
		drawBubbles(g2D);
	}

	private void drawRocks(Graphics2D g2D) {
		for (RockBubble rockBubble : RockBubbleThrower.getRockBubbleList()) {
			rockBubble.drawBubble(g2D);
		}
	}

	private List<Bubble> getBubbleList() {
		return Thrower.getBubbleList();
	}

	private boolean isIntersectWithRock(Bubble bubble) {
		for (RockBubble rockBubble : RockBubbleThrower.getRockBubbleList()) {
			boolean isTouchedToRock = bubble.getEllipse().intersects(
					rockBubble.getRectangle());
			if (isTouchedToRock) {
				return true;
			}
		}
		return false;
	}

	private boolean isOnFloor(Bubble bubble) {
		boolean onFloor = bubble.getY() + bubble.getR() > getSize().height;
		return onFloor;
	}

	private void removeBubble(Bubble bubble) {
		getBubbleList().remove(bubble);
		if (bubble instanceof NormalBubble) {
			BubblesGame.getInstance().decreaseLife();
		}
	}

	private class BubbleBoomListener implements MouseListener {
		public void mouseClicked(MouseEvent mouseEvent) {
			checkBubblesToBoom(mouseEvent);
		}

		public void mouseEntered(MouseEvent arg0) {

		}

		public void mouseExited(MouseEvent arg0) {

		}

		public void mousePressed(MouseEvent arg0) {

		}

		public void mouseReleased(MouseEvent arg0) {

		}

	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
}
