package ordemServico.resource.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

}
