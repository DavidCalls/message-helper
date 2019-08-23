package msg.product;

import msg.mgsinterface.RecieveMessage;

public class RecieveMonitor implements RecieveMessage {
	public boolean recieved() {
		System.out.println("RecieveMonitor");
		return false;
	}
}
