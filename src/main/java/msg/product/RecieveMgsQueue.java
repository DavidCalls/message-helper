package msg.product;

import msg.mgsinterface.RecieveMessage;

public class RecieveMgsQueue implements RecieveMessage {

	public boolean recieved() {
		System.out.println("RecieveMgsQueue");
		return false;
	}
}
