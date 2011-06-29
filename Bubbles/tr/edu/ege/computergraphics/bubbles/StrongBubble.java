package tr.edu.ege.computergraphics.bubbles;

import tr.edu.ege.computergraphics.throwers.Thrower;

/**
 * Patlatilinca puan kazandiran degisik boyutlardaki balonlardan fazlaligi
 * patlatinca daha kucuk balon ortaya cikarmasi.
 * 
 */
public class StrongBubble extends NormalBubble {

	public void belittle() {
		// creating a normal bubble...
		Bubble bubble = new NormalBubble();
		// setting new bubble properties...
		bubble.setX((int) (this.getX() + getR() / 4));
		bubble.setY((int) (this.getY() + getR() / 4));
		bubble.setR(this.getR() / 2);
		bubble.setBubbleColor(this.getBubbleColor());
		// adding bubble to bubble list...
		Thrower.getBubbleList().add(bubble);
	}

	@Override
	public double getR() {
		return super.getR() + 20;
	}
}
