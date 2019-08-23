package msg.utils.helper;
import	java.io.ByteArrayInputStream;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;
import javax.mail.util.ByteArrayDataSource;

import java.io.File;
import java.util.Properties;

import msg.entity.Email;
import msg.exception.MessageException;
import org.apache.commons.lang.StringUtils;

public class EmailHelper {

	public static final String IS_SSL = "1";

	public static void sendEmailByProperties(String filePath) throws MessageException {
		Email email = new Email(filePath);
		sendTemplete(email);
	}

	public static void sendEmail(Email email) throws  MessageException{
		sendTemplete(email);
	}

	private static void sendTemplete(Email email) throws MessageException{
		// 配置发送邮件的环境属性
		final Properties props = new Properties();
		// 表示SMTP发送邮件，需要进行身份验证
		props.put("mail.smtp.auth", email.getAuth());
		props.put("mail.smtp.host",email.getHost());
		props.put("mail.smtp.port", email.getPort());
		//props.put("mail.smtp.port", ALIDM_SMTP_PORT);
		// 如果使用ssl，进行如下配置,
		if(IS_SSL.equals(email.getIsSsl())){
			props.put("mail.smtp.socketFactory.class", email.getSocketFactoryClass());
			props.put("mail.smtp.socketFactory.port", email.getSocketFactoryPort());
		}

		// 发件人的账号，填写控制台配置的发信地址,比如xxx@xxx.com
		props.put("mail.user", email.getUser());
		// 访问SMTP服务时需要提供的密码(在控制台选择发信地址进行设置)
		props.put("mail.password", email.getPassword());
		// 构建授权信息，用于进行SMTP进行身份验证
		Authenticator authenticator = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// 用户名、授权码
				String userName = props.getProperty("mail.user");
				String password = props.getProperty("mail.password");
				return new PasswordAuthentication(userName, password);
			}
		};
		// 使用环境属性和授权信息，创建邮件会话
		Session mailSession = Session.getInstance(props, authenticator);
		// 创建邮件消息
		MimeMessage message = new MimeMessage(mailSession) {
		};
		try {
			// 设置发件人邮件地址和名称。填写控制台配置的发信地址,比如xxx@xxx.com。和上面的mail.user保持一致。名称用户可以自定义填写。
			InternetAddress from = new InternetAddress(email.getFromAddress(), email.getFromPersonal());
			message.setFrom(from);
			//可选。设置回信地址
			Address[] a = new Address[1];
			a[0] = new InternetAddress(email.getReplyTo());
			message.setReplyTo(a);
			// 设置收件人邮件地址，比如yyy@yyy.com
			//多个

			//20190820 bug begin
//			String[] toUsers = StringUtils.split(email.getToUser());
//			InternetAddress[] adds = new InternetAddress[toUsers.length];
//			for(int i = 0;i<adds.length;i++){
//				adds[i] = new InternetAddress(toUsers[i]);
//			}
//			message.setRecipients(Message.RecipientType.TO, adds);
			if(StringUtils.isNotBlank(email.getToUser())){
				InternetAddress[] adds = new InternetAddress().parse(email.getToUser());
				message.setRecipients(Message.RecipientType.TO, adds);
			}
			//20190820 bug end


			String ccUser = email.getCcUser();
			// 设置多个抄送地址
			if (StringUtils.isNotBlank(ccUser)) {
				@SuppressWarnings("static-access")
				InternetAddress[] internetAddressCC = new InternetAddress().parse(ccUser);
				message.setRecipients(Message.RecipientType.CC, internetAddressCC);
			}
			String bccUser = email.getBccUser();
			// 设置多个密送地址
			if (StringUtils.isNotBlank(bccUser)) {
				@SuppressWarnings("static-access")
				InternetAddress[] internetAddressBCC = new InternetAddress().parse(bccUser);
				message.setRecipients(Message.RecipientType.BCC, internetAddressBCC);
			}
			// 设置邮件标题
			message.setSubject(email.getSubject());
			// 发送附件，总的邮件大小不超过15M，创建消息部分
			BodyPart messageBodyPart = new MimeBodyPart();
			// 消息
			//messageBodyPart.setText("");
			messageBodyPart.setContent(email.getContent(), "text/html;charset=UTF-8");
			// 创建多重消息
			Multipart multipart = new MimeMultipart();
			// 设置文本消息部分
			multipart.addBodyPart(messageBodyPart);
			//设置要发送附件的文件路径
			String filename = email.getAttachmentPath();

			boolean fileExists = false;//文件存在标识
			if(StringUtils.isNotBlank(filename)){
				File file = new File(filename);
				fileExists = file.isFile() && file.exists();
			}

			boolean fileStreamNotNull = email.getAttachmentStream() != null;//文件流存在标识
			if(fileExists|| fileStreamNotNull){
				// 附件部分
				messageBodyPart = new MimeBodyPart();
				String newFileName = "";
				if(fileExists){//优先文件
					FileDataSource source = new FileDataSource(filename);
					messageBodyPart.setDataHandler(new DataHandler(source));
					//处理附件名称中文（附带文件路径,修改文件名称）乱码问题
					newFileName = StringUtils.substringAfterLast(filename, File.separator);
				}else {
					DataSource source = new ByteArrayDataSource(email.getAttachmentStream(),message.getContentType());
					messageBodyPart.setDataHandler(new DataHandler(source));
					newFileName = email.getAttachmentName();
				}

				messageBodyPart.setFileName(MimeUtility.encodeText(newFileName));
				//注意：Content-ID的属性值一定要加上<>，不能直接写文件名
				String headerValue = "<" + newFileName + ">";
				messageBodyPart.setHeader("Content-ID", headerValue);
				//样式
				multipart.addBodyPart(messageBodyPart);
				// 发送含有附件的完整消息
				// 发送附件代码，结束
			}
			message.setContent(multipart);
			// 发送邮件
			Transport.send(message);
		}catch (Exception e) {
			throw new MessageException(e.getMessage());
		}

	}




}