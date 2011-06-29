package tr.edu.ege.computergraphics.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import tr.edu.ege.computergraphics.bubbles.Bubble;
import tr.edu.ege.computergraphics.image.ImageIconManager;
import tr.edu.ege.computergraphics.throwers.RockBubbleThrower;
import tr.edu.ege.computergraphics.throwers.Thrower;

public class BubblesGame extends JFrame {

	public static final int WIDTH = 600;

	public static final int HEIGHT = 500;

	private static final long serialVersionUID = 9026677376502393309L;

	private static final String SCORE = "Score : ";

	private static final String LEVEL = "Level : ";

	private static final int LEVEL_TRESHOLD = 2000;

	private static BubblesGame game;

	public static BubblesGame getInstance() {
		if (game == null) {
			game = new BubblesGame();
		}
		return game;
	}

	public static void main(String[] args) {
		getInstance().setVisible(true);
	}

	private GamePanel gamePanel;

	private JPanel jpnlBackground;

	private int totalScore = 0;

	private int life = 3;

	private JLabel jlblScore;

	private JLabel jlblLife;

	private JPanel jpnlMenu;

	private JLabel jlblLevel;

	private BubblesGame() throws HeadlessException {
		super("Bubbles");
		// set properties of frame...
		this.setSize(WIDTH, HEIGHT);
		this.setLocation(400, 200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// decorate frame...
		decorate();
	}

	public void boomAll() {
		List<Bubble> bubbleList = Thrower.getBubbleList();
		for (Bubble bubble : bubbleList) {
			this.updateScore(bubble.getPoint());
		}
		bubbleList.clear();
		RockBubbleThrower.getRockBubbleList().clear();
	}

	public void decreaseLife() {
		this.getLifeLabel().setText("" + --life);
		if (life == 0) {
			stopGame();
		}
	}

	public GamePanel getGamePanel() {
		if (gamePanel == null) {
			gamePanel = new GamePanel();
		}
		return gamePanel;
	}

	public int getLevel() {
		int level = (int) Math.ceil(getTotalScore() / LEVEL_TRESHOLD) + 1;
		this.getLevelLabel().setText(LEVEL + level);
		return level;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void increaseLife() {
		this.getLifeLabel().setText("" + ++life);
	}

	public void stopGame() {
		this.getGamePanel().stop();

		// JOptionPane.showMessageDialog(null, String.format("Your score is
		// %d.",
		// this.getTotalScore()), "Game Over!",
		// JOptionPane.INFORMATION_MESSAGE);
	}

	public void updateScore(int point) {
		totalScore += point;
		this.getScoreLabel().setText(SCORE + totalScore);
	}

	private JButton createAboutButton() {
		// creating legend button...
		JButton jbtnLegend = new JButton("About");
		jbtnLegend.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbtnLegend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				About about = new About();
				about.setVisible(true);
			}
		});
		return jbtnLegend;
	}

	private Component createLegendButton() {
		// creating legend button...
		JButton jbtnLegend = new JButton("Legend");
		jbtnLegend.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbtnLegend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Legend legend = new Legend();
				legend.setVisible(true);
			}
		});
		return jbtnLegend;
	}

	private JButton createStartButton() {
		// create start button...
		JButton jbtnStart = new JButton("New Game");
		jbtnStart.setAlignmentX(Component.CENTER_ALIGNMENT);
		jbtnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetTheGame();
				getGamePanel().setGameOver(false);
				getGamePanel().start();
			}
		});
		return jbtnStart;
	}

	private void decorate() {
		// set layout...
		this.setLayout(new BorderLayout());
		// add background to contentpane...
		this.getContentPane().add(this.getBackgroundPanel(),
				BorderLayout.CENTER);
	}

	private JPanel getBackgroundPanel() {
		if (jpnlBackground == null) {
			this.jpnlBackground = new JPanel();
			this.jpnlBackground.setLayout(new BorderLayout());
			// add gamePanel to background...
			this.jpnlBackground.add(this.getGamePanel(), BorderLayout.CENTER);
			// add menu to background...
			this.jpnlBackground.add(this.getMenuPanel(), BorderLayout.WEST);
		}
		return jpnlBackground;
	}

	private JLabel getLevelLabel() {
		if (jlblLevel == null) {
			jlblLevel = new JLabel(LEVEL + "1", JLabel.CENTER);
			jlblLevel.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return jlblLevel;
	}

	private JLabel getLifeLabel() {
		if (jlblLife == null) {
			jlblLife = new JLabel(ImageIconManager.LIFE_ICON, JLabel.CENTER);
			jlblLife.setAlignmentX(Component.CENTER_ALIGNMENT);
			jlblLife.setText("" + life);
		}
		return jlblLife;
	}

	private JPanel getMenuPanel() {
		if (jpnlMenu == null) {
			jpnlMenu = new JPanel();
			// set properties...
			// jpnlMenu.setLayout(new GridLayout(6, 1));
			jpnlMenu.setLayout(new BoxLayout(jpnlMenu, BoxLayout.Y_AXIS));
			jpnlMenu.setBorder(new LineBorder(Color.BLACK));
			// create and add buttons...
			jpnlMenu.add(createStartButton());
			jpnlMenu.add(createLegendButton());
			jpnlMenu.add(createAboutButton());
			// add labels...
			jpnlMenu.add(getScoreLabel());
			jpnlMenu.add(getLevelLabel());
			jpnlMenu.add(getLifeLabel());
		}
		return jpnlMenu;
	}

	private JLabel getScoreLabel() {
		if (jlblScore == null) {
			jlblScore = new JLabel(SCORE + totalScore, JLabel.CENTER);
			jlblScore.setAlignmentX(Component.CENTER_ALIGNMENT);
		}
		return jlblScore;
	}

	private void resetTheGame() {
		// set the life as 3...
		this.life = 3;
		this.getLifeLabel().setText("" + life);
		// removing all bubbles from the list...
		Thrower.getBubbleList().clear();
		// setting score as 0 and displaying this...
		this.totalScore = 0;
		this.getScoreLabel().setText(SCORE + totalScore);
	}
}
