package it.prova.pokeronline.web.api.exception;

public class TavoloNotYourException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TavoloNotYourException(String message) {
		super(message);
	}
}
