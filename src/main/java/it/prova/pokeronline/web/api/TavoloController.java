package it.prova.pokeronline.web.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.tavolo.TavoloDTO;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.service.tavolo.TavoloService;
import it.prova.pokeronline.web.api.exception.IdNotNullForInsertException;
import it.prova.pokeronline.web.api.exception.IdNullForUpdateException;
import it.prova.pokeronline.web.api.exception.TavoloConGiocatoriException;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;


@RestController
@RequestMapping("/api/tavolo")
public class TavoloController {
	@Autowired
	private TavoloService tavoloService;
	
	@GetMapping
	public List<TavoloDTO> listAll(){
		return TavoloDTO.createListDTOFromModel(tavoloService.listAll());
	}
	
	@GetMapping("/{id}")
	public TavoloDTO findById(@PathVariable(required = true) Long id) {
		Tavolo tavoloDaCaricare = tavoloService.caricaSingoloElementoEager(id);
		if(tavoloDaCaricare == null)
			throw new TavoloNotFoundException("tavolo non trovato");
		
		return TavoloDTO.buildTavoloDTOFromModel(tavoloDaCaricare);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void inserisci(@Valid @RequestBody TavoloDTO tavolo) {
		if(tavolo.getId() != null)
			throw new IdNotNullForInsertException("impossibile inserire un record se contenente id");
		
		tavoloService.inserisciNuovo(tavolo.buildTavoloModel());
	}
	
	@PutMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void update(@RequestBody TavoloDTO tavolo) {
		if(tavolo.getId() == null)
			throw new IdNullForUpdateException("impossibile aggiornare un record se non si inserisce un id");
		
		Tavolo tavoloDaAggiornare = tavoloService.caricaSingoloElementoEager(tavolo.getId());
		if(tavoloDaAggiornare == null)
			throw new TavoloNotFoundException("tavolo non trovato");
		
		tavoloService.aggiorna(tavolo.buildTavoloModel());
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(required = true) Long id) {
		Tavolo tavoloDaEliminare = tavoloService.caricaSingoloElementoEager(id);
		if(tavoloDaEliminare == null)
			throw new TavoloNotFoundException("tavolo non trovato");
		if(tavoloDaEliminare.getUtentiAlTavolo().size() != 0)
			throw new TavoloConGiocatoriException("impossibile eliminare un tavolo con giocatori presenti");
		
		tavoloService.rimuovi(id);
	}
	
	@GetMapping("/cercaTavolo")
	public List<TavoloDTO> listAllByEsperienzaAccumulata(){
		return TavoloDTO.createListDTOFromModel(tavoloService.listAllByEsperienzaAccumulata());
	}
}
