package it.prova.pokeronline.web.api.exception;

public class UtenteNotFoundException extends RuntimeException{
	public UtenteNotFoundException(String message) {
		super(message);
	}
}
