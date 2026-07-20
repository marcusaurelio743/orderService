package ordemServico.resource.exception;

import java.io.Serializable;

public class FieldMessage implements Serializable {

	private static final long serialVersionUID = 1L;

	private String feld;
	private String message;

	public String getFeld() {
		return feld;
	}

	public void setFeld(String feld) {
		this.feld = feld;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FieldMessage() {
		super();
	}

	public FieldMessage(String feld, String message) {
		super();
		this.feld = feld;
		this.message = message;
	}

}
