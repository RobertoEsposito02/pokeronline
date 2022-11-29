package it.prova.pokeronline.service.tavolo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;

public class TavoloServiceImpl implements TavoloService{

	@Autowired
	private TavoloRepository repository;
	
	@Override
	public List<Tavolo> listAll() {
		return (List<Tavolo>)repository.findAll();
	}

	@Override
	public Tavolo caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public void aggiorna(Tavolo tavoloInstance) {
		repository.save(tavoloInstance);
	}

	@Override
	public void inserisciNuovo(Tavolo tavoloInstance) {
		repository.save(tavoloInstance);
	}

	@Override
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
	}
	
	
}
