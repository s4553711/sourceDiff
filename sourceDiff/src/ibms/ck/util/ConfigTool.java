package ibms.ck.util;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class ConfigTool {
	private static Configuration prop = null;

	static {
		try {
			prop = new PropertiesConfiguration("config.properties");
		} catch (ConfigurationException e) {
			throw new RuntimeException("Failed to load config.properties");
		}		
	}
	
	public static String getProperty(String s){
		return prop.getString(s);
	}
}
