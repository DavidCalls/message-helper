package msg.utils;

import java.io.IOException;
import java.util.Properties;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import msg.exception.MessageException;
import org.apache.commons.lang.StringUtils;

public class PropertiesUtil {


	private PropertiesUtil() {
	}

	public static Properties getProperties(String filePath) throws MessageException {
		Properties properties = null;
		try (InputStream inputStream = new BufferedInputStream(new FileInputStream(new File(filePath)))) {
			properties = new Properties();
			// 加载输入流
			properties.load(inputStream);
			//}
		}
		catch (Exception e) {
			throw new MessageException(e.getMessage());
		}
		return properties;
	}

	/**
	 * 该方法返回默认的
	 * @return
	 * @throws MessageException
	 */
	public static Properties getProperties() throws MessageException {
		return getProperties(getDefultConfig());
	}

	public static String getDefultConfig() throws MessageException {

		String filePath = File.separator + "cfg" + File.separator + "message" + File.separator;

		String fileName = "message.properties";
		/* 判断 System.getProperty("develop.dir") 是否存在 */
		String developDir = System.getProperty("develop.dir");
		String defultUrl;
		File file;
		File path;
		/* 1、存在 判断cfg/message/message.properties是否存在，存在直接读取，否则创建一个message.properties新文件 */
		if (StringUtils.isNotBlank(developDir)) {
			path = new File(developDir + filePath);
			file = new File(developDir + filePath + fileName);
			defultUrl = developDir + filePath + fileName;
			if (!path.exists()) {
				path.mkdirs();
			}

			if (!file.exists()) {
				try {
					file.createNewFile();
				}
				catch (IOException e) {
					throw new MessageException(e.getMessage());
				}
			}
			//2、不存在 读取默认配置文件 默认配置在当前项目的根目录下 cfg/message
		}
		else {
			defultUrl = System.getProperty("user.dir");
			path = new File(defultUrl + filePath);
			file = new File(defultUrl + filePath + fileName);
			defultUrl = defultUrl + filePath + fileName;
			if (!path.exists()) {
				path.mkdirs();
			}
			if (!file.exists()) {
				try {
					file.createNewFile();
				}
				catch (IOException e) {
					throw new MessageException(e.getMessage());
				}
			}
		}
		return defultUrl;
	}

}
