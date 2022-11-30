package it.prova.pokeronline;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import it.prova.pokeronline.model.Ruolo;
import it.prova.pokeronline.model.Tavolo;
import it.prova.pokeronline.model.Utente;
import it.prova.pokeronline.service.ruolo.RuoloService;
import it.prova.pokeronline.service.tavolo.TavoloService;
import it.prova.pokeronline.service.utente.UtenteService;

@SpringBootApplication
public class PokeronlineApplication implements CommandLineRunner {

	@Autowired
	private RuoloService ruoloServiceInstance;
	@Autowired
	private UtenteService utenteServiceInstance;
	@Autowired
	private TavoloService tavoloServiceInstance;

	public static void main(String[] args) {
		SpringApplication.run(PokeronlineApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Administrator").codice(Ruolo.ROLE_ADMIN).build());
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Classic Player", Ruolo.ROLE_PLAYER) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Classic Player").codice(Ruolo.ROLE_PLAYER).build());
		}

		if (ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player", Ruolo.ROLE_SPECIAL_PLAYER) == null) {
			ruoloServiceInstance
					.inserisciNuovo(Ruolo.builder().descrizione("Special Player").codice(Ruolo.ROLE_SPECIAL_PLAYER).build());
		}

		if (utenteServiceInstance.findByUsername("admin") == null) {
			Utente admin = Utente.builder()
					.nome("mario")
					.cognome("rossi")
					.username("admin")
					.password("admin")
					.dataRegistrazione(LocalDate.of(2002, 8, 10))
					.esperienzaAccumulata(3)
					.email("email@admin.it")
					.creditoAccumulato(250)
					.build();
			admin.getRuoli().add(ruoloServiceInstance.cercaPerDescrizioneECodice("Administrator", Ruolo.ROLE_ADMIN));
			utenteServiceInstance.inserisciNuovo(admin);
			utenteServiceInstance.changeUserAbilitation(admin.getId());
		}

		if (utenteServiceInstance.findByUsername("user") == null) {
			Utente classicUser = Utente.builder()
					.nome("luigi")
					.cognome("bianchi")
					.username("user")
					.password("user")
					.dataRegistrazione(LocalDate.of(2002, 8, 10))
					.esperienzaAccumulata(1)
					.email("email@user.it")
					.creditoAccumulato(250)
					.build();
			classicUser.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(classicUser);
			utenteServiceInstance.changeUserAbilitation(classicUser.getId());
		}

		if (utenteServiceInstance.findByUsername("user1") == null) {
			Utente classicUser1 = Utente.builder()
					.nome("franco")
					.cognome("verdi")
					.username("user1")
					.password("user1")
					.dataRegistrazione(LocalDate.of(2002, 8, 10))
					.esperienzaAccumulata(0)
					.email("email@user1.it")
					.creditoAccumulato(250)
					.build();
			classicUser1.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Classic Player", Ruolo.ROLE_PLAYER));
			utenteServiceInstance.inserisciNuovo(classicUser1);
			utenteServiceInstance.changeUserAbilitation(classicUser1.getId());
		}

		if (utenteServiceInstance.findByUsername("user2") == null) {
			Utente classicUser2 = Utente.builder()
					.nome("loris")
					.cognome("saija")
					.username("user2")
					.password("user2")
					.email("email@user2.it")
					.dataRegistrazione(LocalDate.of(2002, 8, 10))
					.esperienzaAccumulata(2)
					.creditoAccumulato(250)
					.build();
			classicUser2.getRuoli()
					.add(ruoloServiceInstance.cercaPerDescrizioneECodice("Special Player", Ruolo.ROLE_SPECIAL_PLAYER));
			utenteServiceInstance.inserisciNuovo(classicUser2);
			utenteServiceInstance.changeUserAbilitation(classicUser2.getId());
		}
		 
		Tavolo tavolo1 = Tavolo.builder()
				.esperienzaMinima(1)
				.cifraMinima(100)
				.denominazione("denominazione1")
				.dataCreazione(LocalDate.now())
				.utenteCheCreaIlTavolo(utenteServiceInstance.listAllUtenti().get(0))
				.build();
		tavoloServiceInstance.inserisciNuovoDaApplication(tavolo1);
		
	} 

}
