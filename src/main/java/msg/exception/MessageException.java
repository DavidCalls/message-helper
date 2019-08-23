package msg.exception;

public class MessageException extends Exception {

	public static final String DEFULT_ERR_MSG = " the required field is blank or propertiesCheck return false";

	public MessageException() {
	}

	public MessageException(String message) {
		super(message);
	}

	public MessageException(String message, Throwable cause) {
		super(message, cause);
	}

	public MessageException(Throwable cause) {
		super(cause);
	}

	public MessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
