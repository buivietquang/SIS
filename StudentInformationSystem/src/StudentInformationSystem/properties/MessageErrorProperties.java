
package StudentInformationSystem.properties;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import StudentInformationSystem.util.Constants;

/**
 * Class reads data from the file message_error.properties
 * Class đọc dữ liệu từ file message_error.properties
 * 
 */
public class MessageErrorProperties {
	private static Map<String, String> data = new HashMap<String, String>();

	/**
	 * Read the properties file and put it in the map
	 * 
	 */
	static {
		Properties properties = new Properties();
		try {
			properties.load(new InputStreamReader(
					Thread.currentThread().getContextClassLoader().getResourceAsStream(Constants.MESSAGE_ERROR),
					"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		Enumeration<String> propertyNames = (Enumeration<String>) properties.propertyNames();
		Enumeration<String> enumeration = propertyNames;
		while (enumeration.hasMoreElements()) {
			String key = (String) enumeration.nextElement();
			data.put(key, properties.getProperty(key));
		}
	}

	/**
	 * The function reads from the map
	 *
	 * @param key:
	 *            key to read
	 *
	 * @return the value of the key
	 */
	public String getData(String key) {
		String value = "";
		if (data.containsKey(key)) {
			value = data.get(key);
		}
		return value;
	}

}