package tr.edu.ege.computergraphics.image;

import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class ImageIconManager {
	private static final String DIR = "tr/edu/ege/computergraphics/resources/";

	public static final Icon LIFE_ICON = new ImageIcon(DIR + "life_icon.gif");

	public static final Icon DEATH_BUBBLE_ICON = new ImageIcon(DIR
			+ "deathBubble.png");

	public static final Icon LIFE_BUBBLE_ICON = new ImageIcon(DIR
			+ "lifeBubble.gif");

	public static final Icon SUPER_BUBBLE_ICON = new ImageIcon(DIR
			+ "superBubble.gif");

	public static final Icon ROCK_BUBBLE_ICON = new ImageIcon(DIR
			+ "RockBubble.jpg");

	public static final Image DEATH_BUBBLE_IMAGE = new ImageIcon(DIR
			+ "deathBubble.png").getImage();

	public static final Image LIFE_BUBBLE_IMAGE = new ImageIcon(DIR
			+ "lifeBubble.gif").getImage();

	public static final Image SUPER_BUBBLE_IMAGE = new ImageIcon(DIR
			+ "superBubble.gif").getImage();

	public static final Image ROCK_BUBBLE_IMAGE = new ImageIcon(DIR
			+ "RockBubble.jpg").getImage();

	public static final Image BACKGROUND_IMAGE = new ImageIcon(DIR
			+ "background.jpg").getImage();
}
