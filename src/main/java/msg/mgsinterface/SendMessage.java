package msg.mgsinterface;

import java.io.File;

public interface SendMessage<T> {

	//默认方法（读取配置文件）,若文件路径为空，则读取默认路径DEFAULT_FILE_PATH
	Boolean send(String filePath);
	//指定对象，对象为相应的消息类型锁对应的实体信息
	Boolean send(Object obj);
}


