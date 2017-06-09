package cn.gcs.core.exception;

public class SysException extends Exception {

	private String errorMsg;
	
	public SysException() {
		super();
		
	}

	public SysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		errorMsg = message;
	}

	public SysException(String message, Throwable cause) {
		super(message, cause);
		errorMsg = message;
		
	}

	public SysException(String message) {
		super(message);
		errorMsg= message;
	}

	public SysException(Throwable cause) {
		super(cause);
		
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
}
