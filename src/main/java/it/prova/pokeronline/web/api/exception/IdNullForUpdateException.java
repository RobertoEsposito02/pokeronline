package it.prova.pokeronline.web.api.exception;

public class IdNullForUpdateException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public IdNullForUpdateException(String message) {
		super(message);
	}
}
