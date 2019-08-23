package msg.product;

import java.lang.reflect.InvocationTargetException;

import msg.entity.EnterpriseWechat;
import msg.entity.EnterpriseWechatP;
import msg.exception.MessageException;
import msg.mgsinterface.MessageSender;
import msg.mgsinterface.SendMessage;
import msg.utils.helper.EnterpriseWeChatHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEnterpriseWechatImpl implements MessageSender<EnterpriseWechat> {
	private static final Logger logger = LoggerFactory.getLogger(MessageEnterpriseWechatImpl.class);

	@Override
	public void send(EnterpriseWechat enterpriseWechat) throws MessageException {
		EnterpriseWechatP enterpriseWechatP = new EnterpriseWechatP();
		try {
			BeanUtils.copyProperties(enterpriseWechatP, enterpriseWechat);
		}
		catch (IllegalAccessException e) {
			throw new MessageException(e.getMessage());
		}
		catch (InvocationTargetException e) {
			throw new MessageException(e.getMessage());
		}
		if (enterpriseWechatP.propertiesCheck()) {
			EnterpriseWeChatHelper.sendEnterpriseWeChat(enterpriseWechat);
			logger.debug("Message EnterpriseWechat send success");
		}
		else {
			throw new MessageException(MessageException.DEFULT_ERR_MSG);
		}

	}
}
