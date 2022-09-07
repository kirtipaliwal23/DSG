package HelperPages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class TestDataHelper {
	public Properties prop;

	public TestDataHelper() throws Exception {
		FileInputStream f = new FileInputStream(System.getProperty("user.dir") + File.separator + "objectRepositories"
				+ File.separator + "data.properties");
		prop = new Properties();
		prop.load(f);
	}
	public String getValue(String s) {
		return prop.getProperty(s);
		
		
	}
}
