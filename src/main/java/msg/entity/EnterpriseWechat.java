package msg.entity;


import java.util.Properties;

import msg.exception.MessageException;
import msg.mgsinterface.MessageEntity;
import msg.utils.PropertiesUtil;
import org.apache.commons.lang.StringUtils;

public class EnterpriseWechat implements MessageEntity {
	// 企业id
	private String corpid;
	// 应用id
	private String corpsecret;
	// 发送人id ，多个以为|分隔
	private String touser;
	// 应用与小程序-应用-自建-进入其中一个应用-AgentId
	private String agentid;
	// 发送消息的实体
	private String content;
	// 是否是保密消息，0表示否，1表示是
	private String safe;

	public EnterpriseWechat(){
	}

	public EnterpriseWechat(String filePath) throws MessageException {
		Properties properties = PropertiesUtil.getProperties(filePath);
		if(StringUtils.isNotBlank(filePath)){
			this.corpid = properties.getProperty("wechat.sendmsg.config.corpid");
			this.corpsecret = properties.getProperty("wechat.sendmsg.config.corpsecret");
			this.touser = properties.getProperty("wechat.sendmsg.config.touser");
			this.agentid = properties.getProperty("wechat.sendmsg.config.agentid");
			this.content = properties.getProperty("wechat.sendmsg.config.content");
			this.safe = properties.getProperty("wechat.sendmsg.config.safe");
		}else {
			throw new MessageException("file path is null");
		}
	}

	public String getCorpid() {
		return corpid;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getCorpsecret() {
		return corpsecret;
	}

	public void setCorpsecret(String corpsecret) {
		this.corpsecret = corpsecret;
	}

	public String getTouser() {
		return touser;
	}

	public void setTouser(String touser) {
		this.touser = touser;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
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

	public void setProperties(EnterpriseWechatP enterpriseWechatP){
		this.content = enterpriseWechatP.getContent();
		this.touser = enterpriseWechatP.getTouser();
		this.safe = enterpriseWechatP.getSafe();
	}
}
