package ordemServico.resource.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrors extends StandardError {

	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> errors = new ArrayList<>();
	

	public ValidationErrors() {
		super();
	}

	public ValidationErrors(Long timestemp, Integer status, String error, String message, String path) {
		super(timestemp, status, error, message, path);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addErrors(String field,String message) {
		this.errors.add(new FieldMessage(field, message));
	}
	
	
	
}
