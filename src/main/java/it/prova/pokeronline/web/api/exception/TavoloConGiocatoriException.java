package it.prova.pokeronline.web.api.exception;

public class TavoloConGiocatoriException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public TavoloConGiocatoriException(String message) {
		super(message);
	}
}
