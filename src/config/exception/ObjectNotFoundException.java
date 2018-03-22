package config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
@SuppressWarnings("rawtypes")
public class ObjectNotFoundException extends CommonException {
	
	private static final long serialVersionUID = 1L;
	
	private Integer objectId;
	
	public ObjectNotFoundException(Integer objectId) {
		super("Object not found ");
		this.objectId = objectId;
	}
	
	public ObjectNotFoundException(Class clazz,Integer objectId) {
		super("Object of type " + clazz.getSimpleName() + " not found");
		this.objectId = objectId;
	}
	
	public ObjectNotFoundException(String message,Integer objectId) {
		super(message);
		this.objectId = objectId;
	}
	
	public Integer getResourceId() {
		return objectId;
	}

	@Override
	protected void logMessage(String message) {
		super.logMessage(message + " , id "+ objectId);
	}	
		
}