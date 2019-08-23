package msg.product;

import msg.mgsinterface.RecieveMessage;

public class RecieveRestful implements RecieveMessage {
	public boolean recieved() {
		System.out.println("RecieveRestful");
		return false;
	}
}
