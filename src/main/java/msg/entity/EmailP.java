package msg.entity;

import java.io.InputStream;
import java.io.Serializable;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

public class EmailP implements Serializable {
	/** 抄送 **/
	private String ccUser;
	/** 密送 **/
	private String bccUser;
	/** 附件地址（当附件地址与附件流都不为空，优先附件地址） **/
	private String attachmentPath;
	/** 接收人 **/
	private String toUser;
	/** 内容 **/
	private String content;
	/** 主题 **/
	private String subject;
	/** 附件流 **/
	InputStream attachmentStream;
	/** 附件名称（附件以流的形式，需要传入附件名称） **/
	private String attachmentName;

	public InputStream getAttachmentStream() {
		return attachmentStream;
	}

	public void setAttachmentStream(InputStream attachmentStream) {
		this.attachmentStream = attachmentStream;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
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

	public boolean propertiesCheck(){
		boolean flag = true;
		if(this.attachmentStream != null && StringUtils.isBlank(this.attachmentName)){
			flag = false;
		}
		if(StringUtils.isBlank(toUser)
				|| StringUtils.isBlank(content)
				|| StringUtils.isBlank(subject)){
			flag = false;
		}
		return  flag;
	}

	public JSONObject getDescJSON(){
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("ccUser","/** 抄送人，非必填 **/");
		jsonObject.put("bccUser","/** 密送，非必填 **/");
		jsonObject.put("attachmentPath","/** 邮件附件路径（跨服务器调用接，此项可不填） **/");
		jsonObject.put("toUser","/** 接收人，必填 **/");
		jsonObject.put("content","/** 邮件内容，必填 **/");
		jsonObject.put("subject","/** 邮件主题，必填 **/");
		jsonObject.put("file","/** 附件 **/");
		jsonObject.put("attachmentName","/** 附件名称，附件名称（系统自动处理） **/");
		return jsonObject;
	}


}
