package it.prova.pokeronline.web.api.exception;

public class EsperienzaNotEnoughException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EsperienzaNotEnoughException(String message) {
		super(message);
	}
}
