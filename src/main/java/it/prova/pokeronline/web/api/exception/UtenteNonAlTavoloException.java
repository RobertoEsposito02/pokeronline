package it.prova.pokeronline.web.api.exception;

public class UtenteNonAlTavoloException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public UtenteNonAlTavoloException(String message) {
		super(message);
	}
}
