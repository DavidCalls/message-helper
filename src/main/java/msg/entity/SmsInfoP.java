package msg.entity;

import java.io.Serializable;

import msg.exception.MessageException;
import msg.mgsinterface.MessageEntity;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

public class SmsInfoP implements Serializable {
	/** 签名 **/
	private String signName;

	/** 模板code **/
	private String templateCode;

	/** 模板参数(JSON串) **/
	private String templateParam;

	/** 电话号码 **/
	private String phoneNumbers;

	public SmsInfoP() {
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
		return this.phoneNumbers;
	}

	public void setPhoneNumbers(String phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public boolean propertiesCheck() throws MessageException {
		if (StringUtils.isBlank(this.templateParam) ||
				StringUtils.isBlank(this.phoneNumbers) ||
				StringUtils.isBlank(this.signName) ||
				StringUtils.isBlank(this.templateCode)) {
			return false;
		}
		//若templateParam非JSON格式会抛异常，此时工厂方法会自动捕获
		JSONObject.fromObject(this.templateParam);
		return true;
	}
}
