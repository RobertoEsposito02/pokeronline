package it.prova.pokeronline.service.tavolo;

import java.util.List;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloService {
	public List<Tavolo> listAll();

	public Tavolo caricaSingoloElemento(Long id);

	public void aggiorna(Tavolo tavoloInstance);

	public void inserisciNuovo(Tavolo tavoloInstance);

	public void rimuovi(Long idToRemove);

}
