package hr.redzicleon.application.controllers.reponses;

public class ResponseObject {
	private boolean success;
	private String message;
	public ResponseObject(boolean success, String message) {
		this.success = success;
		this.message = message;
	} 
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public static ResponseObject error(String message) {
		return new ResponseObject(false, message);
	}
	
	public static ResponseObject success(String message) {
		return new ResponseObject(true, message);
	}
	
}
