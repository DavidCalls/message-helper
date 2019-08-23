package msg.utils.helper;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import msg.entity.SmsInfo;
import msg.exception.MessageException;

public class SmsHelper {

	public static void sendSms (SmsInfo smsInfo) throws MessageException{
		sendSmsTemplete(smsInfo);
	}

	public static void sendSms (String filePath) throws MessageException{
		SmsInfo smsInfo = new SmsInfo(filePath);
		sendSmsTemplete(smsInfo);
	}

	private static void sendSmsTemplete(SmsInfo smsInfo) throws MessageException {
		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsInfo.getAccessKeyID(), smsInfo.getAccessKeySecret());
		IAcsClient client = new DefaultAcsClient(profile);
		CommonRequest request = new CommonRequest();
		request.setMethod(MethodType.POST);
		request.setDomain("dysmsapi.aliyuncs.com");
		request.setVersion("2017-05-25");
		request.setAction("SendSms");
		request.putQueryParameter("RegionId", "cn-hangzhou");
		request.putQueryParameter("PhoneNumbers", smsInfo.getPhoneNumbers());
		request.putQueryParameter("SignName", smsInfo.getSignName());
		request.putQueryParameter("TemplateCode", smsInfo.getTemplateCode());
		request.putQueryParameter("TemplateParam", smsInfo.getTemplateParam());
		try {
			CommonResponse response = client.getCommonResponse(request);
			System.out.println(response.getData());
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
//	DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAIDkhHxTLovqCW", "BB2af4W8di3ZOOPyEqfLwPc18RgCS5");
//	IAcsClient client = new DefaultAcsClient(profile);
//	CommonRequest request = new CommonRequest();
//		request.setMethod(MethodType.POST);
//		request.setDomain("dysmsapi.aliyuncs.com");
//		request.setVersion("2017-05-25");
//		request.setAction("SendSms");
//		request.putQueryParameter("RegionId", "cn-hangzhou");
//		request.putQueryParameter("PhoneNumbers", "13566250964,18229538194");
//		request.putQueryParameter("SignName", "402管理系统");
//		request.putQueryParameter("TemplateCode", "SMS_172743070");
//		request.putQueryParameter("TemplateParam", "{\"timeBegin\":\"2019年8月22日\",\"timeEnd\":\"2019年8月22日\"}");
//		try {
//		CommonResponse response = client.getCommonResponse(request);
//		System.out.println(response.getData());
//	} catch (ServerException e) {
//		e.printStackTrace();
//	} catch (ClientException e) {
//		e.printStackTrace();
//	}

}
