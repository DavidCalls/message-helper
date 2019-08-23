package msg.entity;

import java.io.Serializable;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

public class EnterpriseWechatP implements Serializable {
	private String touser;
	private String content;
	private String safe;

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSafe() {
		return safe;
	}

	public void setSafe(String safe) {
		this.safe = safe;
	}

	public boolean propertiesCheck(){
		return  StringUtils.isNotBlank(content);
	}

	public JSONObject getDescJSON(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("touser","/** 发送范围，非必填，默认发送所有，若要指定人格式 user1|user2  **/");
		jsonObject.put("content","/** 微信消息内容，必填 **/");
		jsonObject.put("safe","/** 是否安全传输（1：是，0：否），非必填，默认为0，**/");
		return jsonObject;
	}
}
