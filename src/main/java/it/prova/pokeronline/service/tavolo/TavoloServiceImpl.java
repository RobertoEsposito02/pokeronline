package it.prova.pokeronline.service.tavolo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;
import it.prova.pokeronline.repository.utente.UtenteRepository;

@Service
public class TavoloServiceImpl implements TavoloService{

	@Autowired
	private TavoloRepository repository;
	
	@Autowired
	private UtenteRepository utenteRepository;
	
	@Override
	public List<Tavolo> listAll() {
		return (List<Tavolo>)repository.findAll();
	}

	@Override
	public Tavolo caricaSingoloElemento(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Tavolo tavoloInstance) {
		repository.save(tavoloInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Tavolo tavoloInstance) {
		tavoloInstance.setDataCreazione(LocalDate.now());
		if(tavoloInstance.getCifraMinima() == null)
			tavoloInstance.setCifraMinima(0);
		repository.save(tavoloInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Long idToRemove) {
		repository.deleteById(idToRemove);
	}

	@Override
	public Tavolo caricaSingoloElementoEager(Long id) {
		return repository.findIdByEager(id);
	}

	@Override
	public List<Tavolo> listAllByEsperienzaAccumulata() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessionUtente = utenteRepository.findByUsername(username).orElse(null);
		System.err.println(utenteInSessionUtente);
		return repository.listAllByEsperienzaMinima(utenteInSessionUtente.getEsperienzaAccumulata());
	}
	
	
}
