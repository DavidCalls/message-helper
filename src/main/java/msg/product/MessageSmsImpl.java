package msg.product;

import java.lang.reflect.InvocationTargetException;

import msg.entity.SmsInfo;
import msg.entity.SmsInfoP;
import msg.exception.MessageException;
import msg.mgsinterface.MessageSender;
import msg.utils.helper.SmsHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageSmsImpl implements MessageSender<SmsInfo> {
	private static final Logger logger = LoggerFactory.getLogger(MessageSmsImpl.class);

	@Override
	public void send(SmsInfo smsInfo) throws MessageException {
		SmsInfoP smsInfoP = new SmsInfoP();
		try {
			BeanUtils.copyProperties(smsInfoP, smsInfo);
		}
		catch (IllegalAccessException e) {
			throw new MessageException(e.getMessage());
		}
		catch (InvocationTargetException e) {
			throw new MessageException(e.getMessage());
		}
		if (smsInfoP.propertiesCheck()) {
			SmsHelper.sendSms(smsInfo);
			logger.debug("Message sms send success");
		}
		else {
			throw new MessageException(MessageException.DEFULT_ERR_MSG);
		}
	}
}
