package it.prova.pokeronline.web.api.exception;

public class AlreadyInATavoloException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public AlreadyInATavoloException(String message) {
		super(message);
	}
}
