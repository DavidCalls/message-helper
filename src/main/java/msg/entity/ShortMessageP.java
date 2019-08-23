package msg.entity;

import java.io.Serializable;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

public class ShortMessageP implements Serializable {
	private String phoneNumber;
	private String content;

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

	public boolean propertiesCheck(){
		return  StringUtils.isNotBlank(phoneNumber)
				&& phoneNumber.matches("[1][0-9]{10}")
				&& StringUtils.isNotBlank(content);
	}

	public JSONObject getDescJSON(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("phoneNumber","/** 手机号码，必填 **/");
		jsonObject.put("content","/** 短信内容，必填 **/");
		return jsonObject;
	}


}
