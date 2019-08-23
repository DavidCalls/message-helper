package msg.product;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;

import msg.entity.EnterpriseWechatP;
import msg.entity.ShortMessage;
import msg.entity.ShortMessageP;
import msg.exception.MessageException;
import msg.mgsinterface.MessageSender;
import msg.utils.DBUtil;
import msg.utils.helper.EnterpriseWeChatHelper;
import msg.utils.helper.ShortMessageHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageShortDbSenderImpl implements MessageSender<ShortMessage> {
	private static final Logger logger = LoggerFactory.getLogger(MessageShortDbSenderImpl.class);

	public void send(ShortMessage shortMessage) throws MessageException {

		ShortMessageP shortMessageP = new ShortMessageP();
		try {
			BeanUtils.copyProperties(shortMessageP, shortMessage);
		}
		catch (IllegalAccessException e) {
			throw new MessageException(e.getMessage());
		}
		catch (InvocationTargetException e) {
			throw new MessageException(e.getMessage());
		}
		if (shortMessageP.propertiesCheck()) {
			ShortMessageHelper.sendShortMessage(shortMessage);
			logger.debug("Message shortMessage send success");
		}
		else {
			throw new MessageException(MessageException.DEFULT_ERR_MSG);
		}

	}
}
