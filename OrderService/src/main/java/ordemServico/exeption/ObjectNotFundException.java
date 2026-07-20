package ordemServico.exeption;

public class ObjectNotFundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public ObjectNotFundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ObjectNotFundException(String message) {
		super(message);
	}
	
	

}
