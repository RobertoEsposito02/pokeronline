package it.prova.pokeronline.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "utente")
public class Utente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "nome")
	private String nome;
	@Column(name = "cognome")
	private String cognome;
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	@Column(name = "datecreated")
	private Date dateCreated;
	@Column(name = "dataregistrazione")
	private LocalDate dataRegistrazione;
	@Column(name = "esperienzaaccumulata")
	private Integer esperienzaAccumulata;
	@Column(name = "creditoaccumulato")
	private Integer creditoAccumulato;
	
	@Enumerated(EnumType.STRING)
	private StatoUtente stato;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "utenteCheCreaIlTavolo")
	private List<Tavolo> tavoliCreati = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavoloACuiStoGiocando_id")
	private Tavolo tavoloACuiStoGiocando;
	
	@ManyToMany
	@JoinTable(name = "utente_ruolo", joinColumns = @JoinColumn(name = "utente_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private List<Ruolo> ruoli = new ArrayList<>();
}
