package msg.factory;

import msg.entity.Email;
import msg.entity.EnterpriseWechat;
import msg.entity.ShortMessage;
import msg.entity.SmsInfo;
import msg.exception.MessageException;
import msg.mgsinterface.MessageSender;
import msg.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSenderFactory {
	private static final Logger logger = LoggerFactory.getLogger(MessageSenderFactory.class);

	public static <T extends MessageSender> T createMessageSender(Class<T> clazz) throws MessageException {
		try {
			return clazz.newInstance();
		}
		catch (Exception e) {
			logger.error("Message sender create failed:" + e);
			throw new MessageException(e.getMessage());

		}
	}


	public static Email createEmail() throws MessageException {
		return new Email(PropertiesUtil.getDefultConfig());
	}

	public static EnterpriseWechat creatEenterpriseWechat() throws MessageException{
		return new EnterpriseWechat(PropertiesUtil.getDefultConfig());
	}

	public static ShortMessage createShortMessage() throws MessageException{
		return new ShortMessage(PropertiesUtil.getDefultConfig());
	}

	public static SmsInfo createSmsInfo() throws MessageException{
		return new SmsInfo(PropertiesUtil.getDefultConfig());
	}

}
