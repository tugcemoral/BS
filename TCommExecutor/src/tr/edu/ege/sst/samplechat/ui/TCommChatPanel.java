package tr.edu.ege.sst.samplechat.ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import tr.edu.ege.sst.samplechat.listener.IUIMessageGettable;

/**
 * Chat panel contains a "Chat Area", "Message Box" and a "Send Button". This
 * class is UI of classes which are able to chat.
 * 
 */
public class TCommChatPanel extends JPanel {

	private static final long serialVersionUID = -144686550865411874L;

	private List<IUIMessageGettable> messageGettables;

	private JPanel subPanel;

	private JTextField messageField;

	private JTextArea chatArea;

	private String message;

	/**
	 * Creates an instance of this panel and initializes it.
	 */
	public TCommChatPanel() {
		initialize();
	}

	/**
	 * Adds message gettables to {@link #messageGettables} list which indicates
	 * that the adder wants to listen message is came from UI.
	 * 
	 * @param messageGettable
	 */
	public void addMessageGettable(IUIMessageGettable messageGettable) {
		this.getUIMessageGettables().add(messageGettable);
	}

	public String getMessage() {
		return message;
	}

	/**
	 * When a message is received, it is displayed to chat area.
	 * 
	 * @param message
	 *            the message to write.
	 */
	public void messageReceived(String message) {
		// display incoming message to screen.
		this.chatArea.append(message + "\n");
	}

	/**
	 * Notifies UI message gettables that message is received from UI.
	 */
	protected void notifyMessageFromUI() throws IOException {
		for (IUIMessageGettable messageGettable : getUIMessageGettables()) {
			messageGettable.messageReceivedFromUI(messageField.getText());
		}
	}

	/**
	 * Adds inner components of a chat panel.
	 */
	private void addComponents() {
		// initialize properties and add.
		this.add(initializeChatArea(), BorderLayout.CENTER);
		this.add(initializeSubPanel(), BorderLayout.SOUTH);
	}

	/**
	 * Returns message gettables list.
	 * 
	 * @return list.
	 */
	private List<IUIMessageGettable> getUIMessageGettables() {
		if (messageGettables == null) {
			messageGettables = new Vector<IUIMessageGettable>();
		}
		return messageGettables;
	}

	/**
	 * This method is used to construct UI.
	 */
	private void initialize() {
		// set specific properties and add inner components.
		this.setBorder(BorderFactory.createEtchedBorder());
		this.setSize(300, 400);
		this.setLayout(new BorderLayout());
		addComponents();
	}

	/**
	 * Creates the chat area that displays incoming messages from outside.
	 * 
	 * @return created text area.
	 */
	private JTextArea initializeChatArea() {
		// create and set properties of chat area.
		chatArea = new JTextArea();
		chatArea.setEditable(false);
		chatArea.setAutoscrolls(true);
		chatArea.setFont(new Font("Tahoma", 0, 12));
		return chatArea;
	}

	/**
	 * Creates the message field that messages are written into to send.
	 * 
	 * @return created text field.
	 */
	private JTextField initializeMessageField() {
		// create the message field.
		messageField = new JTextField();
		return messageField;
	}

	/**
	 * Initializes send button and implements action perform of it. When
	 * pressed, notifies that a message is going to sent.
	 * 
	 * @return initialized send button.
	 */
	private JButton initializeSendButton() {
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// send message which taken from text field.
				try {
					notifyMessageFromUI();
					chatArea.append(messageField.getText() + "\n");
					messageField.setText("");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		return sendButton;
	}

	/**
	 * Initializes sub panel that contains message box and send button.
	 * 
	 * @return initialized panel.
	 */
	private JPanel initializeSubPanel() {
		// create a panel.
		subPanel = new JPanel();
		// set layout property and add components into.
		subPanel.setLayout(new GridLayout(1, 2));
		subPanel.add(initializeMessageField());
		subPanel.add(initializeSendButton());
		return subPanel;
	}
}
