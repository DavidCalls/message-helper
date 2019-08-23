package msg.entity;

import java.io.InputStream;
import java.util.Properties;

import msg.exception.MessageException;
import msg.mgsinterface.MessageEntity;
import msg.mgsinterface.SendMessage;
import msg.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;

public class Email implements MessageEntity {
	//是否需要认证
	private String auth;
	//邮箱服务IP（域名）
	private String host;
	//邮箱服务端口
	private String port;
	//发送者邮箱账号
	private String user;
	//发送者邮箱密码（部分邮箱为授权码，如163邮箱）
	private String password;
	//发件人地址（同user）
	private String fromAddress;
	//发件人名称
	private String fromPersonal;
	//回复地址
	private String replyTo;
	//抄送
	private String ccUser;
	//附件地址
	private String attachmentPath;
	//密送
	private String bccUser;
	//是否加密写协议传输（1：是 其他：否）
	private String isSsl;
	//i socket工厂副本，若isSsl==1此字段必填，其值直接从配置文件获取 javax.net.ssl.SSLSocketFactory
	private String socketFactoryClass;
	//同port，isSsl为1 此字段必填
	private String socketFactoryPort;
	//发送人
	private String toUser;
	//内容支持html样式如 ：
	// <font color=blue>尊敬的xxx：</br><font color=red>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;测试</font></font>
	private String content;
	//主题
	private String subject;
	//若根据attachmentPath查找文件返回空，则从attachmentStream获取附件
	InputStream attachmentStream;
	//若以流的形式必须传入attachmentName
	private String attachmentName;

	public Email() {
	}

	public Email(String filePath) throws MessageException {
		Properties properties = PropertiesUtil.getProperties(filePath);
		if(properties != null){
			this.auth = properties.getProperty("mail.smtp.auth");
			this.host = properties.getProperty("mail.smtp.host");
			this.port = properties.getProperty("mail.smtp.port");
			this.user = properties.getProperty("mail.user");
			this.password = properties.getProperty("mail.password");
			this.fromAddress = properties.getProperty("mail.from.address");
			this.fromPersonal = properties.getProperty("mail.from.personal");
			this.toUser = properties.getProperty("mail.to.user");
			this.replyTo = properties.getProperty("mail.replyTo");
			this.ccUser = properties.getProperty("mail.cc.user");
			this.bccUser = properties.getProperty("mail.bcc.user");
			this.content = properties.getProperty("mail.content");
			this.subject = properties.getProperty("mail.subject");
			this.attachmentPath = properties.getProperty("mail.attachment.path");
			this.isSsl = properties.getProperty("mail.isSsl");
			this.socketFactoryClass = properties.getProperty("mail.smtp.socketFactory.class");
			this.socketFactoryPort = properties.getProperty("mail.smtp.socketFactory.port");
		}

	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	public InputStream getAttachmentStream() {
		return attachmentStream;
	}

	public void setAttachmentStream(InputStream attachmentStream) {
		this.attachmentStream = attachmentStream;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getFromPersonal() {
		return fromPersonal;
	}

	public void setFromPersonal(String fromPersonal) {
		this.fromPersonal = fromPersonal;
	}

	public String getReplyTo() {
		return replyTo;
	}

	public void setReplyTo(String replyTo) {
		this.replyTo = replyTo;
	}

	public String getCcUser() {
		return ccUser;
	}

	public void setCcUser(String ccUser) {
		this.ccUser = ccUser;
	}

	public String getBccUser() {
		return bccUser;
	}

	public void setBccUser(String bccUser) {
		this.bccUser = bccUser;
	}

	public String getAttachmentPath() {
		return attachmentPath;
	}

	public void setAttachmentPath(String attachmentPath) {
		this.attachmentPath = attachmentPath;
	}

	public String getIsSsl() {
		return isSsl;
	}

	public void setIsSsl(String isSsl) {
		this.isSsl = isSsl;
	}

	public String getSocketFactoryClass() {
		return socketFactoryClass;
	}

	public void setSocketFactoryClass(String socketFactoryClass) {
		this.socketFactoryClass = socketFactoryClass;
	}

	public String getSocketFactoryPort() {
		return socketFactoryPort;
	}

	public void setSocketFactoryPort(String socketFactoryPort) {
		this.socketFactoryPort = socketFactoryPort;
	}
	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public void setProperties(EmailP emailP){
		this.ccUser  = emailP.getCcUser();
		this.bccUser = emailP.getBccUser();
		this.attachmentPath = emailP.getAttachmentPath();
		this.toUser = emailP.getToUser();
		//替换模板内容
		if(StringUtils.isNotBlank(content)){
			this.content = String.format(content,emailP.getContent());
		}else {
			//logger
		}

		this.subject = emailP.getSubject();
		this.attachmentStream = emailP.getAttachmentStream();
		this.attachmentName = emailP.getAttachmentName();
	}
}
