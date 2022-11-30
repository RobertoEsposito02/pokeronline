package it.prova.pokeronline.service.tavolo_utente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.pokeronline.dto.tavolo.TavoloDTO;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.repository.tavolo.TavoloRepository;
import it.prova.pokeronline.repository.utente.UtenteRepository;
import it.prova.pokeronline.web.api.exception.AlreadyInATavoloException;
import it.prova.pokeronline.web.api.exception.CreditoInsufficienteException;
import it.prova.pokeronline.web.api.exception.EsperienzaNotEnoughException;
import it.prova.pokeronline.web.api.exception.TavoloNotFoundException;
import it.prova.pokeronline.web.api.exception.UtenteNonAlTavoloException;

@Service
public class Tavolo_UtenteServiceImpl implements Tavolo_UtenteService {

	@Autowired
	private UtenteRepository utenteRepository;

	@Autowired
	private TavoloRepository tavoloRepository;

	@Override
	public TavoloDTO dammiLastGame() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessionUtente = utenteRepository.findByUsername(username).orElse(null);
		Tavolo tavoloACuiStoGiocando = tavoloRepository.findTavoloACuiStoGiocando(utenteInSessionUtente.getId())
				.orElse(null);
		return TavoloDTO.buildTavoloDTOFromModel(tavoloACuiStoGiocando);
		
	}
	
	@Override
	@Transactional
	public void entraNelTavolo(Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSessionUtente = utenteRepository.findByUsername(username).orElse(null);
		Tavolo tavoloACuiCiSiVuoleUnire = tavoloRepository.findById(id).orElse(null);
		Tavolo tavoloACuiStoGiocando = tavoloRepository.findTavoloACuiStoGiocando(utenteInSessionUtente.getId())
				.orElse(null);

		if (tavoloACuiCiSiVuoleUnire == null)
			throw new TavoloNotFoundException("tavolo non trovato");
		if (tavoloACuiStoGiocando != null)
			throw new AlreadyInATavoloException("si sta giocando già ad un tavolo, impossibile collegarsi ad un altro");
		if(tavoloACuiCiSiVuoleUnire.getCifraMinima() > utenteInSessionUtente.getCreditoAccumulato())
			throw new CreditoInsufficienteException("credito Insufficiente");
		if(tavoloACuiCiSiVuoleUnire.getEsperienzaMinima() > utenteInSessionUtente.getEsperienzaAccumulata())
			throw new EsperienzaNotEnoughException("esperienza minima non sufficiente");
		
		
		tavoloACuiCiSiVuoleUnire.getUtentiAlTavolo().add(utenteInSessionUtente);
		tavoloRepository.save(tavoloACuiCiSiVuoleUnire);
	}

	@Override
	@Transactional
	public Utente gioca(Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSession = utenteRepository.findByUsername(username).orElse(null);
		Tavolo tavoloACuiCiSiVuoleUnire = tavoloRepository.findById(id).orElse(null);
		
		if (tavoloACuiCiSiVuoleUnire == null)
			throw new TavoloNotFoundException("tavolo non trovato");
		if(tavoloRepository.findTavoloACuiStoGiocando(utenteInSession.getId()) == null)
			throw new UtenteNonAlTavoloException("non si è in nessun tavolo");
		if(tavoloACuiCiSiVuoleUnire.getCifraMinima() > utenteInSession.getCreditoAccumulato()) {
			tavoloACuiCiSiVuoleUnire.getUtentiAlTavolo().remove(utenteInSession);
			tavoloRepository.save(tavoloACuiCiSiVuoleUnire);
			throw new CreditoInsufficienteException("credito Insufficiente");
		}
		
		double segno = Math.random();
		int somma = (int) (Math.random()*1000);
		
		if(segno < 0.5)
			segno = -segno;
		
		Integer nuovoCredito = utenteInSession.getCreditoAccumulato()+ (int)(segno*somma);
		if(nuovoCredito > 0)
			utenteInSession.setCreditoAccumulato(nuovoCredito);
		else
			utenteInSession.setCreditoAccumulato(0);
		utenteRepository.save(utenteInSession);
		
		return utenteRepository.findById(utenteInSession.getId()).orElse(null);
	}

	@Override
	public void esciDalTavolo(Long id) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Utente utenteInSession = utenteRepository.findByUsername(username).orElse(null);
		Tavolo tavoloACuiSiStaGiocando = tavoloRepository.findById(id).orElse(null);
		
		if (tavoloACuiSiStaGiocando == null)
			throw new TavoloNotFoundException("tavolo non trovato");
		
		tavoloACuiSiStaGiocando.getUtentiAlTavolo().remove(utenteInSession);
		tavoloRepository.save(tavoloACuiSiStaGiocando);
	}

}
