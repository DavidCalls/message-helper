package msg.utils.helper;

import java.text.SimpleDateFormat;
import java.util.Date;

import msg.entity.ShortMessage;
import msg.exception.MessageException;
import msg.utils.DBUtil;

public class ShortMessageHelper {
	public static void sendShortMessageByProperties(String filePath) throws MessageException{
		ShortMessage shortMessage = new ShortMessage(filePath);
		sendTemplete(shortMessage);
	}

	public static void sendShortMessage(ShortMessage shortMessage) throws MessageException{
		sendTemplete(shortMessage);
	}

	private static void sendTemplete(ShortMessage shortMessage) throws MessageException {
		String sql =  shortMessage.getSqlTemplete();
		DBUtil dbUtil = DBUtil.getInstance();
		if(dbUtil!=null){
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			String now = simpleDateFormat.format(new Date());
			now = "'"+now+"'";
			sql = String.format(sql,now,shortMessage.getPhoneNumber(),shortMessage.getContent(),now);
			dbUtil.executeSql(sql);
		}

	}





}
