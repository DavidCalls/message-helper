package msg.entity;

import java.util.Properties;

import msg.exception.MessageException;
import msg.mgsinterface.MessageEntity;
import msg.mgsinterface.SendMessage;
import msg.utils.PropertiesUtil;

public class ShortMessage implements MessageEntity {
	//手机号
	private String phoneNumber;
	//发送内容
	private String content;
	//sql模板
	private String sqlTemplete;

	public ShortMessage() {
	}

	public ShortMessage(String filePath) throws MessageException {
		Properties properties = PropertiesUtil.getProperties(filePath);
		if(properties != null){
			this.phoneNumber = properties.getProperty("shortMessgae.phoneNumber");
			this.sqlTemplete = properties.getProperty("shortMessage.sqlTemplete");
			this.content = properties.getProperty("shortMessgae.content");
		}
	}

	public String getSqlTemplete() {
		return sqlTemplete;
	}

	public void setSqlTemplete(String sqlTemplete) {
		this.sqlTemplete = sqlTemplete;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setProperties(ShortMessageP shortMessageP){
		this.phoneNumber = shortMessageP.getPhoneNumber();
		this.content =shortMessageP.getContent();
	}

}
