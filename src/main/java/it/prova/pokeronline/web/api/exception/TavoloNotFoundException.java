package it.prova.pokeronline.web.api.exception;

public class TavoloNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TavoloNotFoundException(String message) {
		super(message);
	}
}
