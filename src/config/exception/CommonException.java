package config.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

@SuppressWarnings("serial")
public class CommonException extends RuntimeException {

	protected Log log = LogFactory.getLog(CommonException.class);
	
	public CommonException() {
	}

	public CommonException(String message) {
		super(message);
		logMessage(message);
	}

	public CommonException(String message, Throwable cause) {
		super(message, cause);
		logMessage(message);
	}

	public CommonException(Throwable cause) {
		super(cause);
		logMessage(cause.getMessage());
	}

	protected void logMessage(String message){
		log.error(message);
	}
	
}