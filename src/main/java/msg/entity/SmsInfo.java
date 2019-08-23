package msg.entity;

import java.util.Properties;

import msg.exception.MessageException;
import msg.mgsinterface.MessageEntity;
import msg.utils.PropertiesUtil;

public class SmsInfo implements MessageEntity {
	/** 秘钥id **/
	private String accessKeyID;
	/** 秘钥 **/
	private String accessKeySecret;
	/** 签名 **/
	private String signName;
	/** 模板code **/
	private String templateCode;
	/** 模板参数 **/
	private String templateParam;
	/** 电话号码 **/
	private String phoneNumbers;

	public SmsInfo() {
	}

	public SmsInfo(String filePath) throws MessageException {
		Properties properties = PropertiesUtil.getProperties(filePath);
		if(properties != null){
			this.accessKeyID = properties.getProperty("sms.info.accessKeyID");
			this.accessKeySecret = properties.getProperty("sms.info.accessKeySecret");
			this.signName = properties.getProperty("sms.info.signName");
			this.templateCode = properties.getProperty("sms.info.templateCode");
			this.templateParam = properties.getProperty("sms.info.templateParam");
			this.phoneNumbers = properties.getProperty("sms.info.PhoneNumbers");
		}

	}

	public String getAccessKeyID() {
		return accessKeyID;
	}

	public void setAccessKeyID(String accessKeyID) {
		this.accessKeyID = accessKeyID;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getSignName() {
		return signName;
	}

	public void setSignName(String signName) {
		this.signName = signName;
	}

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateParam() {
		return templateParam;
	}

	public void setTemplateParam(String templateParam) {
		this.templateParam = templateParam;
	}

	public String getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public void setProperties(SmsInfoP smsInfoP){
		this.phoneNumbers = smsInfoP.getPhoneNumbers();
		this.templateCode = smsInfoP.getTemplateCode();
		this.templateParam = smsInfoP.getTemplateParam();
		this.signName = smsInfoP.getSignName();
	}

}
