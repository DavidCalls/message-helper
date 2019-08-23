package msg.product;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

import msg.entity.Email;
import msg.entity.EmailP;
import msg.exception.MessageException;
import msg.mgsinterface.MessageSender;
import msg.mgsinterface.SendMessage;
import msg.utils.helper.EmailHelper;
import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageEmailImpl implements MessageSender<Email> {
	private static final Logger logger = LoggerFactory.getLogger(MessageEnterpriseWechatImpl.class);
	@Override
	public void send(Email email) throws MessageException {
		// 必要参数校验
		EmailP emailP = new EmailP();
		try {
			BeanUtils.copyProperties(emailP, email);
		}
		catch (IllegalAccessException e) {
			throw new MessageException(e.getMessage());
		}
		catch (InvocationTargetException e) {
			throw new MessageException(e.getMessage());
		}
		if(emailP.propertiesCheck()){
			EmailHelper.sendEmail(email);
			logger.info("Message email send success");
		}else {
			throw new MessageException(MessageException.DEFULT_ERR_MSG);
		}

	}
}



