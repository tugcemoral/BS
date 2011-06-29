package tr.edu.ege.computergraphics.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class About extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel jpnlAbout;

	public JPanel getJpnlAbout() {
		if (jpnlAbout == null) {
			jpnlAbout = new JPanel();
			BoxLayout boxLayout = new BoxLayout(jpnlAbout, BoxLayout.Y_AXIS);
			// setting properties of panel...
			jpnlAbout.setLayout(boxLayout);
			jpnlAbout.setBorder(BorderFactory
					.createTitledBorder("Bubbles v1.0"));
			decorate();

		}
		return jpnlAbout;
	}

	/**
	 * This is the default constructor
	 */
	public About() {
		super("About");
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(350, 150);
		this.setLocation(300, 300);
		this.setLayout(new BorderLayout());
		this.setResizable(false);
		this.getContentPane().add(getJpnlAbout(), BorderLayout.CENTER);
	}

	/**
	 * Decorates the about panel...
	 * 
	 * @param jpnlAbout
	 */
	private void decorate() {
		// create authors title label...
		JLabel jlblAuthorsTitle = new JLabel("Authors", JLabel.CENTER);
		jlblAuthorsTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
		jlblAuthorsTitle.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.jpnlAbout.add(jlblAuthorsTitle);
		// create authors label...
		JLabel jlblAuthors = new JLabel(
				"Tayfun Gokmen HALAC, Tugcem ORAL, Ziya AKAR", JLabel.CENTER);
		jlblAuthors.setAlignmentX(Component.CENTER_ALIGNMENT);
		// jlblAuthors.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.jpnlAbout.add(jlblAuthors);
		// create ok button...
		JButton jbtnOK = new JButton("OK");
		jbtnOK.setAlignmentX(Component.CENTER_ALIGNMENT);
		// jbtnOK.setAlignmentY(Component.CENTER_ALIGNMENT);
		jbtnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		this.jpnlAbout.add(jbtnOK);
	}
}
