package it.prova.pokeronline.repository.tavolo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import it.prova.pokeronline.model.Tavolo;

public interface TavoloRepository extends CrudRepository<Tavolo, Long>{
	
	@Query("from Tavolo t left join fetch t.utenteCheCreaIlTavolo u where t.id = :id")
	Tavolo findIdByEager(Long id);
}
