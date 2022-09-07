package HelperPages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j {
	
	private static Logger log =LogManager.getLogger(Log4j.class.getName());
	
	public Logger getLogger() {
		return log;
	}

}
