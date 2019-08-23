package msg.mgsinterface;

import java.io.File;

import msg.exception.MessageException;

public interface MessageSender<T extends MessageEntity> {

	/**
	 * 指定对象，对象为相应的消息类型对应的实体信息
	 * @param message 消息对象
	 * @throws MessageException 消息异常
	 */
	void send(T message) throws MessageException;
}
