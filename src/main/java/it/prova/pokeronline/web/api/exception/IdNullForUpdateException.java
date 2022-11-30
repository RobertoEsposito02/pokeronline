package it.prova.pokeronline.web.api.exception;

public class IdNullForUpdateException extends RuntimeException{
	public IdNullForUpdateException(String message) {
		super(message);
	}
}
