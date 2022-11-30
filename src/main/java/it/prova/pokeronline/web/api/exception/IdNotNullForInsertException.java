package it.prova.pokeronline.web.api.exception;

public class IdNotNullForInsertException extends RuntimeException{
	public IdNotNullForInsertException(String message) {
		super(message);
	}
}
