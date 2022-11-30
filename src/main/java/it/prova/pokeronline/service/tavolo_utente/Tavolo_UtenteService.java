package it.prova.pokeronline.service.tavolo_utente;

import it.prova.pokeronline.dto.tavolo.TavoloDTO;
import it.prova.pokeronline.model.Utente;

public interface Tavolo_UtenteService {
	public TavoloDTO dammiLastGame();
	
	public void entraNelTavolo(Long id);
	
	public Utente gioca(Long id);
	
	public void esciDalTavolo(Long id);
}
