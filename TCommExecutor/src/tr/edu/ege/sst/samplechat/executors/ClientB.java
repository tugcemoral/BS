package tr.edu.ege.sst.samplechat.executors;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JFrame;

import tr.edu.ege.sst.infrastructure.Communicator;
import tr.edu.ege.sst.infrastructure.IMessageReceivable;
import tr.edu.ege.sst.infrastructure.Message;
import tr.edu.ege.sst.samplechat.listener.IUIMessageGettable;
import tr.edu.ege.sst.samplechat.ui.TCommChatPanel;
import tr.edu.ege.sst.samplechat.util.CommUtil;

/**
 * The Client B is an executor of TComm infrastructure.
 */
public class ClientB extends JFrame implements IMessageReceivable,
		IUIMessageGettable {

	private static final long serialVersionUID = 2654848238216184L;

	public static void main(String[] args) throws UnknownHostException,
			IOException, InterruptedException {
		// create a new instance of this client.
		new ClientB();
	}

	private TCommChatPanel chatPanel;

	private Communicator communicator;

	/**
	 * Creates and initializes an instance of Client B.
	 * 
	 * @throws IOException
	 */
	public ClientB() throws IOException {
		communicator = new Communicator(CommUtil.getB_PortNumber());
		communicator.addMessageReceivable(this);
		// initialize the frame.
		initializeFrame();
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
		Message message = new Message(CommUtil.getClientB_Addr(), CommUtil
				.getClientA_Addr(), messageContent, CommUtil.getA_PortNumber());
		// send message to client B.
		this.getCommunicator().sendMessage(message);
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

	/**
	 * Initializes chat frame of Client B.
	 */
	private void initializeFrame() {
		chatPanel = new TCommChatPanel();
		chatPanel.addMessageGettable(this);
		this.add(chatPanel);

		this.setTitle("B Channel");
		this.setSize(300, 400);
		this.setLocation(350, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
