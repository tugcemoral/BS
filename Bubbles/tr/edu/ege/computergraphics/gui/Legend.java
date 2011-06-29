package tr.edu.ege.computergraphics.gui;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tr.edu.ege.computergraphics.image.ImageIconManager;

public class Legend extends JFrame {

	private static final long serialVersionUID = 7612996144853303903L;

	private JPanel legendPanel;

	private JLabel jlblLifeIconDescription;

	private JLabel jlblLifeIcon;

	private JLabel jlblSuperBubbleDescription;

	private JLabel jlblSuperBubbleImage;

	private JLabel jlblDeathBubbleDescription;

	private JLabel jlblDeathBubbleImage;

	private JLabel jlblLifeBubbleDescription;

	private JLabel jlblLifeBubbleImage;

	private JLabel jlblRockBubbleDescription;

	private JLabel jlblRockBubbleImage;

	public Legend() {
		super("The Legend");
		init();
	}

	public JPanel getLegendPanel() {
		if (legendPanel == null) {
			legendPanel = new JPanel();
			// setting layout of legend panel...
			legendPanel.setLayout(new GridLayout(5, 2));
			// setting border of this panel...
			legendPanel.setBorder(BorderFactory.createTitledBorder("Legends"));
			// adding labels and icons...
			legendPanel.add(this.getJlblLifeIconDescription());
			legendPanel.add(this.getJlblLifeIcon());
			legendPanel.add(this.getJlblSuperBubbleDescription());
			legendPanel.add(this.getJlblSuperBubbleImage());

			legendPanel.add(this.getJlblLifeBubbleDescription());
			legendPanel.add(this.getJlblLifeBubbleImage());

			legendPanel.add(this.getJlblDeathBubbleDescription());
			legendPanel.add(this.getJlblDeathBubbleImage());

			legendPanel.add(this.getJlblRockBubbleDescription());
			legendPanel.add(this.getJlblRockBubbleImage());
		}

		return legendPanel;
	}

	private JLabel getJlblDeathBubbleDescription() {
		if (jlblDeathBubbleDescription == null) {
			jlblDeathBubbleDescription = new JLabel(
					"Death Bubble takes an extra life of you: ");
		}
		return jlblDeathBubbleDescription;
	}

	private JLabel getJlblDeathBubbleImage() {
		if (jlblDeathBubbleImage == null) {
			jlblDeathBubbleImage = new JLabel(
					ImageIconManager.DEATH_BUBBLE_ICON, JLabel.CENTER);
		}
		return jlblDeathBubbleImage;
	}

	private JLabel getJlblLifeBubbleDescription() {
		if (jlblLifeBubbleDescription == null) {
			jlblLifeBubbleDescription = new JLabel(
					"Life bubble gives an extra life: ");
		}
		return jlblLifeBubbleDescription;
	}

	private JLabel getJlblLifeBubbleImage() {
		if (jlblLifeBubbleImage == null) {
			jlblLifeBubbleImage = new JLabel(ImageIconManager.LIFE_BUBBLE_ICON,
					JLabel.CENTER);
		}
		return jlblLifeBubbleImage;
	}

	private JLabel getJlblLifeIcon() {
		if (jlblLifeIcon == null) {
			jlblLifeIcon = new JLabel(ImageIconManager.LIFE_ICON, JLabel.CENTER);
		}
		return jlblLifeIcon;
	}

	private JLabel getJlblLifeIconDescription() {
		if (jlblLifeIconDescription == null) {
			jlblLifeIconDescription = new JLabel(
					"Life icon displays your life count: ");
		}
		return jlblLifeIconDescription;
	}

	private JLabel getJlblRockBubbleDescription() {
		if (jlblRockBubbleDescription == null) {
			jlblRockBubbleDescription = new JLabel(
					"Rock Bubble turns into a rock when blew the bubble up: ");
		}
		return jlblRockBubbleDescription;
	}

	private JLabel getJlblRockBubbleImage() {
		if (jlblRockBubbleImage == null) {
			jlblRockBubbleImage = new JLabel(ImageIconManager.ROCK_BUBBLE_ICON,
					JLabel.CENTER);
		}
		return jlblRockBubbleImage;
	}

	private JLabel getJlblSuperBubbleDescription() {
		if (jlblSuperBubbleDescription == null) {
			jlblSuperBubbleDescription = new JLabel(
					"Super Bubble blows all the bubbles in the screen: ");
		}
		return jlblSuperBubbleDescription;
	}

	private JLabel getJlblSuperBubbleImage() {
		if (jlblSuperBubbleImage == null) {
			jlblSuperBubbleImage = new JLabel(
					ImageIconManager.SUPER_BUBBLE_ICON, JLabel.CENTER);
		}
		return jlblSuperBubbleImage;
	}

	private void init() {
		this.setSize(700, 400);
		this.setLocation(400, 300);
		this.getContentPane().add(this.getLegendPanel());
		this.setResizable(false);
	}

}
