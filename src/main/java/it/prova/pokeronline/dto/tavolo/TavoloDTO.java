package it.prova.pokeronline.dto.tavolo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import it.prova.pokeronline.dto.utente.UtenteDTO;
import it.prova.pokeronline.model.Tavolo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class TavoloDTO {
	private Long id;
	@PositiveOrZero
	private Integer esperienzaMinima;
	@PositiveOrZero
	private Integer cifraMinima;
	@NotBlank
	private String denominazione;
	private LocalDate dataCreazione;
	@NotNull
	private UtenteDTO utenteCheCreaIlTavolo;
	
	@Builder.Default
	private List<UtenteDTO> utentiAlTavolo = new ArrayList<>();
	
	public Tavolo buildTavoloModel() {
		Tavolo result = Tavolo.builder()
				.id(id)
				.esperienzaMinima(esperienzaMinima)
				.cifraMinima(cifraMinima)
				.denominazione(denominazione)
				.dataCreazione(dataCreazione)
				.utenteCheCreaIlTavolo(utenteCheCreaIlTavolo.buildUtenteModel(true))
				.utentiAlTavolo(UtenteDTO.createUtenteListModelFromDTO(utentiAlTavolo))
				.build();
		return result;
	}
	
	public static TavoloDTO buildTavoloDTOFromModel(Tavolo tavoloModel) {
		TavoloDTO result = TavoloDTO.builder()
				.id(tavoloModel.getId())
				.esperienzaMinima(tavoloModel.getEsperienzaMinima())
				.cifraMinima(tavoloModel.getCifraMinima())
				.dataCreazione(tavoloModel.getDataCreazione())
				.denominazione(tavoloModel.getDenominazione())
				.utenteCheCreaIlTavolo(UtenteDTO.buildUtenteDTOFromModel(tavoloModel.getUtenteCheCreaIlTavolo()))
				.utentiAlTavolo(UtenteDTO.createUtenteListDTOFromModel(tavoloModel.getUtentiAlTavolo()))
				.build();
				
		return result;
	}
	
	public static List<TavoloDTO> createListDTOFromModel(List<Tavolo> tavoloListModel){
		return tavoloListModel.stream().map(tavolo -> {
			return TavoloDTO.buildTavoloDTOFromModel(tavolo);
		}).collect(Collectors.toList());
	}
	
	public static List<Tavolo> createListModelFromDTO(List<TavoloDTO> tavoloListDTO){
		return tavoloListDTO.stream().map(tavolo -> {
			return tavolo.buildTavoloModel();
		}).collect(Collectors.toList());
	}
}
