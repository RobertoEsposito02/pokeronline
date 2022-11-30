package it.prova.pokeronline.web.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.prova.pokeronline.dto.tavolo.TavoloDTO;
import it.prova.pokeronline.dto.utente.UtenteDTO;
import it.prova.pokeronline.service.tavolo_utente.Tavolo_UtenteService;

@RestController
@RequestMapping("/api/play")
public class PlayController {
	
	@Autowired
	private Tavolo_UtenteService tavolo_UtenteService;
	
	@GetMapping("/lastGame")
	public TavoloDTO dammiLastGame() {
		return tavolo_UtenteService.dammiLastGame();
	}
	
	@GetMapping("/entra/{id}")
	public void entraInUnTavolo(@PathVariable(required = true) Long id) {
		tavolo_UtenteService.entraNelTavolo(id);
	}
	
	@GetMapping("/gioca/{id}")
	public UtenteDTO gioca(@PathVariable(required = true) Long id) {
		return UtenteDTO.buildUtenteDTOFromModel(tavolo_UtenteService.gioca(id));
	}
	
	@GetMapping("/esci/{id}")
	public void esciDaUnTavolo(@PathVariable(required = true) Long id) {
		tavolo_UtenteService.esciDalTavolo(id);
	}
}
