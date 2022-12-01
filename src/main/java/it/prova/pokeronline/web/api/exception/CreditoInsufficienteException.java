package it.prova.pokeronline.web.api.exception;

public class CreditoInsufficienteException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public CreditoInsufficienteException(String message) {
		super(message);
	}
}
