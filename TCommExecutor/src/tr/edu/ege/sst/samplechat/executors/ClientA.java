package tr.edu.ege.sst.samplechat.executors;

import java.io.IOException;

import javax.swing.JFrame;

import tr.edu.ege.sst.infrastructure.Communicator;
import tr.edu.ege.sst.infrastructure.IMessageReceivable;
import tr.edu.ege.sst.infrastructure.Message;
import tr.edu.ege.sst.samplechat.listener.IUIMessageGettable;
import tr.edu.ege.sst.samplechat.ui.TCommChatPanel;
import tr.edu.ege.sst.samplechat.util.CommUtil;

/**
 * The Client A is an executor of TComm infrastructure.
 */
public class ClientA extends JFrame implements IMessageReceivable,
		IUIMessageGettable {

	private static final long serialVersionUID = -2460419747940330873L;

	public static void main(String[] args) throws IOException {
		// create a new instance of this client.
		new ClientA();
	}

	private TCommChatPanel chatPanel;

	private Communicator communicator;

	/**
	 * Creates and initializes an instance of Client A.
	 * 
	 * @throws IOException
	 * 
	 * @throws IOException
	 */
	public ClientA() throws IOException {
		// create a specific communicator in order to communicate with
		// clientB.
		communicator = new Communicator(CommUtil.getA_PortNumber());
		communicator.addMessageReceivable(this);
		// initialize the frame.
		initializeFrame();
	}

	/**
	 * Creates a communicator instance to communicate with other peers if not
	 * initialized yet.
	 * 
	 * @return initialized {@link Communicator} instance.
	 */
	private Communicator getCommunicator() {
		return communicator;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tr.edu.ege.sst.infrastructure.IMessageReceivable#messageReceived(tr.edu
	 * .ege.sst.infrastructure.Message)
	 */
	@Override
	public void messageReceived(Message message) {
		// send message to UI component.
		chatPanel.messageReceived(CommUtil.getClientName(message)
				+ message.getContent());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tr.edu.ege.sst.samplechat.listener.IUIMessageGettable#messageReceivedFromUI
	 * (java.lang.String)
	 */
	@Override
	public void messageReceivedFromUI(String messageContent) {
		// prepare a message to send to client B.
		Message message = new Message(CommUtil.getClientA_Addr(), CommUtil
				.getClientB_Addr(), messageContent, CommUtil.getB_PortNumber());
		// send message to client B.
		this.getCommunicator().sendMessage(message);
	}

	/**
	 * Initializes chat frame of Client A.
	 */
	private void initializeFrame() {
		chatPanel = new TCommChatPanel();
		// add itself as a message gettable.
		chatPanel.addMessageGettable(this);
		this.add(chatPanel);
		this.setTitle("A Channel");
		this.setSize(300, 400);
		this.setLocation(150, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
