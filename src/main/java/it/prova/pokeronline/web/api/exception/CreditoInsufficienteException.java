package it.prova.pokeronline.web.api.exception;

public class CreditoInsufficienteException extends RuntimeException{
	public CreditoInsufficienteException(String message) {
		super(message);
	}
}
