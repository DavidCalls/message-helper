package msg.utils.helper;

import java.text.MessageFormat;

import msg.entity.EnterpriseWechat;
import msg.exception.MessageException;
import msg.utils.HttpUtil;
import net.sf.json.JSONObject;

public class EnterpriseWeChatHelper {

	public static void sendMsgByProperties(String filePath) throws MessageException{
		EnterpriseWechat enterpriseWechat = new EnterpriseWechat(filePath);
		sendTemplate(enterpriseWechat);
	}

	public static void sendEnterpriseWeChat(EnterpriseWechat enterpriseWechat) throws MessageException{
		sendTemplate(enterpriseWechat);
	}

	private static void sendTemplate(EnterpriseWechat enterpriseWechat) throws MessageException {
			//必要参数校验
			String corpid = enterpriseWechat.getCorpid();
			String content = enterpriseWechat.getContent();
			String corpsecret = enterpriseWechat.getCorpsecret();
			String touser = enterpriseWechat.getTouser();
			String agentid = enterpriseWechat.getAgentid();
			String safe = enterpriseWechat.getSafe();

			if (touser == null || touser.length() == 0) {
				touser = "@all";
			}

			//tokenUrl
			String tokenUrl = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid={0}&corpsecret={1}";
			tokenUrl = MessageFormat.format(tokenUrl, corpid, corpsecret);
			// 获取token
			String resultString = HttpUtil.doGet(tokenUrl);
			//获取token Json对象
			JSONObject resultJson = JSONObject.fromObject(resultString);
			String accessToken = resultJson.getString("access_token");
			String sendMsgUrl = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + accessToken;
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("touser", touser);
			jsonObject.put("msgtype", "text");
			jsonObject.put("agentid", agentid);
			//消息文本内容
			JSONObject textJson = new JSONObject();
			textJson.put("content", content);
			jsonObject.put("text", textJson.toString());
			jsonObject.put("safe", safe);//0 不保密 1 保密
			//发送文本消息
		    HttpUtil.doPost(sendMsgUrl, jsonObject.toString());

	}


}



