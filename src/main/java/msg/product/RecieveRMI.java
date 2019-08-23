package msg.product;

import msg.mgsinterface.RecieveMessage;

public class RecieveRMI implements RecieveMessage {
	public boolean recieved() {
		System.out.println("RecieveRMI");
		return false;
	}
}
