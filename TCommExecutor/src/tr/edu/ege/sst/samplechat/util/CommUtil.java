package tr.edu.ege.sst.samplechat.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

import tr.edu.ege.sst.infrastructure.Message;
import tr.edu.ege.sst.samplechat.executors.ClientA;
import tr.edu.ege.sst.samplechat.executors.ClientB;

/**
 * This class is a Utility class. Contains IP and port numbers of clients.
 * 
 */
public class CommUtil {

	/**
	 * Returns {@link InetAddress} of {@link ClientA}
	 * 
	 * @return IP address.
	 */
	public static String getClientA_Addr() {
		try {
			return InetAddress.getByName("localhost").getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns {@link InetAddress} of {@link ClientB}
	 * 
	 * @return IP address.
	 */
	public static String getClientB_Addr() {
		try {
			return InetAddress.getByName("localhost").getHostAddress();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns port number of {@link ClientB}
	 * 
	 * @return port number
	 */
	public static int getB_PortNumber() {
		return 4152;
	}

	/**
	 * Returns port number of {@link ClientB}
	 * 
	 * @return port number
	 */
	public static int getA_PortNumber() {
		return 4193;
	}

	/**
	 * Finds the client name according to its port number. Used to display who
	 * sent the message.
	 * 
	 * @param message
	 *            message to get receiver port number.
	 * @return <code>A : </code> or <code>B : </code>
	 */
	public static String getClientName(Message message) {
		if (message.getReceiverPortNumber() == getA_PortNumber())
			return "B : ";
		else if (message.getReceiverPortNumber() == getB_PortNumber()) {
			return "A : ";
		}
		return null;
	}

}
