package ordemServico.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ordemServico.exeption.DataIntegrityViolationException;
import ordemServico.exeption.ObjectNotFundException;

@ControllerAdvice
public class ResourceExceptionHander {
	
	@ExceptionHandler(ObjectNotFundException.class)
	public ResponseEntity<StandardError> objectNotFundException(ObjectNotFundException ex, 
			HttpServletRequest request){
		
		StandardError standardError = new StandardError(System.currentTimeMillis(), 
				HttpStatus.NOT_FOUND.value(),
				"Object Not found",
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
	}
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException ex, 
			HttpServletRequest request){
		
		StandardError standardError = new StandardError(System.currentTimeMillis(), 
				HttpStatus.BAD_REQUEST.value(),
				"Object Not found",
				ex.getMessage(),
				request.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrors> methodArgumentNotValidException(MethodArgumentNotValidException ex, 
			HttpServletRequest request){
		
		ValidationErrors validationErrors = new ValidationErrors(System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value(),
				"Object Violation:",
				"Violação dos dados:",
				request.getRequestURI());
		
		for(FieldError x: ex.getBindingResult().getFieldErrors()) {
			validationErrors.addErrors(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
	}

}
